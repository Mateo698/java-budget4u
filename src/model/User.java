package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

public abstract class User {
	String name, password;
	long currentMoney;
	TypesOfUser type;
	Balance firstBalance;
	MoneyLender firstMoneyLender;
	IncomeNode firstIncome;
	OutlayNode firstOutlay;

	public User(String uname, String upass, TypesOfUser utype) {
		name = uname;
		password = upass;
		type = utype;
		firstIncome = null;
		firstOutlay = null;
		currentMoney = 0;
	}

	// ------------------------------------- Income code
	// ------------------------------

	public void createIncome(String name, long amount, Calendar cD, Calendar monthly) {
		RegularIncome in = new RegularIncome(name, amount, cD, monthly);
		addIncome(in);
	}

	public void createIncome(String name, long amount, Calendar cD, String purpose) {
		IrregularIncome in = new IrregularIncome(name, amount, cD, purpose);
		addIncome(in);
	}

	public void createIncome(String name, long amount, Calendar cD, MoneyLender lender, Calendar payDay) {
		Loan lo = new Loan(name, amount, cD, payDay, lender);
		addIncome(lo);
	}

	public void addIncome(Income income) {
		currentMoney += income.getAmount();
		if (firstIncome == null) {
			firstIncome = new IncomeNode(income.getName(), income);
		} else {
			IncomeNode newNode = new IncomeNode(income.getName(),income);
			firstIncome.addNode(newNode);
		}
	}

	public void removeIncome(Income income) {
		ArrayList<Income> realIncomes = getIncomes();
		if(realIncomes != null) {
			boolean leave = false;
			for (int i = 0; i < realIncomes.size() && !leave; i++) {
				if(realIncomes.get(i) == income) {
					realIncomes.remove(i);
					leave = true;
				}
			}
			Collections.sort(realIncomes);
			IncomeNode newFirst = new IncomeNode(realIncomes.get(0).getName(),realIncomes.get(0));
			for(int i=1;i<realIncomes.size();i++) {
				Node newNode = new IncomeNode(realIncomes.get(i).getName(),realIncomes.get(i));
				newFirst.addNode(newNode);
			}
			firstIncome =  newFirst;
		}
	}

	// ------------------------------------- Outlay code
	// ------------------------------

	// Ordinary
	public void createOutlay(String name, long amount, Calendar cD, Calendar monthly) {
		OrdinaryOutlay oo = new OrdinaryOutlay(name, amount, cD, monthly);
		addOutlay(oo);
	}

	// Home and extraordinary
	public void createOutlay(String name, long amount, Calendar cD, String purpose, int type) {
		switch (type) {
		case 1:
			HomeOutlay ho = new HomeOutlay(name, amount, cD, purpose);
			addOutlay(ho);
			break;
		case 2:
			ExtraordinaryOutlay eo = new ExtraordinaryOutlay(name, amount, cD, purpose);
			addOutlay(eo);
			break;
		}

	}

	public boolean removeOutlay(String name, long amount) {
		boolean removed = false;
		OutlayNode currentNode = firstOutlay;

		while (!removed) {
			if (currentNode.getOutlay().getName().equals(name) && currentNode.getOutlay().getAmount() == amount) {
				removed = true;
				OutlayNode prev = currentNode.getLowerNode();
				OutlayNode post = currentNode.getGreaterNode();

				if (currentNode.getGreaterNode() != null && currentNode.getLowerNode() == null) {
					currentNode.getGreaterNode().setLowerNode(null);
				} else if (currentNode.getLowerNode() != null && currentNode.getGreaterNode() == null) {
					currentNode.getLowerNode().setGreaterNode(null);
				} else {
					prev.setGreaterNode(post);
					post.setLowerNode(prev);
				}
				currentNode = null;
			}
			if (currentNode.getOutlay().getAmount() < amount) {
				currentNode = currentNode.getGreaterNode();
			} else {
				currentNode = currentNode.getLowerNode();
			}
		}

		return removed;
	}

	public void addOutlay(Outlay outlay) {
		currentMoney += outlay.getAmount();
		if (firstOutlay == null) {
			firstOutlay = new OutlayNode(outlay.getName(), outlay);
		} else {
			firstOutlay.addNode(firstOutlay);
		}
	}

	// ------------------------------------- getters ------------------------------
	public IncomeNode getIncomeNode() {
		return firstIncome;
	}

	public OutlayNode getOutlayNode() {
		return firstOutlay;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public TypesOfUser getType() {
		return type;
	}

	public long getMoney() {
		return currentMoney;
	}

	public ArrayList<MoneyLender> getMoneyLenders() {
		ArrayList<MoneyLender> list = new ArrayList<MoneyLender>();
		if (firstMoneyLender != null) {
			list.add(firstMoneyLender);
			MoneyLender aux = firstMoneyLender.getNext();
			while (aux != null) {
				list.add(aux);
				aux = aux.getNext();
			}
			return list;
		} else {
			return null;
		}
	}

	public ArrayList<Income> getIncomes() {
		if (firstIncome != null) {
			return firstIncome.realIncomes();
		} else {
			return null;
		}
	}
}
