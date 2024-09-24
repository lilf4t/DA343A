package L8.alarm_pcl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DemoAlarm implements PropertyChangeListener{
	public DemoAlarm(int ms) {
		AlarmThread at = new AlarmThread(ms);
		at.startAlarm();
		at.addAlarmListener(this);
	}

	public static void main(String[] args) {
		DemoAlarm da = new DemoAlarm(4000);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if( evt.getPropertyName().equals("alarm"))
			System.out.println("ALARM!");
	}
}