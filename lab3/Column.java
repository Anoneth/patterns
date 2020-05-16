package lab3;

import java.io.FileWriter;

import lab1.Transport;

public class Column implements ChainOfResponsibility {
    private ChainOfResponsibility next;

    @Override
    public void print(Transport transport) {
        if (transport.getModelsSize() > 3) {
            try (FileWriter fileWriter = new FileWriter("out.txt", true)) {
                fileWriter.append(transport.getName() + "\r\n" + transport.getModelsSize() + "\r\n");
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