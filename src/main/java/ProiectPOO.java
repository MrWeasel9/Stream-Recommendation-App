public class ProiectPOO {
    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Nothing to read here");
            return;
        }
        Facade facade = new Facade(args);
        facade.run();

    }
}



