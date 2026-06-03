public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        //1. One way to get the Class Object for a custom class
        Class userClass = Class.forName("User");

        //2. using object
        User user = new User(1, "Vijendra");
        Class userClass2 = user.getClass();

        //3. usering .class on class
        Class userClass3 = User.class;

        // System.out.println(userClass.get);
    }
}
