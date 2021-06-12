package model;

import java.io.Serializable;
import java.util.ArrayList;

/** Represent the income nodes.
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class IncomeNode extends Node implements AddNode,CheckNode,Serializable{
	private static final long serialVersionUID = 1;
	private Income income;
	private IncomeNode greaterNode;
	private IncomeNode lowerNode;
	
	/** Constructor income node.
	* This method creates a new income Node.
	* @param id 	contains the id of the node. String, cann't be empty neither null.
	* @param in 	contains the income of the node. Income, cann't be null.
	* @return IncomeNode, returns a new income node.
	*/
	public IncomeNode(String id, Income in) {
		super(id);
		income = in;
		greaterNode = null;
		lowerNode = null;
	}

	public IncomeNode getGreaterNode() {
		return greaterNode;
	}

	public void setGreaterNode(IncomeNode greaterNode) {
		this.greaterNode = greaterNode;
	}

	public IncomeNode getLowerNode() {
		return lowerNode;
	}

	public void setLowerNode(IncomeNode lowerNode) {
		this.lowerNode = lowerNode;
	}

	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
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

	
	 /** Add IncomeNode.
	 * This method adds a new node and link it with the higher or lower nodes if it's necessary. 
	 * @param n 	contains the new node to add. Node, cann't be null.
	 */
	@Override
	public void addNode(Node n) {
		IncomeNode realNode = (IncomeNode) n;
		if(income.getCreationDate().compareTo(realNode.getIncome().getCreationDate()) >= 0){
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
	
	/** Real incomes - first part.
	 * This method starts the conversion of the incomes into an array list. 
	 * @return list, an array list with the first income.
	 */
	public ArrayList<Income> realIncomes(){
		ArrayList<Income> list = new ArrayList<>();
		list.add(income);
		if(greaterNode != null) {
			list = greaterNode.realIncomes(list);
		}
		if(lowerNode != null) {
			list = lowerNode.realIncomes(list);
		}
		return list;
		
	}
	
	/** Real incomes - second part.
	 * This method continues/finish the conversion of the incomes into an array list. 
	 * @param list	contains an array list with other incomes. ArrayList, cann't be null..
	 * @return list, an array list with the incomes.
	 */
	public ArrayList<Income> realIncomes(ArrayList<Income> list){
		list.add(income);
		if(greaterNode != null) {
			list = greaterNode.realIncomes(list);
			
		}
		if(lowerNode != null) {
			list = lowerNode.realIncomes(list);
		}
		return list;
	}
	
	/** Search IncomeNode.
	* This method searches the requested income. 
	* @param ou 	contains the outlay contained in the node to search. Income cann't be null.
	* @return searched, returns the searched Income.
	*/
	public Income searchNode(Income in) {
		Income searched = null;
		if(in.getName() == income.getName()) {
			return income;
		}else {
			if(greaterNode != null) {
				searched = greaterNode.searchNode(in);
			}
			if(searched == null && lowerNode != null) {
				searched = lowerNode.searchNode(in);
			}
			return searched;
		}
	}
	
	/** Replace IncomeNode.
	 * This method replaces an income with a new one. 
	 * @param oldIncome 	contains the income to be replaced. Income, cann't be null..
	 * @param newIncome		contains the income to replace with. Income, cann't be null.
	 */
	public void replace(Income oldIncome, Income newInc) {
		if(income == oldIncome) {
			income = newInc;
		}else {
			if(greaterNode != null) {
				greaterNode.replace(oldIncome, newInc);
			}
			if(lowerNode != null) {
				lowerNode.replace(oldIncome, newInc);
			}
		}
	}
}
