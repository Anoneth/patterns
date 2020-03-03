package lab2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Adaptee {
    public OutputStream arrayToStream(String[] array) {
        OutputStream outputStream = new ByteArrayOutputStream();
        for (String s : array) {
            try {
                outputStream.write(s.getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return outputStream;
    }

    public String streamToString(InputStream inputStream) {
        String out = null;
        try {
            out = new String(inputStream.readAllBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return out;
    }
}