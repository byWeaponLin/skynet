
package com.weaponlin.skynet;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class SkynetTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (!"com/weaponlin/demo/all/App".equals(className)) {
            return classfileBuffer;
        }
        CtClass cl = null;
        try {
            ClassPool classPool = ClassPool.getDefault();
            cl = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));

            CtMethod[] methods = cl.getDeclaredMethods();
            for (CtMethod ctMethod : methods) {
                ctMethod.addLocalVariable("start", CtClass.longType);
                ctMethod.insertBefore("start = System.currentTimeMillis();");
                ctMethod.insertAfter("System.out.println(\"method " + ctMethod.getName() + " cost: \" + (System.currentTimeMillis() - start));");
            }

            byte[] transformed = cl.toBytecode();
            return transformed;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classfileBuffer;
    }
}