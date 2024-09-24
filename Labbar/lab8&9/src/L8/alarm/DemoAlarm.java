package L8.alarm;
//skapar en instans av AlarmThread och startar tråden genom anrop till metoden startAlarm()
public class DemoAlarm implements AlarmListener {
	public DemoAlarm(int ms) {
		AlarmThread at = new AlarmThread(ms);
		at.startAlarm();
		// instansen av DemoAlarm ska registeras i AlarmThread genom anrop till addAlarmListener-metoden (med referens this).
		at.addAlarmListener(this);
		//Lägg till en rad i DemoAlarm vilken registrerar en instans av WakeUpPrinter i AlarmThread.
		at.addAlarmListener(new WakeUpPrinter());
	}

	// När man kör denna metoden så skrivs det bara ut WAKE UP! men inte ALARM!
	// det beror på att instansvariabeln listener endast kan hålla referens till en lyssnare åt gången.
	// man kan fixa det genom att göra en arraylist

	private class WakeUpPrinter implements AlarmListener {

		@Override
		public void alarm() {
			System.out.println("WAKE UP!");
		}
	}

	public static void main(String[] args) {
		DemoAlarm da = new DemoAlarm(4000);
	}

	@Override
	public void alarm() {
		System.out.println("ALARM!");
	}
}
