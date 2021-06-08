package model;

import java.io.Serializable;
import java.util.ArrayList;

public class OutlayNode extends Node implements AddNode,CheckNode,Serializable{
	private static final long serialVersionUID = 1;
	private Outlay outlay;
	private OutlayNode greaterNode;
	private OutlayNode lowerNode;
	
	public OutlayNode(String id, Outlay ou) {
		super(id);
		outlay = ou;
		greaterNode = null;
		lowerNode = null;
	}

	public OutlayNode getGreaterNode() {
		return greaterNode;
	}

	public void setGreaterNode(OutlayNode greaterNode) {
		this.greaterNode = greaterNode;
	}

	public OutlayNode getLowerNode() {
		return lowerNode;
	}

	public void setLowerNode(OutlayNode lowerNode) {
		this.lowerNode = lowerNode;
	}

	public Outlay getOutlay() {
		return outlay;
	}

	public void setIncome(Outlay outlay) {
		this.outlay = outlay;
	}

	@Override
	public boolean checkNode() {
		if(greaterNode != null || lowerNode != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void addNode(Node n) {
		OutlayNode realNode = (OutlayNode) n;
		if(outlay.getCreationDate().compareTo(realNode.getOutlay().getCreationDate()) >= 0){
			if(greaterNode != null) {
				greaterNode.addNode(realNode);
			}else {
				greaterNode = realNode;
			}
		}else {
			if(lowerNode != null) {
				lowerNode.addNode(realNode);
			}else {
				lowerNode = realNode;
			}
		}
		
	}
	
	public ArrayList<Outlay> realOutlays(){
		ArrayList<Outlay> list = new ArrayList<>();
		list.add(outlay);
		if(greaterNode != null) {
			list = greaterNode.realOutlays(list);
		}
		if(lowerNode != null) {
			list = lowerNode.realOutlays(list);
		}
		return list;
		
	}
	
	public ArrayList<Outlay> realOutlays(ArrayList<Outlay> list){
		//System.out.println("Entered");
		list.add(outlay);
		if(greaterNode != null) {
			list = greaterNode.realOutlays(list);
			
		}
		if(lowerNode != null) {
			list = lowerNode.realOutlays(list);
		}
		return list;
	}
	
	public Outlay searchNode(Outlay ou) {
		Outlay searched = null;
		if(ou.getName() == outlay.getName()) {
			return outlay;
		}else {
			if(greaterNode != null) {
				searched = greaterNode.searchNode(ou);
			}
			if(searched == null && lowerNode != null) {
				searched = lowerNode.searchNode(ou);
			}
			return searched;
		}
	}
	
	public void replace(Outlay oldOutlay, Outlay newOut) {
		if(outlay == oldOutlay) {
			outlay = newOut;
		}else {
			if(greaterNode != null) {
				greaterNode.replace(oldOutlay, newOut);
			}
			if(lowerNode != null) {
				lowerNode.replace(oldOutlay, newOut);
			}
		}
	}
}
