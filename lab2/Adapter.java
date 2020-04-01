package lab2;

import java.io.InputStream;
import java.io.OutputStream;

public class Adapter implements Target {
    private Adaptee adaptee;
    
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public OutputStream arrayToStream(String[] array) {
        return adaptee.arrayToStream(array);
    }

    @Override
    public String streamToString(InputStream inputStream) {
        return adaptee.streamToString(inputStream);
    }
}