package p2;

import p1.Buffer;
import p1.Message;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This is a client class that receives Message-objects from MessageServer and sends it to listening classes.
 *
 * @author Fatima Kadum
 */

public class MessageClient {
    private ArrayList<CallbackInterface> callbackList;
    private Buffer<Message> messageBuffer;

    /**
     * The constructor takes two parameters: an IP address as a String and a port number as an integer.
     * These are used to establish a connection with a server with the help of the "Connection" inner class.
     * Also by setting up a list of callback functions, and creating a buffer to store messages.
     *
     * @param ip - IP address to a server.
     * @param port - Port where the server listens.
     */
    public MessageClient(String ip, int port) {
        new Connection(ip, port);
        //in order to add a listener
        callbackList = new ArrayList<CallbackInterface>();

        //This buffer is used to store messages that are received from the server.
        messageBuffer = new Buffer<Message>();
    }

    /**
     * This method adds a listener to the array of callback listeners. After the messages gets received by
     * the server in "Connection", the message gets send to every callback listener in the array
     *
     * @param listener -  Object of a class that implements CallbackInterface
     */
    public void addListener(CallbackInterface listener) {
        callbackList.add(listener);
    }

    /**
     * A private method that is used by a "Connection" thread to send the list of messages
     * that gets received by all listeners
     *
     * @param msg - Message that gets send to every listener
     */
    private void sendList(Message msg) {
        ////Iterator iterates over the callbackList of registered listeners
        Iterator<CallbackInterface> iterator = callbackList.iterator();
        //while there is a next listener,
        while (iterator.hasNext()) {
            //retreive next listener and send the message to the current listener.
            iterator.next().getMessage(msg);
        }
    }

    /**
     * This class is responsible for connecting to the server and continuously reading Message objects
     * from the server's ObjectInputStream.
     *
     * When a new Message is received, it adds the Message to the messageBuffer and sends
     * the Message to all registered listeners using the sendList() method.
     *
     * @author Fatima Kadum
     */
    private class Connection extends Thread {
        private String ip;
        private int port;
        public Connection(String ip, int port) {
            this.ip = ip;
            this.port = port;
            //start the thread
            start();
        }
        public void run() {
            try (Socket socket = new Socket(ip, port)) {
                //to read message objects
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                while (true) {
                    try {
                        Message msg = (Message) ois.readObject();
                        //adds message object in buffer
                        messageBuffer.put(msg);
                        //put message as a paramter of sendList()
                        sendList(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

