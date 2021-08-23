package observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private static Subject subject = null;

    List<Observer> observers = new ArrayList<>();

    public static Subject getInstance()
    {
        if (subject == null)
            subject = new Subject();

        return subject;
    }
    public void addObserver(Observer obs){
        observers.add(obs);
    }

    public void removeObserver(Observer obs){
        observers.remove(obs);
    }

    public void notifyObs(){
        for(Observer observer : observers){
            observer.update();
        }
    }
}
