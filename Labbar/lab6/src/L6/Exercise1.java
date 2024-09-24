package L6;

import java.util.Timer;
import java.util.TimerTask;


//ToDo: Lägg till en timer, när timern startats så ska ett antal strängar skrivas ut på konsolen med 1s mellanrum

public class Exercise1 {
    private String[] strings;
    private Timer timer;

    //denna konstruktorn ska ange att strings är 1000 = 1 sekund, alltså den anger hur lång mellanrum ska vara
    public Exercise1(String[] strings) {
        this.strings = strings;
        //skapa ny objekt av timer bc vi vill använda den för o göra vår delay
        timer = new Timer();
        //koden i run anropas efter 0 s (ingen delay i början) och sen varje 1 sekund
        timer.schedule(new ToDo(), 0, 1000);
    }

    private class ToDo extends TimerTask {
        private int i = 0;
        @Override
        public void run() {
            //kör sålänge de finns nåt i strings, else, så avslutar den räkna
            if( i < strings.length) {
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
