package com.weaponlin.skynet.plugins;


import com.weaponlin.skynet.plugins.httpclient4x.HttpClientInterceptor;
import com.weaponlin.skynet.plugins.tomcat.TomcatInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PluginsLoader {

    private static List<Class<? extends AbstractInterceptor>> plugins = new ArrayList<>();

    public static void register() {
        plugins.add(HttpClientInterceptor.class);
        plugins.add(TomcatInterceptor.class);
    }

    /**
     * TODO load plugins from premain/agentmain args
     */
    public static List<Plugin> getPlugins() {
        register();
        return plugins.stream().map(p -> {
            Interceptor anno = p.getAnnotation(Interceptor.class);
            Plugin plugin = new Plugin();
            plugin.setClazz(anno.clazz());
            plugin.setClazzName(anno.clazzName());
            plugin.setMethods(anno.methods());
            return plugin;
        }).collect(Collectors.toList());
    }

}
