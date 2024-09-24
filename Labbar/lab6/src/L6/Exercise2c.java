package L6;

//skillnaden jag kan se här är att vi låter inte klassen extenda thread, utan vi har klassen Activity som extendar thread
// som vi använder oss av genom variabeln thread, but like ser ingen skillnad? kanske olika sätt o skriva
// de e probably för vi ska lära oss utan o importera thread klasserna o sånttttt, uppgift 1 importera vi ex

public class Exercise2c {
    private Activity thread;
    private String[] strings;
    private int index = 0;
    private long pause;

    public Exercise2c(String[] strings) {
        this(strings, 1000);
    }

    public Exercise2c(String[] strings, long pause) {
        this.strings = strings.clone();
        this.pause = pause;
    }

    public void start() {
        if( thread == null ) {
            thread = new Activity();
            thread.start();
        }
    }

    private class Activity extends Thread {
        public void run() {
            while( index < strings.length ) {
                System.out.println(strings[index]);
                index++;
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException e) {}
            }
        }
    }

    public static void main(String[] args) {
        //Uppgift 2c del 1
        String[] strings = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};

        Exercise2c ex2c = new Exercise2c(strings);

        //Din kod för att starta tråden
        ex2c.start();

        //Uppgift 2c del 2
        //String[] strings1 = {"Jag heter Rut", "Du heter Sven", "Han heter Ola"};
        //String[] strings2 = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};

        //Exercise2c ex2c1 = new Exercise2c(strings1);
        //Exercise2c ex2c2 = new Exercise2c(strings2);

        //Exercise2c ex2c1 = new Exercise2c(strings1, 1500);
        //Exercise2c ex2c2 = new Exercise2c(strings2, 750);

        //Din kod för att starta tråden


        //Uppgift 4
//        try {
//            Thread.sleep(2000);
//        } catch(InterruptedException e) {}
//
//        ex2c.stop();
//
//        try {
//            Thread.sleep(2000);
//        } catch(InterruptedException e) {}
//
//        ex2c.start();
//        ex2c.stop();
//
//        try {
//            Thread.sleep(2000);
//        } catch(InterruptedException e) {}
//
//        ex2c.start();

    }
}

