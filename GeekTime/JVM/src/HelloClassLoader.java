import java.util.Base64;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        new HelloClassLoader().findClass("HelloClass").newInstance();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String helloBase64 = "yv66vgAAADQAHAoABgAOCQAPABAIABEKABIAEwcAFAcAFQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAg8Y2xpbml0PgEAClNvdXJjZUZpbGUBAA9IZWxsb0NsYXNzLmphdmEMAAcACAcAFgwAFwAYAQAYSGVsbG8gQ2xhc3MgSW5pdGlhbGl6ZWQhBwAZDAAaABsBAApIZWxsb0NsYXNzAQAQamF2YS9sYW5nL09iamVjdAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVgAhAAUABgAAAAAAAgABAAcACAABAAkAAAAdAAEAAQAAAAUqtwABsQAAAAEACgAAAAYAAQAAAAEACAALAAgAAQAJAAAAJQACAAAAAAAJsgACEgO2AASxAAAAAQAKAAAACgACAAAAAwAIAAQAAQAMAAAAAgAN";
        byte[] bytes = decode(helloBase64);
        return defineClass(name,bytes,0,bytes.length);
    }

    public byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}
