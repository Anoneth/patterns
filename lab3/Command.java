package lab3;

import java.io.Writer;

import lab1.Car;

public interface Command {
    public void print(Car car, Writer writer);
}