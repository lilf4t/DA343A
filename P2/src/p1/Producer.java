package p1;

/**
 *
 * This class represents a producer thread with the purpose of retrieving an object with a
 * MessageProducer implementation from a buffer (Buffer<MessageProducer>) with the method get();
 * and inserting them in another buffer (Buffer<Message>) with the method put();
 * There is supposed to be a delay between every object.
 *
 * @author Fatima Kadum
 */
public class Producer extends Thread {
    private Buffer<MessageProducer> producerBuffer;
    private Buffer<Message> messageBuffer;

    /**
     * Constructor to create Producer object
     *
     * @param producerBuffer - The buffer containing MessageProducer objects.
     * @param messageBuffer - The buffer that we want to put the object into.
     */
    public Producer(Buffer<MessageProducer> producerBuffer, Buffer<Message> messageBuffer) {
        this.producerBuffer = producerBuffer;
        this.messageBuffer = messageBuffer;
    }

    /**
     * start() method exists in the class Thread, so we call it by using "super".
     * The purpose of it is to start the thread.
     */
    @Override
    public synchronized void start() {
        super.start();
        }

    /**
     * The run() method exists in the Thread class. This code is designed to retrieve
     * messages from MessageProducer objects with the help of get() method
     * and store those messages in a buffer with put().
     * The sleep() method is used to simulate a delay between each message's being added to the buffer.
     *
     * There is a while loop to check if the thread has been interrupted, if it hasn't, the code is to be executed.
     * Inside the try block, the "mp" variable that was declared outside the loop is retreived from a buffer
     * containing MessageProducer objects using get().
     *
     * There is a nested for loop to loop through the MessageProducer object:
     * times (amount of times the message is shown)
     * and size (amount of messages in the sequence)
     * So we can get every message and put them into the Message buffer.
     *
     * mp.nextMessage() is called to get the next message from the MessageProducer object,
     * and that message is then put into the messageBuffer. The thread then sleeps for mp.delay() ms.
     *
     */
    @Override
    public void run() {
        MessageProducer mp;
        while(!Thread.interrupted()) {
            try{
                mp = producerBuffer.get();

                for(int times = 0; times < mp.times(); times++) {
                    for(int size = 0; size < mp.size(); size++) {

                        messageBuffer.put(mp.nextMessage());
                        Thread.sleep(mp.delay());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
