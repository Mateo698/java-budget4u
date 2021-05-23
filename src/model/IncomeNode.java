package model;

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
	public void addNode() {
		//PENDING
		
	}

	@Override
	public boolean checkNode() {
		// TODO Auto-generated method stub
		return false;
	}
}
