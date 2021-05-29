package model;

public class Node {
	private String identifier;
	private Node next;
	
	public Node(String id) {
		setIdentifier(id);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public void setNext(Node nextP) {
		next = nextP;
	}
	
	public Node getNext() {
		return next;
	}
}
