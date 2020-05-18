package lab3;

import lab1.Car;
import lab1.Motorcycle;

public interface Visitor {
    public void visit(Car car);
    public void visit(Motorcycle motorcycle);
}