package com.weaponlin.skynet.plugins.httpclient4x;

import com.weaponlin.skynet.plugins.AbstractEnhancer;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.ByteArrayInputStream;
import java.security.ProtectionDomain;
import java.util.UUID;

public class InternalHttpClientEnhancer extends AbstractEnhancer {

    public static String clz = "org.apache.http.impl.client.InternalHttpClient";

    @Override
    public byte[] enhance(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        CtClass cl = null;
        try {
            ClassPool classPool = ClassPool.getDefault();
            cl = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));

            CtMethod[] methods = cl.getDeclaredMethods();
            for (CtMethod ctMethod : methods) {
                if (ctMethod.getName().equals("doExecute")) {
//                    ctMethod.addLocalVariable("start", CtClass.longType);
//                    ctMethod.insertBefore("start = System.currentTimeMillis();");

                    ctMethod.insertBefore("System.out.println(\"inject succeess\");");
                    ctMethod.insertBefore("request.setHeader(\"traceId\", \"192871321jads7q2g312f7sd\");");
//                    ctMethod.insertBefore("System.out.println(\"inject succeess, start time: \" + start);");
//                    ctMethod.insertAfter("System.out.println(\"method " + ctMethod.getName() + " cost: \" + (System.currentTimeMillis() - start));");
                }
            }

            byte[] transformed = cl.toBytecode();
            return transformed;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classfileBuffer;
    }
}
