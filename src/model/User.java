package model;

import java.util.ArrayList;
import java.util.Calendar;
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

	// ------------------------------------- Income code ------------------------------

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
					long amount = realIncomes.get(i).getAmount();
					currentMoney -= amount;
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
	
	public ArrayList<Income> getIncomes() {
		if (firstIncome != null) {
			return firstIncome.realIncomes();
		} else {
			return null;
		}
	}
	
	public ArrayList<Income> getIncomeNameSorted(){
		ArrayList<Income> list = getIncomes();
		if(list != null) {
			 int n = list.size();
		        for (int i = 0; i < n-1; i++)
		            for (int j = 0; j < n-i-1; j++)
		                if (list.get(j).getName().compareTo(list.get(j+1).getName())>=0)
		                {
		                    Income temp = list.get(j);
		                    list.set(j,list.get(j+1));
		                    list.set(j+1, temp);
		                }
		}
		return list;
	}
	
	public ArrayList<Income> getIncomeAmountSorted(){
		ArrayList<Income> list = getIncomes();
		if(list != null) {
			int n = list.size();
			for (int i = 0; i < n-1; i++){
				int min_idx = i;
				for (int j = i+1; j < n; j++)
					if (list.get(j).getAmount() < list.get(min_idx).getAmount())
						min_idx = j;
				Income temp = list.get(min_idx);
				list.set(min_idx,list.get(i));
				list.set(i, temp);
			}
		}
        return list;
	}
	
	public ArrayList<Income> getIncomeDateSorted(){
		ArrayList<Income> list = getIncomes();
		try {
			int n = list.size();
	        for (int i = 1; i < n; ++i) {
	            Income key = list.get(i);
	            int j = i - 1;
	            while (j >= 0 && list.get(j).getCreationDate().compareTo(key.getCreationDate())>0) {
	                list.set(j + 1, list.get(j));
	                j = j - 1;
	            }
	            list.set(j+1, key);
	        }
	        
		}catch(NullPointerException n) {
			
		}
		return list;
	}
	
	public void editIncome(Income oldIncome,Income newInc) {
		currentMoney-= oldIncome.getAmount();
		currentMoney+= newInc.getAmount();
		if(firstIncome != null) {
			firstIncome.replace(oldIncome,newInc);
		}
	}

	// ------------------------------------- Outlay code ------------------------------

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

	//------------------------------------------------------  Loaner code ------------------------------------------------------ 
	
	public void createMoneyLender(String name, String lastName, String phone, User currentUser) {
		MoneyLender newMoneyLender = new MoneyLender(name, lastName, phone, currentUser);
		
		if(firstMoneyLender == null) {
			firstMoneyLender = newMoneyLender;
		}else {
			MoneyLender current = firstMoneyLender;
			
			while(current.getNext() != null){
				current = current.getNext();
			}			
			current.setNext(newMoneyLender);
		}
	}
	//------------------------------------------------------  Balance code ------------------------------------------------------ 
	
	
	
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

	
}
