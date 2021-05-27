package model;

import java.util.ArrayList;

public class IncomeNode extends Node implements AddNode,CheckNode{
	private Income income;
	private IncomeNode greaterNode;
	private IncomeNode lowerNode;
	
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
		IncomeNode realNode = (IncomeNode) n;
		if(greaterNode.getIncome().getCreationDate().compareTo(realNode.getIncome().getCreationDate()) > 0) {
			greaterNode.addNode(realNode);
		}else {
			lowerNode.addNode(realNode);
		}
		
	}
	
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
}
