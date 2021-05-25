package model;

public class OutlayNode extends Node implements AddNode,CheckNode{
	private Outlay outlay;
	private OutlayNode greaterNode;
	private OutlayNode lowerNode;
	
	
	public OutlayNode(String id, Outlay o) {
		super(id);
		outlay = o;
		greaterNode = null;
		lowerNode = null;
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
		if(greaterNode.getOutlay().getCreationDate().compareTo(realNode.getOutlay().getCreationDate()) > 0) {
			greaterNode.addNode(realNode);
		}else {
			lowerNode.addNode(realNode);
		}
		
	}

	public Outlay getOutlay() {
		return outlay;
	}

	public void setOutlay(Outlay outlay) {
		this.outlay = outlay;
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

}
