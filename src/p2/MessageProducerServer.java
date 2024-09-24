package p2;

import p1.MessageProducer;
import p1.MessageProducerInput;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
Klassen måste veta på vilken port den ska lyssna för att hantera
uppkopplingar. Servern måste också ha en referens till MessageProducerInput.
Servern utgörs troligen av ett antal klasser (kan vara inre klasser). Servern ska vara en iterativ server
som använder en tråd. Servern ska alltså hantera en klient i taget. Kommunikationen mellan
klient och server sker med objektströmmar, dvs ObjectInputStream.

Efter att klienten kopplat upp ska en MessageProducer-
implementering överföras till servern.
 */
public class MessageProducerServer extends Thread {
    private MessageProducerInput mpi;
    private int port;

    public MessageProducerServer(MessageProducerInput mpi, int port) {
        this.mpi = mpi;
        this.port = port;
    }
    public void startServer() {
        start();
    }

    // Metod som kör en server som lyssnar på en specifik port nummer,
    // accepterar inkommande anslutning från klienter, och läser en ArrayProducer objekt från varje klient.
    //
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                //väntar på inkommande anslutning från en klient, accepterar, och skapar en ny Socket objekt.
                Socket socket = serverSocket.accept();

                //Gör en ObjectInputStream objekt ois, som läser objekt från strömmen av socket.
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                //Läser in ArrayProducer objekt från input stream av the socket genom att använda ois.
                ArrayProducer ap = (ArrayProducer) ois.readObject();

                //lägger in ArrayProducer objektet med mpi (MessageProducerInterface), sedan stänger socket.
                mpi.addMessageProducer(ap);
                socket.close();
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
}

