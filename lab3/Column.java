package lab3;

import java.io.FileWriter;

import lab1.Transport;

public class Column implements ChainOfResponsibility {
    private ChainOfResponsibility next;

    @Override
    public void print(Transport transport) {
        if (transport.getModelsSize() > 3) {
            try (FileWriter fileWriter = new FileWriter("out.txt", true)) {
                double[] t1 = transport.getPrices();
                String[] t2 = transport.getModelNames();
                fileWriter.append("Name: " + transport.getName() + " Size: " + transport.getModelsSize() + "\r\n");
                for (int i = 0; i < t1.length; i++) {
                    fileWriter.append("Model: " + t2[i] + " Price: " + t1[i] + "\r\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else next.print(transport);
    }

    @Override
    public void setNext(ChainOfResponsibility next) {
        this.next = next;
    }
}