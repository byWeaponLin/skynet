package com.weaponlin.skynet.plugins.httpclient4x;

import com.weaponlin.skynet.context.TraceContext;
import net.bytebuddy.asm.Advice;
import org.apache.http.HttpRequest;

public class HttpClientInterceptor {
    @Advice.OnMethodEnter
    public static void onEnter(@Advice.Argument(1) HttpRequest request) {
        String traceId = TraceContext.getTraceId();
        System.out.println("trace id: " + traceId);
        request.setHeader("X-Trace-Id", traceId);
        request.addHeader("X-Trace-Id", traceId);
    }
}