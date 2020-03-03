package lab2;

public class Client {
    public static void main(String[] args) {
        double a = 10;
        double b = 22;
        double c = Proxy.doMul(a, b);
        System.out.println(c);
    }
}