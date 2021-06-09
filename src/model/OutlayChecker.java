package model;

import java.io.Serializable;

public class OutlayChecker implements Serializable{
	
	private static final long serialVersionUID = 1;
	private OrdinaryOutlay outlay;
	private boolean checked;
	
	public OutlayChecker(OrdinaryOutlay o) {
		setOutlay(o);
		checked = false;;
	}

	public Outlay getOutlay() {
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
