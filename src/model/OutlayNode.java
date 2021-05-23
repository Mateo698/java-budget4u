package model;

public class OutlayNode extends Node implements AddNode,CheckNode{
	private Outlay outlay;
	private OutlayNode greaterNode;
	private OutlayNode lowerNode;
	
	
	public OutlayNode(String id) {
		super(id);
		
	}

	@Override
	public boolean checkNode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addNode() {
		// TODO Auto-generated method stub
		
	}

}
