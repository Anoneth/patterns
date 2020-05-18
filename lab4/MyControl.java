package lab4;

import java.util.ArrayList;
import java.util.List;

public class MyControl {
    private List<MyControlListener> listeners = new ArrayList<>();

    public void addListener(MyControlListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MyControlListener listener) {
        listeners.remove(listener);
    }
    
    public void fireListeners() {
        for (MyControlListener listener : listeners) {
            listener.onDataChanged();
        }
    }
}