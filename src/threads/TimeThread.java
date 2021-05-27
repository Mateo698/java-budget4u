package threads;

import ui.AssistantGUI;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;

public class TimeThread extends Thread{

	AssistantGUI gui;
	LocalTime time;
	boolean stop;
	
	public TimeThread(AssistantGUI g) {
		gui =  g;
		time = LocalTime.now();
		stop = false;
	}
	
	@Override
	public void run() {
		while(!stop) {
			time = LocalTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
			String realTime = time.format(myFormatObj);
			Platform.runLater(new Thread() {
				@Override
				public void run() {
					gui.MAINPANEupdateTime(realTime);
				}
			});
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}