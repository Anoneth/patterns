package lab4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class Model implements TableModel {
    private double[] x;
    private double[] y;
    private MyControl myControl = new MyControl();
    private List<TableModelListener> tableModelListeners = new ArrayList<>();

    public Model() {
        x = new double[] { };
        y = new double[] { };
    }

    private double func(double x) {
        return (double) Math.cos(x);
    }

    public void setX(double[] x) {
        this.x = x;
        y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] = func(x[i]);
        }
    }

    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    public void addListener(MyControlListener listener) {
        myControl.addListener(listener);
    }

    public void removeListener(MyControlListener listener) {
        myControl.removeListener(listener);
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "x";
            case 1:
                return "y";
            default:
                return "";
        }
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        tableModelListeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        tableModelListeners.remove(l);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return columnIndex == 0 ? x[rowIndex] : y[rowIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            x[rowIndex] = (double) aValue;
            Arrays.sort(x);
            updateY();
            myControl.fireListeners();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public int getRowCount() {
        return x.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }

    public void addRow() {
        double[] t = Arrays.copyOf(x, x.length + 1);
        x = t;
        x[x.length - 1] = 0;
        t = Arrays.copyOf(y, y.length + 1);
        y = t;
        Arrays.sort(x);
        updateY();
        myControl.fireListeners();
    }

    public void removeRow() {
        if (x.length > 0) {
            double[] t = Arrays.copyOf(x, x.length - 1);
            x = t;
            t = Arrays.copyOf(y, y.length - 1);
            y = t;
            myControl.fireListeners();
        }
    }

    private void updateY() {
        for (int i = 0; i < x.length; i++) {
            y[i] = func(x[i]);
        }
    }
}