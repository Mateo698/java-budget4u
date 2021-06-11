package model;

import java.io.Serializable;

public class Node implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
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
