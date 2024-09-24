package L9;

import java.util.LinkedList;

public class Controller {
    private StringBuffer buffer;

    public Controller(StringBuffer buffer) {
        this.buffer = buffer;
        new Messages().start();
    }
    private class Messages extends Thread {
        public void run() {
            String str;
            while(!Thread.interrupted()) {
                try {
                    str = buffer.get();
                    TextWindow.println(str);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
        public class StringBuffer {
          private LinkedList<String> buffer = new LinkedList<String>();
          public synchronized void put(String str) {
            buffer.addLast(str);
            notifyAll();
        }
        public synchronized String get() throws InterruptedException {
            while(buffer.size()==0) {
                wait();
            }
            return buffer.removeFirst();
        }
    }

    public class StringProducer extends Thread {
        private StringBuffer buffer;
        public StringProducer(StringBuffer buffer) {
            this.buffer = buffer;
        }
        public void run() {
            String[] arr = {"Hej","du","glade","tag","en","spade","..."};
            System.out.println("StringConsumer runs");
            for(String s : arr) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                buffer.put(s);
            }
        }
    }
    public class StringConsumer extends Thread {
        private StringBuffer buffer;

        public StringConsumer(StringBuffer buffer) {
            this.buffer = buffer;
        }
        public void run() {
            String str;
            System.out.println("StringConsumer runs");
            while(!Thread.interrupted()) {
                try {
                    str = buffer.get();
                    System.out.println(str);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

    }
}
