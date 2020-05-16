package lab3;

import lab1.Transport;

public interface ChainOfResponsibility {
    public void print(Transport transport);
    public void setNext(ChainOfResponsibility next);
}