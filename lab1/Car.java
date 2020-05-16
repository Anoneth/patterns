package lab1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import lab1.exception.DuplicateModelNameException;
import lab1.exception.ModelPriceOutOfBoundsException;
import lab1.exception.NoSuchModelNameException;
import lab3.Command;

public class Car implements Transport, Cloneable, Serializable {
    protected static class Model implements Serializable {
        private String modelName;
        private double price;

        Model(String modelName, double price) {
            this.modelName = modelName;
            this.price = price;
        }

        void setModelName(String value) {
            modelName = value;
        }

        String getModelName() {
            return modelName;
        }

        void setPrice(double value) {
            price = value;
        }

        double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return modelName + " " + price;
        }
    }

    class CarIterator implements Iterator {
        int current = -1;

        CarIterator() {
            if (models.length > 0) {
                current = 0;
            }
        }

        @Override
        public boolean hasNext() {
            return current != -1 && current < models.length;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return models[current++];
            }
            return null;
        }
    }

    public static class MementoCar {
        static byte[] buf;
        public static Car getCar() {
            try {
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buf));
                Car car = (Car) ois.readObject();
                return car;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            return null;
        }

        public static void setCar(Car car) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(car);
                buf = baos.toByteArray();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private Command command;

    private String name;
    private Model[] models;

    public Car(String name, int size) {
        this.name = name;
        models = new Model[0];
        try {
            Random rnd = new Random();
            for (int i = 0; i < size; i++) {
                addModel("car" + i, rnd.nextInt(100));
            }
        } catch (Exception ex) {

        }
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
    public void setModelName(String oldName, String newName)
            throws NoSuchModelNameException, DuplicateModelNameException {
        for (Model model : models) {
            if (model.getModelName().equals(newName))
                throw new DuplicateModelNameException("Model " + newName + " already exists");
        }
        for (Model model : models) {
            if (model.getModelName().equals(oldName))
                model.setModelName(newName);
        }
        throw new NoSuchModelNameException("Model " + oldName + " not found");
    }

    @Override
    public String[] getModelNames() {
        String[] strings = new String[models.length];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = models[i].getModelName();
        }
        return strings;
    }

    @Override
    public double getPrice(String modelName) throws NoSuchModelNameException {
        for (Model model : models) {
            if (model.getModelName().equals(modelName))
                return model.getPrice();
        }
        throw new NoSuchModelNameException("Model " + modelName + " not found");
    }

    @Override
    public void setPrice(String modelName, double price) throws NoSuchModelNameException {
        if (price < 0)
            throw new ModelPriceOutOfBoundsException("Price must be positive");
        boolean isOk = false;
        for (Model model : models) {
            if (model.getModelName().equals(modelName)) {
                model.setPrice(price);
                isOk = true;
            }
        }
        if (!isOk)
            throw new NoSuchModelNameException("Model " + modelName + " not found");
    }

    @Override
    public double[] getPrices() {
        double[] prices = new double[models.length];
        for (int i = 0; i < prices.length; i++) {
            prices[i] = models[i].getPrice();
        }
        return prices;
    }

    @Override
    public void addModel(String modelName, double price) throws DuplicateModelNameException {
        if (price < 0)
            throw new ModelPriceOutOfBoundsException("Price must be positive");
        for (Model model : models) {
            if (model.getModelName().equals(modelName))
                throw new DuplicateModelNameException("Model " + modelName + " already exists");
        }
        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = new Model(modelName, price);
    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException {
        int i = 0;
        while (i < models.length && !models[i].getModelName().equals(modelName)) {
            i++;
        }
        if (i < models.length) {
            Model[] oldModels = Arrays.copyOf(models, models.length);
            models = new Model[oldModels.length - 1];
            System.arraycopy(oldModels, 0, models, 0, i);
            System.arraycopy(oldModels, i + 1, models, i, models.length - i);
        } else
            throw new NoSuchModelNameException("Model " + modelName + " not found");
    }

    @Override
    public int getModelsSize() {
        return models.length;
    }

    public Object clone() {
        Car clone = null;
        try {
            clone = (Car) super.clone();
            clone.models = models.clone();
            for (int i = 0; i < models.length; i++) {
                clone.models[i] = new Model(models[i].modelName, models[i].price);
            }
        } catch (Exception ex) {
        }
        return clone;
    }

    public void setPrintCommand(Command command) {
        this.command = command;
    }

    public void print(Writer writer) {
        command.print(this, writer);
    }

    public Iterator iterator() {
        return new CarIterator();
    }

    public void createMemento() {
        MementoCar.setCar(this);
    }

    public void setMemento() {
        Car car = MementoCar.getCar();
        if (car != null) {
            this.name = car.name;
            this.models = car.models;
        }
    }

}