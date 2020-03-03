package lab2;

import java.io.InputStream;
import java.io.OutputStream;

public interface Target {
    public OutputStream arrayToStream(String[] array);
    public String streamToString(InputStream inputStream);
}