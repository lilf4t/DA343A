package L8.alarm;

import java.util.LinkedList;

//har en tråd vilken skriver ut ”Nu är det dags att gå upp!” efter en viss tid.
public class AlarmThread {
	private Thread thread;
	private long ms;
	private LinkedList<AlarmListener> list = new LinkedList<AlarmListener>();

	public AlarmThread(long ms) {
		this.ms = ms;
	}

	public void startAlarm() {
		if(thread==null) {
			thread = new AT();
			thread.start();
		}
	}
	public void addAlarmListener(AlarmListener listener){
		if(listener != null)
			list.add(listener);
	}

	private class AT extends Thread {
		public void run() {
			try {
				Thread.sleep(ms);
			}catch(InterruptedException e) {

			}
			//ist för denna System.out.println("Nu är det dags att gå upp!");
			//listener.alarm();
			for(AlarmListener alarmListener : list) {
				//wake up!!
				alarmListener.alarm();
			}
			thread = null;
		}
	}
}