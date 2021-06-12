package model;

import java.io.Serializable;
import java.util.ArrayList;

/** Represents the outlays node.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class OutlayNode extends Node implements AddNode,CheckNode,Serializable{
	private static final long serialVersionUID = 1;
	private Outlay outlay;
	private OutlayNode greaterNode;
	private OutlayNode lowerNode;
	
	
	/** Constructor OutlayNode.
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

	/** CheckNode.
	 * This method checks if a node is linked or not. 
	 * @return boolean, returns a boolean to let the program knows if the node it's linked or not.
	 */
	@Override
	public boolean checkNode() {
		if(greaterNode != null || lowerNode != null) {
			return true;
		}else {
			return false;
		}
	}

	
	/** Add OutlayNode.
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
	
	/** Real outlays - first part.
	 * This method starts the conversion of the outlays into an array list. 
	 * @return list, an array list with the first outlay.
	 */
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
	
	/** Real outlays - second part.
	 * This method continues/finish the conversion of the outlays into an array list. 
	 * @param list	contains an array list with other outlays. ArrayList, cann't be null..
	 * @return list, an array list with the outlays.
	 */
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
	
	
	 /** Search OutlayNode.
	 * This method searches the requested outlay. 
	 * @param ou 	contains the outlay contained in the node to search. Outlay cann't be null.
	 * @return searched, returns the searched Outlay.
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
	
	
	/** Replace OutlayNode.
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
