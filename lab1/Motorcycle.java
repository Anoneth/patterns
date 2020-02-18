package lab1;

import lab1.exception.DuplicateModelNameException;
import lab1.exception.ModelPriceOutOfBoundsException;
import lab1.exception.NoSuchModelNameException;

public class Motorcycle implements Transport, Cloneable {
    private class Model {
        String modelName = null;
        double price = Double.NaN;
        
        Model prev = null;
        Model next = null;
    }
    
    private Model head = new Model();
    {
        head.prev = head;
        head.next = head;
    }

    private int size = 0;
    private String name;

    public Motorcycle(String name, int size) {
        this.name = name;
        this.size = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String value) {
        name = value;
    }

    @Override
    public void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        Model model = head.next;
        while (model != head) {
            if (model.modelName.equals(newName)) throw new DuplicateModelNameException("Model " + newName + " already exists");
        }
        while (model != head && !model.modelName.equals(oldName)) {
            model = model.next;
        }
        if (model != head) {
            model.modelName = newName;
        }
        else {
            throw new NoSuchModelNameException("Model " + oldName + " not found");
        }
    }

    @Override
    public String[] getModelNames() {
        String[] strings = new String[size];
        int i = 0;
        Model model = head.next;
        while (model != head) {
            strings[i] = model.modelName;
            model = model.next;
            i++;
        }
        return strings;
    }

    @Override
    public double getPrice(String modelName) throws NoSuchModelNameException {
        Model model = head.next;
        while (model != head && !model.modelName.equals(modelName)) {
            return model.price;
        }
        throw new NoSuchModelNameException("Model " + modelName + " not found");
    }

    @Override
    public void setPrice(String modelName, double price) throws NoSuchModelNameException {
        if (price < 0) throw new ModelPriceOutOfBoundsException("Price must be positive");
        Model model = head.next;
        while (model != head && !model.modelName.equals(modelName)) {
            model.price = price;
        }
        if (model == head) throw new NoSuchModelNameException("Model " + modelName + " not found");
    }

    @Override
    public double[] getPrices() {
        double[] prices = new double[size];
        int i = 0;
        Model model = head.next;
        while (model != head) {
            prices[i] = model.price;
        }
        return prices;
    }

    @Override
    public void addModel(String modelName, double price) throws DuplicateModelNameException {
        if (price < 0) throw new ModelPriceOutOfBoundsException("Price must be positive");
        Model model = head.next;
        while (model != head && !model.modelName.equals(modelName)) {
            model = model.next;
        }
        if (model == head) throw new DuplicateModelNameException("Model " + modelName + " already exists");
        Model newModel = new Model();
        newModel.modelName = modelName;
        newModel.price = price;
        Model last = head.prev;
        last.next = newModel;
        newModel.prev = last;
        head.prev = newModel;
        newModel.next = head;
        size++;
    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        Model model = head.next;
        while (model != head && !model.modelName.equals(modelName)) {
            model = model.next;
        }
        if (model != head) {
            Model prev = model.prev;
            Model next = model.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }
        else throw new NoSuchModelNameException("Model " + modelName + " not found");
    }

    @Override
    public int getModelsSize() {
        return size;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}