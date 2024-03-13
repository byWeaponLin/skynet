package com.weaponlin.skynet.plugins;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PluginsLoader {

    public static Map<String, AbstractEnhancer> enhancers = new ConcurrentHashMap<>();

    public AbstractEnhancer getEnhancer(String clazz) {

        return null;
    }

}
