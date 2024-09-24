package L6;

import java.util.Timer;
import java.util.TimerTask;

public class Exercise1 {
    private String[] strings;
    private long pause;
    private Timer timer;

    public Exercise1(String[] strings) {
        //din kod
    }

    public Exercise1(String[] strings, long pause) {
        //din kod
    }

    private class ToDo extends TimerTask {
        @Override
        public void run() {
            //din kod
        }
    }

    public static void main(String[] args) {
        //Uppgift 1a
        String[] strings = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};

        Exercise1 ex1 = new Exercise1(strings);

        //Uppgift 1b
        //String[] strings1 = {"Jag heter Rut", "Du heter Sven", "Han heter Ola"};
        //String[] strings2 = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};

        //Exercise1 ex1a = new Exercise1(strings1);
        //Exercise1 ex1b = new Exercise1(strings2);

        //Exercise1 ex1a = new Exercise1(strings1, 1500);
        //Exercise1 ex1b = new Exercise1(strings2, 750);
    }
}
