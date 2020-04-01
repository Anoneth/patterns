package lab2;

import java.io.InputStream;
import java.io.OutputStream;

public interface Adaptee {
    public OutputStream arrayToStream(String[] array);

    public String streamToString(InputStream inputStream);
}