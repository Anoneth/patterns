import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import lab1.Car;
import lab1.Motorcycle;
import lab1.MotorcycleFactory;
import lab1.Singleton;
import lab1.StaticClass;
import lab1.Transport;
import lab2.Adapter;
import lab2.Target;

public class Main {
    public static void main(String[] args) throws Exception {
        testLab2_1();
    }

    static void testLab1_1() {
        System.out.println("------------------------------");
        Singleton singleton = Singleton.getInstance();
        singleton.getProperties().forEach((name, value) -> System.out.println(name + ": " + value));
    }

    static void testLab1_2() throws Exception {
        System.out.println("------------------------------");
        Transport transport = StaticClass.createInstance("car", 5);
        StaticClass.printModelNames(transport);
        StaticClass.printPrices(transport);
        StaticClass.printAvg(transport);

        System.out.println();

        StaticClass.setTransportFactory(new MotorcycleFactory());
        transport = StaticClass.createInstance("mototcycle", 5);
        StaticClass.printModelNames(transport);
        StaticClass.printPrices(transport);
        StaticClass.printAvg(transport);
    }

    static void testLab1_3() throws Exception {
        System.out.println("------------------------------");
        Motorcycle motorcycle = new Motorcycle("name", 1);
        Motorcycle clone = (Motorcycle) motorcycle.clone();
        clone.setPrice("motorcycle0", 155);
        StaticClass.printPrices(motorcycle);
        StaticClass.printPrices(clone);
        Car car = new Car("nameCar", 1);
        Car clone2 = (Car) car.clone();
        clone2.setPrice("car0", 100);
        StaticClass.printPrices(car);
        StaticClass.printPrices(clone2);
    }

    static void testLab2_1() {
        String[] array = new String[] { "one", "two", "three" };
        Target target = new Adapter();
        OutputStream out = target.arrayToStream(array);
        ByteArrayOutputStream baos = (ByteArrayOutputStream) out;
        String res = target.streamToString(new ByteArrayInputStream(baos.toByteArray()));
        System.out.println(res);
    }
}