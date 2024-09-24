package p1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class P1Viewer extends Viewer implements PropertyChangeListener {

    public P1Viewer(MessageManager messageManager, int width, int height) {
        super(width, height);

        //Vi lägger till en lyssnare
        messageManager.addMessageListener(this);
    }

    @Override
    public void setMessage(Message message) {
        super.setMessage(message);
    }
    /*
    Denna metoden tar PropertyChangeEvent objekt som sin argument,
    metoden kollar först om the property of the even är "message",
    if true, då ska den hämta det nya värdet ac the property genom att använda
    getNewValue och lägger till Message objektet.

    setMessage anropas och message läggs in.

     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("message")) {

            Message message = (Message) evt.getNewValue();

            setMessage(message);
        }
    }
}




