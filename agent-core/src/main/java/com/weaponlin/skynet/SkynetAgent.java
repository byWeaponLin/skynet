
package com.weaponlin.skynet;

import com.weaponlin.skynet.plugins.httpclient4x.HttpClientInterceptor;
import com.weaponlin.skynet.plugins.tomcat.TomcatInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class SkynetAgent {
    /**
     * jvm 参数形式启动，运行此方法
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        AgentBuilder.Default agentBuilder = new AgentBuilder.Default();
        agentBuilder.type(ElementMatchers.named("org.apache.http.impl.client.InternalHttpClient"))
                .transform((builder, typeDescription, classLoader, module) ->
                        builder.method(ElementMatchers.named("doExecute"))
                                .intercept(Advice.to(HttpClientInterceptor.class)))
                .installOn(inst);

//        agentBuilder.type(ElementMatchers.named("org.apache.catalina.connector.Request"))
//                .transform((builder, typeDescription, classLoader, module) ->
//                        builder.method(ElementMatchers.named("startAsync"))
//                                .intercept(Advice.to(TomcatInterceptor.class)));
//
//        agentBuilder.installOn(inst);


//        new AgentBuilder.Default()
//                .type(ElementMatchers.named("org.apache.catalina.connector.Request"))
//                .transform((builder, typeDescription, classLoader, module) ->
//                        builder.method(ElementMatchers.named("startAsync"))
//                                .intercept(Advice.to(TomcatInterceptor.class)))
//                .installOn(inst);
    }

    /**
     * 动态 attach 方式启动，运行此方法
     *
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        new AgentBuilder.Default()
                .type(ElementMatchers.named("org.apache.http.impl.client.InternalHttpClient"))
                .transform((builder, typeDescription, classLoader, module) ->
                        builder.method(ElementMatchers.named("doExecute"))
                                .intercept(Advice.to(HttpClientInterceptor.class)))
                .installOn(inst);
    }
}
