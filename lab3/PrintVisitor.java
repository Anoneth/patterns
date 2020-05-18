package lab3;

import lab1.Car;
import lab1.Motorcycle;

public class PrintVisitor implements Visitor {
    @Override
    public void visit(Car car) {
        double[] t1 = car.getPrices();
        String[] t2 = car.getModelNames();
        System.out.print("Name: " + car.getName() + " Size: " + car.getModelsSize() + ", ");
        for (int i = 0; i < t1.length; i++) {
            System.out.print("Model: " + t2[i] + " Price: " + t1[i] + ", ");
        }
        System.out.println("");
    }

    @Override
    public void visit(Motorcycle motorcycle) {
        double[] t1 = motorcycle.getPrices();
        String[] t2 = motorcycle.getModelNames();
        System.out.println("Name: " + motorcycle.getName() + " Size: " + motorcycle.getModelsSize());
        for (int i = 0; i < t1.length; i++) {
            System.out.println("Model: " + t2[i] + " Price: " + t1[i]);
        }
    }
}