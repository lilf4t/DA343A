package p2;

import p1.Message;
import p1.Viewer;
/*
The purpose of this class is to display Message objects received from the server in a GUI.
With the help of MessageClient
 */
public class P2Viewer extends Viewer implements CallbackInterface {
    private MessageClient messageClient;


    //Adds the current object of P2Viewer which implements CallbackInterface as a listener to the MessageClient.
    public P2Viewer(MessageClient messageClient, int width, int height) {
        super(width, height);
        this.messageClient = messageClient;
        messageClient.addListener(this);
    }

    //Sets the Message for the Viewer using setMessage() of the Viewer class.
    @Override
    public void getMessage(Message msg) {
        super.setMessage(msg);
    }
}