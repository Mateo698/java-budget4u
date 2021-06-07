package threads;

import javafx.application.Platform;
import model.ThreadStop;
import ui.AssistantGUI;

public class AnimationThread extends Thread implements ThreadStop{
	
	private AssistantGUI gui;
	private int leftMax,rightMax;
	private int x;
	private boolean direction;
	private boolean stop;
	private int  figure;
	
	public AnimationThread(int left,int right,int xPos,AssistantGUI gui, int option) {
		leftMax = left;
		rightMax = right;
		this.gui = gui;
		x = xPos;
		direction = false;
		stop = false;
		figure = option;
	}
	
	
	public void setStop() {
		stop = true;
	}
	
	@Override
	public void run() {
		while(!stop) {
			if(direction) {
				if(x+1>=rightMax) {
					direction = false;
				}else {
					x+= 5;
				}
			}else {
				if(x-1<=leftMax) {
					direction = true;
				}else {
					x-= 5;
				}
			}
			Platform.runLater(new Thread() {
				@Override
				public void run() {
					gui.MAINMENUupdateBall(x, figure);;
				}
			});
			try {
				sleep(10);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
	}
}
