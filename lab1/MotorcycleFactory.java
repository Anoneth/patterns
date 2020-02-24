package lab1;

public class MotorcycleFactory implements TransportFactory {
    public Transport createInstance(String name, int size) {
        return new Motorcycle(name, size);
    }
}