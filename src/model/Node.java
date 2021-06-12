package model;

import java.io.Serializable;

/** It's the base class for the nodes.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class Node implements Serializable{
	
	private static final long serialVersionUID = 1;
	private String identifier;
	private Node next;
	
	
	/** Constructor Node.
	* This method creates a new Node.
	* @param id 	contains the id of the node. String, cann't be empty neither null.
	* @return Node, returns a new node.
	*/
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
