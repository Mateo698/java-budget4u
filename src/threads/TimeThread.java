package threads;

import ui.AssistantGUI;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import model.ThreadStop;

/** It's a thread to show the current time always updated.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class TimeThread extends Thread implements ThreadStop{

	AssistantGUI gui;
	LocalTime time;
	boolean stop;
	
	
	 /** Constructor time thread.
	 * This method creates a new thread to show the current time always updated.
	 * @param g contains the gui where the thread it's supposed to work. AssistantGUI cann't be null.
	 * @return TimeThread, returns a time thread to show the time updated.
	 */
	public TimeThread(AssistantGUI g) {
		gui =  g;
		time = LocalTime.now();
		stop = false;
	}
	
	public void setStop() {
		stop = true;
	}
	
	/** Run time thread.
	 * This method starts/runs the thread.
	 */
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
