package lab3;

import java.io.Writer;

import lab1.Car;

public class RowCommand implements Command {
    @Override
    public void print(Car car, Writer writer) {
        try {
            double[] t1 = car.getPrices();
            String[] t2 = car.getModelNames();
            writer.append("Name: " + car.getName() + " Size: " + car.getModelsSize());
            for (int i = 0; i < t1.length; i++) {
                writer.append(", Model: " + t2[i] + " Price: " + t1[i]);
            }
            writer.append("\r\n");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}