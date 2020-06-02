package lab4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import lab1.Car;
import lab1.Motorcycle;
import lab1.Transport;

public class TextDAO implements DAO {
    @Override
    public void store(Transport t) throws Exception {
        File file = new File("dao.txt");
        FileWriter fileWriter = new FileWriter(file);
        if (t instanceof Car) {
            fileWriter.append("Тип: автомобиль\n");
        } else {
            fileWriter.append("Тип: мотоцикл\n");
        }
        fileWriter.append("Название: " + t.getName() + "\n");
        fileWriter.append("Количество: " + t.getModelsSize() + "\n");
        String[] names = t.getModelNames();
        double[] prices = t.getPrices();
        for (int i = 0; i < names.length; i++) {
            fileWriter.append("Модель: " + names[i] + " цена: " + prices[i] + "\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }
    @Override
    public Transport get() throws Exception {
        File file = new File("dao.txt");
        FileReader fileReader = new FileReader(file);
        Transport transport = null;
        int tmp = -1;
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        if (line.contains("мотоцикл")) {
            tmp = 1;
        } else {
            tmp = 0;
        }
        line = bufferedReader.readLine();
        String name = line.split(": ")[1];
        if (tmp == 0) {
            transport = new Car(name, 0);
        } else {
            transport = new Motorcycle(name, 0);
        }
        line = bufferedReader.readLine();
        int count = Integer.parseInt(line.split(": ")[1]);
        for (int i = 0; i < count; i++) {
            line = bufferedReader.readLine();
            transport.addModel(line.split(" ")[1], Double.parseDouble(line.split(" ")[3]));
        } 
        return transport;
    } 
}