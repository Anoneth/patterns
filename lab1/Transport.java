package lab1;

import lab1.exception.DuplicateModelNameException;
import lab1.exception.NoSuchModelNameException;

public interface Transport {
    public String getName();
    public void setName(String name);
    public void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;
    public String[] getModelNames();
    public double getPrice(String modelName) throws NoSuchModelNameException;
    public void setPrice(String modelName, double price) throws NoSuchModelNameException;
    public double[] getPrices();
    public void addModel(String modelName, double price) throws DuplicateModelNameException;
    public void removeModel(String modelName) throws NoSuchModelNameException;
    public int getModelsSize();
}