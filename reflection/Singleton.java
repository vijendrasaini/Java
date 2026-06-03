import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class Singleton {
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        BillPugSingle billPugSingle = BillPugSingle.getInstance();

        // System.out.println(billPugSingle);
        // BillPugSingle instance = new BillPugSingle(); // will throw comple time error
        Constructor<BillPugSingle> constructor = BillPugSingle.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        BillPugSingle billPugSingle2 = constructor.newInstance();

        System.out.println(billPugSingle == billPugSingle2);
        System.out.println(billPugSingle2);
    }
}

class BillPugSingle {
    private static boolean isCreated = false;
    private BillPugSingle() {
        if(isCreated) {
            throw new RuntimeException("Singlton already exists");
        }

        isCreated = true;
    }

    private static class Holder {
        static final BillPugSingle INSTANCE = new BillPugSingle();
    }
    
    public static BillPugSingle getInstance() {
        return Holder.INSTANCE;
    }

    public String toString() {
        return "Yes yes!";
    }
}
