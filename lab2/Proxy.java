package lab2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Proxy {
    public static double doMul(double a, double b) {
        double result = 0;
        try {
            Socket client = new Socket("localhost", 5000);
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF(String.valueOf(a) + "," + String.valueOf(b));
            dos.flush();
            DataInputStream dis = new DataInputStream(client.getInputStream());
            result = Double.parseDouble(dis.readUTF());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}