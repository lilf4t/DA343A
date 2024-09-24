package p1;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * TextfileProducer is a class that reads a "filename" in order to create a Message object.
 *
 * @author Fatima Kadum
 */

public class TextfileProducer implements MessageProducer {
    private Message[] messages;
    private int delay = 0;
    private int times = 0;
    private int size;
    private int currentIndex = -1;

    /**
     * This constructor is for creating an object of TextfileProducer that reads "filename.txt"
     * and creates a Message Object with its content.
     *
     * In order to create a Message Object, we need to be able to read the data in the textfile, therefore we shall use;
     * A BufferedReader (for effectiveness) InputStreamReader that reads from a
     * FileInputStreamer created using the filename parameter.
     * The UTF-8 character encoding is specified to ensure proper decoding of the file's contents.
     *
     * Times - the amount of times the message is shown.
     * Delay - delay between the messages.
     * Size - amount of messages in the sequence.
     *
     * The Message class contains a message and an icon. This pair of message and icon are added to the list of messages.
     *
     * @param filename - the path where the filename we want to read data from lies.
     */
    public TextfileProducer(String filename) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"))) {

            /*
             Parsing because they are declared using int, and using the object created from the BufferedReader,
             to read the contents of the file, in this case we use new.txt file;
             4 - times
             200 - delay
             10 - size
             */
            times = Integer.parseInt(br.readLine());
            delay = Integer.parseInt(br.readLine());
            size = Integer.parseInt(br.readLine());

            /*
            Create a new array of Message objects with a size of "size" in this case 10,
            and stores it in the variable "messages"
             */
            messages = new Message[size];

            /*
             Use the previously created object to use in a for loop
             to read each message from the file and store it in the array.
             */

            for(int i = 0; i < size; i++){
                messages[i] = new Message(br.readLine(), new ImageIcon(br.readLine()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the delay (ms) between every Message object.
     *
     * @return delay - the delay (ms) between every Message object.
     */
    @Override
    public int delay() {
        return delay;
    }

    /**
     * Returns the amount of times the message sequence is to be shown.
     *
     * @return times - amount of times the message sequence is to be shown.
     */
    @Override
    public int times() {
        return times;
    }

    /**
     * Returns amount of messages in the sequence. If the messages are empty, return 0, else, return size.
     *
     * @return size of messages in the sequence.
     */
    @Override
    public int size() {
        return (messages==null) ? 0 : messages.length;
        }

    /**
     * Returns the next Message object in the sequence using an index that loops through a list of messages,
     * if the size is 0, return null, else, return message[i]
     *
     * @return - next message object in the sequence.
     */
    @Override
    public Message nextMessage() {
        if(size()==0)
            return null;
        currentIndex = (currentIndex+1) % messages.length;
        return messages[currentIndex];
    }
}
