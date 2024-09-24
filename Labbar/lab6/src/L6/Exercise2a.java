package L6;

public class Exercise2a extends Thread {
    private String[] strings;
    private long pause;
    private int index=0;

    public Exercise2a(String[] strings) {
        this(strings, 1000);
    }

    public Exercise2a(String[] strings, long pause) {
        this.strings = strings.clone();
        this.pause = pause;
    }

    public void run() {
        while( index < strings.length ) {
            System.out.println(strings[index]);
            index++;
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {}
        }
    }

    public static void main(String[] args) {
        //Uppgift 2a del 1
        String[] strings = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};

        Exercise2a ex2a = new Exercise2a(strings);

        //denna e viktig annars startar inget
        ex2a.start();

        //Uppgift 2a del 2
        //String[] strings1 = {"Jag heter Rut", "Du heter Sven", "Han heter Ola"};
        //String[] strings2 = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};

        //Exercise2a ex2a1 = new Exercise2a(strings1);
        //Exercise2a ex2a2 = new Exercise2a(strings2);

        //Exercise2a ex2a1 = new Exercise2a(strings1, 1500);
        //Exercise2a ex2a2 = new Exercise2a(strings2, 750);

    }
}

