package model;

public class Node {
	private String identifier;
	
	public Node(String id) {
		setIdentifier(id);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
}
