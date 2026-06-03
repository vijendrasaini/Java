import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClassDemo {
    public static void main(String[] args) throws NoSuchMethodException {


        // Cannoical -> the name how do we write generally for a class ( using . i.e. User.Address)
        // geSimpleName -> Only short name ( i.e. com.google.Map -> Map : Short name)
        // getName -> (User$Address) -> This is expected / user by forName

        // System.out.println(Outer.Inner.class.getSimpleName()); //Inner
        // System.out.println(Outer.Inner.class.getName()); //Outer$Inner
        // System.out.println(Outer.Inner.class.getCanonicalName());//Outer.Inner

        Class<User> clazz = User.class;
        // Field[] fields = clazz.getDeclaredFields();
        // for (Field field : fields) {
        //     System.out.println();
        //     System.out.println(Modifier.isPrivate(field.getModifiers()) + " " + field.getType() + " " + field.getName() + " " + field.getDeclaringClass());
        // }

        System.out.println("------------------------");
        // Method[] methods = clazz.getDeclaredMethods();
        // for (Method method : methods) {
        //     int mod = method.getModifiers();
        //     System.out.println();
        //     System.out.println(Modifier.isPublic(mod));
        //     System.out.println(Modifier.isStatic(mod));
        //     System.out.println(Modifier.isAbstract(mod));
        //     System.out.println(Modifier.isFinal(mod));
        //     System.out.println(method.getName());
        //     System.out.println(method.getReturnType());
        //     System.out.println(method.getExceptionTypes());
        //     System.out.println(method.getAnnotations());
        // }

        Method getNameMethod = clazz.getDeclaredMethod("getName");
        System.out.println(getNameMethod);
        int mod = getNameMethod.getModifiers();

        
        // System.out.println();
        // System.out.println(Modifier.isPublic(mod));
        // System.out.println(Modifier.isStatic(mod));
        // System.out.println(Modifier.isAbstract(mod));
        // System.out.println(Modifier.isFinal(mod));
    }
}
