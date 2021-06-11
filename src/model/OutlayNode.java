package model;

import java.io.Serializable;
import java.util.ArrayList;

public class OutlayNode extends Node implements AddNode,CheckNode,Serializable{
	private static final long serialVersionUID = 1;
	private Outlay outlay;
	private OutlayNode greaterNode;
	private OutlayNode lowerNode;
	
	/*
	 * Constructor OutlayNode.
	 * This method creates a new outlay node. 
	 * @param id 	contains the identifier of the new node. String, not empty neither null.
	 * @param ou	contains the outlay of the node. Outlay, cann't be null.
	 * @return OutlayNode, returns a new outlay node.
	 */
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

	/*
	 * Add OutlayNode Generic.
	 * This method adds a new node and link it with the higher or lower nodes if it's necessary. 
	 * @param n 	contains the new node to add. Node, cann't be null.
	 */
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
		list.add(outlay);
		if(greaterNode != null) {
			list = greaterNode.realOutlays(list);
			
		}
		if(lowerNode != null) {
			list = lowerNode.realOutlays(list);
		}
		return list;
	}
	
	/*
	 * Search OutlayNode.
	 * This method searches the requested node. 
	 * @param ou 	contains the outlay contained in the node to search. Outlay cann't be null.
	 * @return searched, returns the searched outlayNode.
	 */
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
	
	/*
	 * Replace OutlayNode.
	 * This method replaces an outlay with a new one. 
	 * @param oldOut 	contains the outlay to be replaced. Outlay, cann't be null..
	 * @param newOut	contains the outlay to replace with. Outlay, cann't be null.
	 */
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
