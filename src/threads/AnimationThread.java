package threads;

import javafx.application.Platform;
import model.ThreadStop;
import ui.AssistantGUI;

/** It's an animation thread to move figures in a gui.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class AnimationThread extends Thread implements ThreadStop{
	
	private AssistantGUI gui;
	private int leftMax,rightMax;
	private int x;
	private boolean direction;
	private boolean stop;
	private int  figure;
	
	/**	Constructor animation thread.
	 * 	This method creates a new animation thread.
	 * @param left 		contains the maximum value of the window to the left. Int, cann't be null.
	 * @param right 	contains the maximum value of the window to the right. Int, cann't be null.
	 * @param xPos 		contains the position value of the object. Int, cann't be null.
	 * @param gui 		contains the graphic user interface  where the animation thread it's supposed to work. AssistantGUI, cann't be null.
	 * @param option	contains the number of the new figure to know if it's the first, second, etc. Int, cann't be null.
	 * @return AnimationThread, an animation thread to move the figure(s) 
	 */
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
	
	
	/**	Run animation thread.
	 * 	This method runs/starts the thread.
	 */
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
