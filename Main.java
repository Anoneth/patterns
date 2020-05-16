import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.Iterator;

import lab1.Car;
import lab1.Motorcycle;
import lab1.MotorcycleFactory;
import lab1.Singleton;
import lab1.StaticClass;
import lab1.Transport;
import lab2.Adapter;
import lab2.Target;
import lab3.Row;
import lab3.RowCommand;
import lab3.Smile;
import lab3.Strategy;
import lab3.StrategyDOM;
import lab3.StrategySAX;
import lab3.Column;
import lab3.ColumnCommand;
import lab3.Physics;

public class Main {
    public static void main(String[] args) throws Exception {
        testLab3_7();
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

    static void testLab3_1() {
        Row row = new Row();
        Column column = new Column();
        row.setNext(column);
        column.setNext(row);
        Transport t1 = new Car("car", 2);
        Transport t2 = new Motorcycle("motorcycle", 5);
        column.print(t1);
        row.print(t2);
    }

    static void testLab3_2() throws Exception {
        Car car = new Car("blabla", 5);
        RowCommand rowCommand = new RowCommand();
        ColumnCommand columnCommand = new ColumnCommand();
        FileWriter fileWriter = new FileWriter("command.txt");
        car.setPrintCommand(rowCommand);
        car.print(fileWriter);
        fileWriter.flush();
        car.setPrintCommand(columnCommand);
        car.print(fileWriter);
        fileWriter.flush();
    }

    static void testLab3_3() {
        Car car = new Car("bla", 5);
        Iterator iterator = car.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    static void testLab3_4() {
        Car car = new Car("memento", 4);
        System.out.println("Origin: " + car.getName());
        car.createMemento();
        car.setName("memento321");
        System.out.println("Edited: " + car.getName());
        car.setMemento();
        System.out.println("Restored: " + car.getName());
    }

    static void testLab3_5() {
        Smile smile = new Smile();
        smile.setVisible(true);
    }

    static void testLab3_6(String[] args) throws Exception {
        Strategy strategy = new StrategySAX();
        strategy.check(args[0], args[1]);
    }

    static void testLab3_7() {
        Physics physics = new Physics();
        physics.setVisible(true);
    }
}