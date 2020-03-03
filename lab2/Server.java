package lab2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5000);
        while(true) {
            Socket client = server.accept();
            DataInputStream dis = new DataInputStream(client.getInputStream());
            String input = new String(dis.readUTF());
            double a = Double.parseDouble(input.split(",")[0]);
            double b = Double.parseDouble(input.split(",")[1]);
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF(String.valueOf(a * b));
            dos.flush();
            client.close();
        }
    }
}