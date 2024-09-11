package com.weaponlin.skynet.plugins.httpclient4x;

import com.google.common.collect.Lists;
import com.weaponlin.skynet.context.TraceContext;
import com.weaponlin.skynet.plugins.AbstractInterceptor;
import com.weaponlin.skynet.plugins.Interceptor;
import com.weaponlin.skynet.plugins.PluginsLoader;
import net.bytebuddy.asm.Advice;
import org.apache.http.HttpRequest;

import javax.annotation.PostConstruct;
import java.util.List;

@Interceptor(
        clazz = HttpClientInterceptor.class,
        clazzName = HttpClientInterceptor.ENHANCE_CLAZZ_NAME,
        methods = {"doExecute"}
)
public class HttpClientInterceptor extends AbstractInterceptor {
    public static final Class<?> ENHANCE_CLAZZ = HttpClientInterceptor.class;
    public static final String ENHANCE_CLAZZ_NAME = "org.apache.http.impl.client.InternalHttpClient";
    public static final List<String> ENHANCE_METHODS = Lists.newArrayList("doExecute");

    @Advice.OnMethodEnter
    public static void onEnter(@Advice.Argument(1) HttpRequest request) {
        String traceId = TraceContext.getTraceId();
        System.out.println("trace id: " + traceId);
        request.addHeader(TraceContext.TRACE_ID, traceId);
    }

    @PostConstruct
    public void register() {
        PluginsLoader.register();
    }
}