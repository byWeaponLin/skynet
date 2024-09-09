package com.weaponlin.skynet.plugins.tomcat;

import com.weaponlin.skynet.context.TraceContext;
import net.bytebuddy.asm.Advice;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class TomcatInterceptor {

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
