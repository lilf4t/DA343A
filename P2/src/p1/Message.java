package p1;

import javax.swing.*;
import java.io.Serializable;

// Message är en klass som innehåller data och Message-objekt.
// En message objekt har både text, och en icon (som kommer från klassen Icon från swing)
// Har metoden getText och getIcon
// Vi vill skapa Message-Objekt som används i tråd, implements Serializable då det
// överför tillståndet av ett objekt till byte-strömmar. Det behövs om den ska användas i tex ObjectfileProducer

public class Message implements Serializable {
    private String text;
    private Icon icon;

    public Message(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }
    public String getText() {
        return text;
    }
    public Icon getIcon() {
        return icon;
    }
}
