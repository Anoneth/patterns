package lab1;

public class AutoFactory implements TransportFactory {
    @Override
    public Transport createInstance(String name, int size) {
        return new Car(name, size);
    }
}