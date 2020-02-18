import lab1.Singleton;
import lab1.StaticClass;
import lab1.Transport;

public class Main {
    public static void main(String[] args) throws Exception {
        testLab1_1();
        testLab1_2();
    }

    static void testLab1_1() {
        Singleton singleton = Singleton.getInstance();
        singleton.getProperties().forEach((name, value) -> System.out.println(name + ": " + value));
    }

    static void testLab1_2() throws Exception {
        Transport transport = StaticClass.createInstance("name", 0);
        transport.addModel("name1", 10);
        transport.addModel("name2", 11);
        transport.addModel("name3", 12);
        transport.removeModel("name2");
        StaticClass.printAvg(transport);
        StaticClass.printModelNames(transport);
        StaticClass.printPrices(transport);
    }
}