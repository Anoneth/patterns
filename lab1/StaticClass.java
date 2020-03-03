package lab1;

public class StaticClass {

    private static TransportFactory factory = new AutoFactory();

    public static void printAvg(Transport transport) {
        double avg = 0;
        for (double d : transport.getPrices()) {
            avg += d;
        }
        System.out.println("Avg: " + avg / transport.getModelsSize());
    }

    public static void printModelNames(Transport transport) {
        for (String s : transport.getModelNames()) {
            System.out.println(s);
        }
    }

    public static void printPrices(Transport transport) {
        for (double d : transport.getPrices()) {
            System.out.println(d);
        }
    }

    public static void setTransportFactory(TransportFactory transportFactory) {
        factory = transportFactory;
    }

    public static Transport createInstance(String name, int size) {
        return factory.createInstance(name, size);
    }

    public static Transport synchronizedTransport(Transport t) {
        return new SynchronizedTransport(t);
    }
}