package p2;

import p1.Message;
import p1.MessageProducer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
En MessageProducerClient ska kunna koppla upp mot en MessageProducerServer.
för att göra detta, måste den känna till ip-adress och uppkopplingsport till MessageProducerServer.

Klienten utgörs troligen av ett antal klasser (kan vara inre klasser).
Klienten ska koppla upp mot server, överföra en MessageProducer-implementering och sedan koppla ner förbindelsen.

 */
public class MessageProducerClient {
    private String ip;
    private int port;
    private ObjectOutputStream oos;
    private Message[] messages;

    public MessageProducerClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    /*
    Detta är en metod som skickar en samling av Messages som är implementerade av MessageProducer-objekt,
    tar det nödvändiga informationen från den, skapar en ArrayProducer objekt med den infon, och skickar över
    till en socket med en specific IP adress och port nummer.
     */
    public void send(MessageProducer mp) {
        int times = mp.times();
        int delay = mp.delay();
        int size = mp.size();

        //gör en objekt av Message[size] med size som parameter (för o ange storleken) ,
        // går in i en for loop för att fylla alla Message objekt med hjälp av nextMessage() metoden av mp objekt.
        messages = new Message[size];
        for(int i = 0; i < size; i++) {
            messages[i] = mp.nextMessage();
        }

        //Vi gör en ny Socket objekt med ip och port variable,
        // och skriver ArrayProducer objekt till outputstream av the socket,
        // flush() för att skicka över den, close() för att stänga kopplingen.
        try (Socket socket = new Socket(ip, port)) {
            oos = new ObjectOutputStream(socket.getOutputStream());

            //ArrayProducer är lämplig att använda för att överföra en MessageProducer-implementering.
            ArrayProducer apObject = new ArrayProducer(messages, times, delay);

            oos.writeObject(apObject);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
