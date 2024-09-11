
package com.weaponlin.skynet;

import com.weaponlin.skynet.plugins.Plugin;
import com.weaponlin.skynet.plugins.PluginsLoader;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.util.List;

public class SkynetAgent {
    /**
     * jvm 参数形式启动，运行此方法
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("skynet agent premain");

        AgentBuilder agentBuilder = new AgentBuilder.Default();
        List<Plugin> plugins = PluginsLoader.getPlugins();
        for (Plugin plugin : plugins) {
            agentBuilder = agentBuilder.type(ElementMatchers.named(plugin.getClazzName()))
                    .transform((builder, typeDescription, classLoader, module) -> {
                        return builder.method(ElementMatchers.namedOneOf(plugin.getMethods()))
                                .intercept(Advice.to(plugin.getClazz()));
                    });
        }
        agentBuilder.installOn(inst);
    }

    /**
     * 动态 attach 方式启动，运行此方法
     *
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("skynet agent agentmain");
        AgentBuilder agentBuilder = new AgentBuilder.Default();
        List<Plugin> plugins = PluginsLoader.getPlugins();
        for (Plugin plugin : plugins) {
            agentBuilder = agentBuilder.type(ElementMatchers.named(plugin.getClazzName()))
                    .transform((builder, typeDescription, classLoader, module) -> {
                        return builder.method(ElementMatchers.namedOneOf(plugin.getMethods()))
                                .intercept(Advice.to(plugin.getClazz()));
                    });
        }
        agentBuilder.installOn(inst);
    }
}
