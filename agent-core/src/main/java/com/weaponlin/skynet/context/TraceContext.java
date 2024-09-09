package com.weaponlin.skynet.context;

import java.util.UUID;

public class TraceContext {
    private static final ThreadLocal<String> traceIdHolder = new ThreadLocal<>();

    public static final String TRACE_ID = "X-Trace-Id";
    public static final String SPAN_ID = "X-Span-Id";

    public static void setTraceId(String traceId) {
        traceIdHolder.set(traceId);
    }

    public static String generateTraceId() {
        // 生成一个随机的 Trace ID
        return UUID.randomUUID().toString();
    }

    public static String getTraceId() {
        String traceId = traceIdHolder.get();
        if (traceId == null) {
            traceId = generateTraceId();
            setTraceId(traceId);
        }
        return traceId;
    }

    public static void clear() {
        traceIdHolder.remove();
    }
}