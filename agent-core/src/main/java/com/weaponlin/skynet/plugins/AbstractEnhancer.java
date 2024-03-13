package com.weaponlin.skynet.plugins;

import java.security.ProtectionDomain;

public abstract class AbstractEnhancer {



    public abstract byte[] enhance(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer);

}
