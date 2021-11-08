import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object hello = new HelloClassLoader().findClass("Hello").newInstance();
        Method methodHello = hello.getClass().getMethod("hello");
        methodHello.invoke(hello);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {


        byte[] bytes = getClassByte();

        return defineClass(name,bytes,0,bytes.length);
    }

    private byte[] getClassByte() {

        String classPath = "src/main/resources/Hello.xlass";

        InputStream ins = null;
        byte[] byteData = new byte[0];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ins = new FileInputStream(classPath);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = ins.read(bytes)) != -1) {
                bos.write(bytes,0,len);
            }
            byteData = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ins) {
                    ins.close();
                }
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < byteData.length; i++) {
            byteData[i] = (byte) (255- byteData[i]);
        }
        return byteData;

    }
}
