package lab4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lab1.Transport;

public class SerializeDAO implements DAO {
    @Override
    public void store(Transport t) throws Exception {
        File file = new File("dao.bin");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(t);
        oos.close();
    }

    @Override
    public Transport get() throws Exception {
        File file = new File("dao.bin");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        return (Transport) ois.readObject();
    }
}