package lab4;

import lab1.Transport;

public interface DAO {
    public void store(Transport t) throws Exception;
    public Transport get() throws Exception;
}