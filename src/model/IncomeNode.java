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
		//System.out.println("Entered");
		list.add(income);
		if(greaterNode != null) {
			list = greaterNode.realIncomes(list);
			
		}
		if(lowerNode != null) {
			list = lowerNode.realIncomes(list);
		}
		return list;
	}
	
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
}
