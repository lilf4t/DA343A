package L6;

public class Exercise2c {
    private Activity thread;
    private String[] strings;
    private long pause;

    public Exercise2c(String[] strings) {
        //Din kod
    }

    public Exercise2c(String[] strings, long pause) {
        //Din kod
    }

    public void start() {
        //Din kod
    }

    private class Activity extends Thread {
        public void run() {
            //Din kod
        }
    }

    public static void main(String[] args) {
        //Uppgift 2c del 1
        String[] strings = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};

        Exercise2c ex2c = new Exercise2c(strings);

        //Din kod för att starta tråden

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

