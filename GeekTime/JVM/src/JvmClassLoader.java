import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class JvmClassLoader {
    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println("urL.toExternalForm() = " + urL.toExternalForm());
        }
        printCLassLoader("扩展类加载器",JvmClassLoader.class.getClassLoader().getParent());
        printCLassLoader("应用类加载器",JvmClassLoader.class.getClassLoader());

    }

    public static void printCLassLoader(String name, ClassLoader classLoader) {

        System.out.println("--------------");
        if (null != classLoader) {
            System.out.println("name = " + name + "-> " + classLoader.toString());
            printUrlForCLassLoader(classLoader);
        } else {
            System.out.println(name+ " classLoader is null");
        }
    }

    private static void printUrlForCLassLoader(ClassLoader classLoader) {
        Object ucp = insightField(classLoader, "ucp");
        //ucp 里面隐藏了所有类的路径
        Object path = insightField(ucp, "path");
        List paths = (List) path;
        for (Object o : paths) {
            System.out.println("==========>" + o.toString());
        }
    }

    private static Object insightField(Object obj, String fName) {
        Field f = null;
        try {
            if (obj instanceof URLClassLoader) {
                f = URLClassLoader.class.getDeclaredField(fName);
            } else {
                f = obj.getClass().getDeclaredField(fName);
            }
            f.setAccessible(true);
            return f.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
