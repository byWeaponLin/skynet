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


//    @Override
//    public byte[] enhance(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
//        CtClass cl = null;
//        try {
//            ClassPool classPool = ClassPool.getDefault();
//            cl = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
//
//            CtMethod[] methods = cl.getDeclaredMethods();
//            for (CtMethod ctMethod : methods) {
//                if (ctMethod.getName().equals("doExecute")) {
//
//                    // 创建异常处理器类型
//                    CtClass etype = classPool.get("java.lang.Exception");
//
//                    // 创建并命名新的局部变量
//                    ctMethod.addLocalVariable("start", CtClass.longType);
//                    ctMethod.addLocalVariable("end", CtClass.longType);
//
//
//                    // 在方法体后添加catch和finally块
//                    ctMethod.addCatch("throw $e;", etype);
//                    ctMethod.insertBefore("start = System.currentTimeMillis();");
//                    ctMethod.insertAfter("{ end = System.currentTimeMillis(); " +
//                            "System.out.println(\"Process time: \" + (end-start) + \"ms\"); }", true);
////                    ctMethod.insertBefore("long start = System.currentTimeMillis();" +
////                            "                    try {" +
////                            "request.setHeader(\"traceId\", \"192871321jads7q2g312f7sd\");");
////                    ctMethod.insertAfter("} catch (Throwable t) {\n" +
////                            "                        throw t;\n" +
////                            "                    } finally {\n" +
////                            "                        long end = System.currentTimeMillis();\n" +
////                            "                        System.out.println(\"request duration: \" + (end - start));\n" +
////                            "                    }");
//
////                    ctMethod.insertBefore("System.out.println(\"inject succeess\");");
////                    ctMethod.insertBefore("System.out.println(\"inject succeess2\");");
////                    ctMethod.insertBefore("request.setHeader(\"traceId\", \"192871321jads7q2g312f7sd\");");
////                    ctMethod.insertBefore("System.out.println(\"inject succeess, start time: \" + start);");
////                    ctMethod.insertAfter("System.out.println(\"method " + ctMethod.getName() + " cost: \" + (System.currentTimeMillis() - start));");
//                }
//            }
//            cl.writeFile("/Users/linweipeng/baidu/bpit-inf/skynet/skynet/tmp");
//
//            byte[] transformed = cl.toBytecode();
//            return transformed;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return classfileBuffer;
//    }



    @Override
    public byte[] enhance(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        CtClass cl = null;
        try {
            ClassPool classPool = ClassPool.getDefault();
            cl = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));

            CtMethod[] methods = cl.getDeclaredMethods();
            for (CtMethod ctMethod : methods) {
                if (ctMethod.getName().equals("doExecute")) {
//                    long start , end;
//                    ctMethod.addLocalVariable("start", CtClass.longType);
//                    ctMethod.addLocalVariable("end", CtClass.longType);
////                    ctMethod.insertBefore("start = System.currentTimeMillis();");
//                    ctMethod.insertBefore("long start = System.currentTimeMillis();" +
//                            "                    try {" +
//                            "request.setHeader(\"traceId\", \"192871321jads7q2g312f7sd\");");
//                    ctMethod.insertAfter("} catch (Throwable t) {\n" +
//                            "                        throw t;\n" +
//                            "                    } finally {\n" +
//                            "                        long end = System.currentTimeMillis();\n" +
//                            "                        System.out.println(\"request duration: \" + (end - start));\n" +
//                            "                    }");

                    ctMethod.insertBefore("System.out.println(\"inject succeess\");");
                    ctMethod.insertBefore("System.out.println(\"inject succeess2\");");
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
