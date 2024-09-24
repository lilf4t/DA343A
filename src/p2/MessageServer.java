package p2;

/*
 MessageManager ska överföra Message-implementeringar  till MessageServer på samma sätt som
 Message-implementeringarna överförs till P1Viewer-objekten i P1.
 MessageServer och MessageClient ska kommunicera via objektströmmar
 (ObjectInputStream resp ObjectOutputStream).
 */

import p1.Buffer;
import p1.Message;
import p1.MessageManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 * This class represents a server that deals with communication between the client and the server by sending messages.
 * This is done by using a multithreaded server, where every connected client is handled by a thread. (run methods)
 * MessageServer gets Message-objects from MessageManager, and sends them over to MessageClient-object through TCP.
 *
 * The variable from MessageManager is used to deal with the incoming messsages from the client.
 * We also have to have a port,so we can decide which port the server will listen to for connection.
 * ClientHandler contains an ArrayList of ClientHandler object.
 *
 * @author Fatima Kadum
 */

public class MessageServer implements PropertyChangeListener {
    private MessageManager messageManager;
    private Buffer<Message> buffer = new Buffer<>();
    private int port;
    private ArrayList<ClientMessageHandler> clientMessage;
    private Thread thread;

    /**
     * Constructs a MessageServer object that listens for incoming connections on the specified port,
     * using a MessageManager object to manage messages.
     *
     * the constructor registers the MessageServer object as a listener for
     * incoming messages, and starts a new thread to listen for incoming connections.
     *
     * @param messageManager - the MessageManager object to use for managing messages
     * @param port           -  the port number to listen on for incoming connections
     */
    public MessageServer(MessageManager messageManager, int port) {
        this.messageManager = messageManager;
        this.port = port;
        clientMessage = new ArrayList<>();

        // Register this object as a listener for incoming messages
        messageManager.addMessageListener(this);

        // Start a new thread to listen for incoming connections
        thread = new Connection(port);
        thread.start();
    }

    /**
     * Sends a message to all connected clients from MessageManager with a for each loop that
     * loops through the client list.
     *
     * @param message - the Message object to send
     */
    public void sendMessage(Message message) {
        for (ClientMessageHandler cmh : this.clientMessage) {
            cmh.put(message);
        }
    }

    /**
     * This method is called whenever a property of the observed object changes.
     * It takes in a PropertyChangeEvent and sends the new value of the property
     * as a Message using the sendMessage() method.
     *
     * @param event - A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        // Get the new value of the property as a Message object
        Message message = (Message) event.getNewValue();
        // Send the message using the sendMessage() method
        sendMessage(message);
    }

    /**
     * An inner class of the MessageServer class responsible for establishing connections with clients
     * by using a run() method that is called when a new thread of the "Connection" class is started.
     *
     * @author Fatima Kadum
     */

    private class Connection extends Thread {
        private int port;

        /**
         * Constructor for the Connection class, takes an integer parameter "port", which represents the port
         * number on which the server socket will listen for incoming connections.
         *
         * @param port - the port number to listen on for incoming connections
         */
        public Connection(int port) {
            this.port = port;
        }

        /**
         * This method is responsible for listening for incoming connections and processing them.
         *
         * a "ServerSocket" object is created using the specified port number.
         * A "while" loop is used to continuously listen for incoming connections.
         *
         * When a client connects, the "accept()" method of the "ServerSocket" object is called,
         * which blocks the thread until a client connects to the server.
         * Once a client connects, a new "Socket" object is created to handle the communication with that client.
         *
         * A new "ClientMessageHandler" object is created with the "Socket" object as a parameter.
         * This object is responsible for handling the messages sent by the client.
         * The "start()" method is called on the "ClientMessageHandler" object to start a new thread for that client.
         *
         * Finally, the "ClientMessageHandler" object is added to the "clientMessage"
         * list of the outer "MessageServer" object using the "add()" method.
         */
        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                while (true) {
                    try {
                        //When a client connects, the "accept()" method of the "ServerSocket" object is called,
                        Socket socket = serverSocket.accept();

                        //A new "ClientMessageHandler" object is created with the "Socket" object as a parameter.
                        ClientMessageHandler cmh = new ClientMessageHandler(socket);
                        //starts a new thread for this client.
                        cmh.start();
                        //cmh object is added to the ClientMessageHandler list (using clientMessage)
                        clientMessage.add(cmh);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This inner class is responsible for sending messages back to a single client over its socket connection,
     * and the "put()" method provides an interface for the outer "MessageServer" class to send messages
     * to this client through this inner class.
     *
     * @author Fatima Kadum
     */
    private class ClientMessageHandler extends Thread {
        private Socket socket;
        private ObjectOutputStream oos;

        /**
         * Constructor for ClientMessageHandler objects. a "Socket" object to represent the client socket
         *
         * @param socket - socket object to represent the client socket.
         */
        public ClientMessageHandler(Socket socket) {
            this.socket = socket;
        }

        /**
         * This method initializes the "outputStream" instance variable with an "ObjectOutputStream"
         * object created from the "socket.getOutputStream()" method. This output stream will be
         * used to send messages back to the client.
         *
         */

        public void run() {
            try {
                oos = new ObjectOutputStream(socket.getOutputStream());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /**
         * This method of the class takes a "Message" object as a parameter and writes it to the
         * "outputStream" object using the "writeObject()" method. It then flushes the stream
         *
         * @param message -  message object to send to the client
         */
        public void put(Message message) {
            try {
                oos.writeObject(message);
                oos.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


