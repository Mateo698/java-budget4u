package model;

import java.io.Serializable;

/** It's a checker. It checks when the pay date arrives
*@author https://github.com/Mateo698
*@author https://github.com/KennetSanchez
*@version 1.0
*/
public class OutlayChecker implements Serializable{
	
	private static final long serialVersionUID = 1;
	private OrdinaryOutlay outlay;
	private boolean checked;
	
	
	 /** Constructor OutlayChecker.
	 * This method creates a new outlayChecker
	 * @param o 	contains the outlay to check. Outlay, cann't be null.
	 * @return OutlayChecker, returns an outlay checker to let the program knows when an outlay should be discounted.
	 */
	public OutlayChecker(OrdinaryOutlay o) {
		setOutlay(o);
		checked = false;;
	}

	public OrdinaryOutlay getOutlay() {
		return outlay;
	}

	public void setOutlay(OrdinaryOutlay outlay) {
		this.outlay = outlay;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked() {
		if(checked) {
			checked = false;
		}else {
			checked = true;
		}
	}
}
