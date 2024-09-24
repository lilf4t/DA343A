package p1;
/*
 MessageProducer är en interface som definierar funktionalitetet för Message-Objekt
 bla vill vi hantera en sekvens av message objekt med size() och nextMessage()
 times - hur många gånger sekvensen ska visas med times()
 delay - hur lång tid varje message objekt ska visas med delay()

 med dessa metoder kan man skapa en storts animation

 */

public interface MessageProducer {
	public int delay();
	public int times();
	public int size();
	public Message nextMessage();
	
	default void info() {
		System.out.println("times="+times()+", delay="+delay()+", size="+size()+"]");
	}
}
