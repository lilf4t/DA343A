package p1;

/*
Klassen används för att lägga till MessageProducer-objekt i en Buffert.
När metoden addMessageProducer anropas, så får MessageProducerInput-objekt tillgång till
en objekt av en klass som implementerar MessageProducer (ex TextfileProducer).
Objektet placeras i en buffert Buffer<MessageProducer> mha put();


Producer lägger till en objekt med put();
Consumer hämtar en objekt med get();
 */

public class MessageProducerInput {
    private Buffer<MessageProducer> producerBuffer;

    public MessageProducerInput(Buffer<MessageProducer> producerBuffer) {
        this.producerBuffer = producerBuffer;
    }

    public void addMessageProducer(MessageProducer m) {
        producerBuffer.put(m);
    }
}
