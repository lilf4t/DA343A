package p1;

import java.io.*;
//när man kör detta programmet med TestMessageProducer, så får man samma resultat som man fick när man körde ArrayProducer
public class ObjectfileProducer implements MessageProducer {
    private Message[] messages;
    private int delay = 0;
    private int times = 0;
    private int size;
    private int currentIndex = -1;

    public ObjectfileProducer(String filename) {
        /*
        Same as TextfileProducer, we also want to read a file, however this time, with objects. This is used for .dat files
        in this case.
         */
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {

            /*
             Reading objects and storing them in the variables
             */
            times = ois.readInt();
            delay = ois.readInt();
            size = ois.readInt();

             /*
            Create a new array of Message objects with a size of "size" in this case 10,
            and stores it in the variable "messages"
             */
            messages = new Message[size];

             /*
             Use the previously created object to use in a for loop
             to read each Message object from the file and store it in the array.
             */
            for(int i = 0; i < size; i++) {
                messages[i] = (Message) ois.readObject();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delay() {
        return delay;
    }

    @Override
    public int times() {
        return times;
    }

    @Override
    public int size() {
        if (messages == null)
            return 0;
        else {
            return messages.length;
        }
    }

    @Override
    public Message nextMessage() {
        if(size()==0)
            return null;
        currentIndex = (currentIndex+1) % messages.length;
        return messages[currentIndex];
    }
}
