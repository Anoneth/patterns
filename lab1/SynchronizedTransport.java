package lab1;

import lab1.exception.DuplicateModelNameException;
import lab1.exception.NoSuchModelNameException;
import lab3.Visitor;

public class SynchronizedTransport implements Transport {
    private Transport t;

    public SynchronizedTransport(Transport t) {
        this.t = t;
    }

    @Override
    public synchronized void setName(String name) {
        t.setName(name);
    }

    @Override
    public synchronized String getName() {
        return t.getName();
    }

    @Override
    public synchronized void setModelName(String oldName, String newName)
            throws NoSuchModelNameException, DuplicateModelNameException {
        t.setModelName(oldName, newName);
    }

    @Override
    public synchronized void addModel(String modelName, double price) throws DuplicateModelNameException {
        t.addModel(modelName, price);
    }

    @Override
    public synchronized void setPrice(String modelName, double price) throws NoSuchModelNameException {
        t.setPrice(modelName, price);
    }

    @Override
    public synchronized double getPrice(String modelName) throws NoSuchModelNameException {
        return t.getPrice(modelName);
    }

    @Override
    public synchronized String[] getModelNames() {
        return t.getModelNames();
    }

    @Override
    public synchronized double[] getPrices() {
        return t.getPrices();
    }

    @Override
    public synchronized int getModelsSize() {
        return t.getModelsSize();
    }

    @Override
    public synchronized void removeModel(String modelName) throws NoSuchModelNameException {
        t.removeModel(modelName);
    }

    @Override
    public synchronized void accept(Visitor visitor) {
        t.accept(visitor);
    }

}