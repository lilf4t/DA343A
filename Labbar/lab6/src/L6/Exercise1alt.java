package L6;

import java.util.Timer;
import java.util.TimerTask;


//Alt version, den gör samma sak som Exercise 1, fast med bara en konstruktor, den andra konstruktorn med long pause e
//egentligen så att man kan ha olika långa pauser mellan varje OM MAN VILL HA DE SÅ

public class Exercise1alt {
    private String[] strings;
    private long pause;
    private Timer timer;

    //denna konstruktorn ska ange att strings är 1000 = 1 sekund, alltså den anger hur lång mellanrum ska vara
    public Exercise1alt(String[] strings) {
        this(strings, 1000);
    }

    public Exercise1alt(String[] strings, long pause) {
        //clone kopierar objekt, antar att den tar värdet som finns i första konstruktorn???
        this.strings = strings.clone();
        this.pause = pause;

        //skapa ny objekt av timer bc vi vill använda den för o göra vår delay
        timer = new Timer();
        //koden i run anropas efter 0 s ( ingen delay i början) och sen varje 1 sekund
        timer.schedule(new ToDo(), 0, 1000);

    }

    private class ToDo extends TimerTask {
        private int i = 0;
        @Override
        public void run() {
            //kör sålänge de finns nåt i strings, else, så avslutar den räkna
            if(i < strings.length) {
                System.out.println(strings[i]);
                i++;
            }
            else {
                timer.cancel();
            }
        }
    }

    public static void main(String[] args) {
        //Uppgift 1a
        String[] strings = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};

        Exercise1alt ex1 = new Exercise1alt(strings);


        //Uppgift 1b
        //String[] strings1 = {"Jag heter Rut", "Du heter Sven", "Han heter Ola"};
        //String[] strings2 = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};

        //Exercise1 ex1a = new Exercise1(strings1);
        //Exercise1 ex1b = new Exercise1(strings2);

        //Exercise1 ex1a = new Exercise1(strings1, 1500);
        //Exercise1 ex1b = new Exercise1(strings2, 750);
    }
}
