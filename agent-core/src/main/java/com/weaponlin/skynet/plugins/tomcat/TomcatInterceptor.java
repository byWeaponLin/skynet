package com.weaponlin.skynet.plugins.tomcat;

import com.google.common.collect.Lists;
import com.weaponlin.skynet.context.TraceContext;
import com.weaponlin.skynet.plugins.AbstractInterceptor;
import com.weaponlin.skynet.plugins.Interceptor;
import net.bytebuddy.asm.Advice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Interceptor(
        clazz = TomcatInterceptor.class,
        clazzName = TomcatInterceptor.ENHANCE_CLAZZ_NAME,
        methods = {"invoke"}
)
public class TomcatInterceptor extends AbstractInterceptor {

    public static final Class<?> ENHANCE_CLAZZ = TomcatInterceptor.class;
    public static final String ENHANCE_CLAZZ_NAME = "org.apache.catalina.core.StandardHostValve";
    public static final List<String> ENHANCE_METHODS = Lists.newArrayList("invoke");

    @Advice.OnMethodEnter
    public static void onEnter(@Advice.Argument(0) HttpServletRequest request) {
        String traceId = request.getHeader(TraceContext.TRACE_ID);
        System.out.println("traceId from tomcat interceptor: " + traceId);
        traceId = Optional.ofNullable(traceId).orElseGet(TraceContext::generateTraceId);
        TraceContext.setTraceId(traceId);
    }

    @Advice.OnMethodExit
    public static void onExit() {
        TraceContext.clear();
    }
}
