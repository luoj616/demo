package com.lj.test;

public class MainTest {




    public static void helloword(){

        /*MethodSpec main = MethodSpec.methodBuilder("show")
                .addModifiers(Modifier.PUBLIC,Modifier.STATIC)
                .addStatement("$T.out.println($S)",System.class,"Hello World!")
                .build();
        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder("com.lj.test", helloWorld).build();
        File outputFile = new File("src/"); //输出文件

        try {
            javaFile.writeTo(outputFile);
            javaFile.writeTo(System.out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }

    public static void get(){
       /* try {
            Class hello = Class.forName("com.lj.test.HelloWorld");
            hello.newInstance();
            Method method = hello.getMethod("show", null);
            method.invoke(null, null);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Class Not Found");
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("No Such Method");
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }
}
