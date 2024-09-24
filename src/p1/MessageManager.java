package p1;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/*
 Meningen med den här klassen är att skapa objekt som ska hämta Message objekt ur bufferten,
 och se till att dessa visas i ett antal p1Viewer objekt.
 För att göra detta så måste P1Viewer bli notified om detta. detta gjordes genom PropertyChangeListener.

 Har thread för att kunna köra run() metoden

 Varför behöver P1Viewer bli notified?  MessageManager objekt känner inte till P1Viewer objekt
 men P1Viewer känner till MessageManager, man kan därför registrera en sorts lyssnare.

 changeSupport är en objekt av PropertyChangeSupport som används för att tala om för intressenter ifall det skett
 förändringar i Message objekt.

 */
public class MessageManager implements Runnable {
    private Thread thread;
    private Buffer<Message> messageBuffer;
    private Message message;
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public MessageManager(Buffer<Message> messageBuffer) {
        this.messageBuffer = messageBuffer;
    }

    /*
    Den här metoden låter andra objekt att vara lyssnare,
    så de kan bli notified när en property av aktuella objektet av en klass har ändrats.
     */
    public void addMessageListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    /*
    skapar o startar en ny tråd ifall det inte finns någon.
     */
    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    /*
    denna metoden körs när tråden startar. den hämtar meddelanden från messageBuffer, och skriver ut som text.
    metoden firePropertyChange() anropas för att berätta att message objekt propery har ändrats.
    andra parametern false innebär att message inte var tom innan. tredje argumentet är objektet som representerar
     en ny value av the property.
     */

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                message = messageBuffer.get();
                System.out.println(message.getText());

                changeSupport.firePropertyChange("message", false, message);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}




