
package com.weaponlin.skynet;

import com.weaponlin.skynet.plugins.AbstractEnhancer;
import com.weaponlin.skynet.plugins.httpclient4x.InternalHttpClientEnhancer;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class SkynetTransformer implements ClassFileTransformer {
    public static String clz = "org.apache.http.impl.client.InternalHttpClient";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
//        System.out.println(className);
//        System.out.println(loader.toString());
//        System.out.println(className + "\t" + loader.toString() + "\t" + Thread.currentThread().getContextClassLoader().toString());
        if (!clz.equals(className.replaceAll("/", "."))) {
            return classfileBuffer;
        }
        System.out.println("====================== modify clazz =====================s");


//        CtClass cl = null;
        try {
            AbstractEnhancer enhancer = new InternalHttpClientEnhancer();
            return enhancer.enhance(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
//            ClassPool classPool = ClassPool.getDefault();
//            cl = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
//
//            CtMethod[] methods = cl.getDeclaredMethods();
//            for (CtMethod ctMethod : methods) {
//                if (ctMethod.getName().equals("doExecute")) {
//
////                    ctMethod.addLocalVariable("start", CtClass.longType);
////                    ctMethod.insertBefore("start = System.currentTimeMillis();");
//                    ctMethod.insertBefore("System.out.println(\"inject succeess\");");
//                    ctMethod.insertBefore("request.setHeader(\"xxx\", \"xxx\");;");
////                    ctMethod.insertBefore("System.out.println(\"inject succeess, start time: \" + start);");
////                    ctMethod.insertAfter("System.out.println(\"method " + ctMethod.getName() + " cost: \" + (System.currentTimeMillis() - start));");
//                }
//            }
//
//            byte[] transformed = cl.toBytecode();
//            return transformed;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classfileBuffer;
    }
}