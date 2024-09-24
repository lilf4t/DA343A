package L6;

public class Exercise4a {
    private Activity thread;
    private String[] strings;
    private long pause;
    private int index = 0;

    public Exercise4a(String[] strings) {
        this(strings, 1000);
    }

    public Exercise4a(String[] strings, long pause) {
        this.strings = strings.clone();
        this.pause = pause;
    }

    public void start() {
        if (thread == null) {
            thread = new Activity();
            thread.start();
        }
    }

    public void stop() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    private class Activity extends Thread {
        public void run() {
            while (index < strings.length && !Thread.interrupted()) {
                System.out.println(strings[index]);
                index++;
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException e) {
                    break;
                }
            }
            thread = null;
        }
    }
    public static void main(String[] args) {
        String[] strings1 = {"Jag heter Rut", "Du heter Sven", "Han heter Ola"};
        String[] strings2 = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag",
                "Lördag", "Söndag"};
        Exercise4a ex4a = new Exercise4a(strings1);
        Exercise4a ex4b = new Exercise4a(strings2);
        ex4a.start();
        ex4b.start();
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {}
        ex4a.stop();
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {}
        ex4a.start();
        ex4b.stop();
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {}
        ex4b.start();
    }
}

