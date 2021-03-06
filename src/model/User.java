package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

/** Represents an user.
 * @author https://github.com/Mateo698
 * @author https://github.com/KennetSanchez 
 * @version 1.0
*/
public abstract class User implements Serializable{

	private static final long serialVersionUID = 1;
	String name, password;
	long currentMoney;
	TypesOfUser type;
	Balance firstBalance;
	MoneyLender firstMoneyLender;
	IncomeNode firstIncome;
	OutlayNode firstOutlay;
	Checker regularChecker;
	Calendar lastLogin;

	public User(String uname, String upass, TypesOfUser utype) {
		name = uname;
		password = upass;
		type = utype;
		firstIncome = null;
		firstOutlay = null;
		currentMoney = 0;
		regularChecker = new Checker();
		lastLogin = null;
	}

	// ------------------------------------- Income code ------------------------------

	public void createIncome(String name, long amount, Calendar cD, Calendar monthly) {
		RegularIncome in = new RegularIncome(name, amount, cD, monthly);
		regularChecker.addIncomeChecker(new IncomeChecker(in));
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
			if(realIncomes.size() == 0) {
				firstIncome = null;
			}else {
				Collections.sort(realIncomes);
				IncomeNode newFirst = new IncomeNode(realIncomes.get(0).getName(),realIncomes.get(0));
				for(int i=1;i<realIncomes.size();i++) {
					Node newNode = new IncomeNode(realIncomes.get(i).getName(),realIncomes.get(i));
					newFirst.addNode(newNode);
				}
				firstIncome =  newFirst;
			}
			
		}
		if(income instanceof RegularIncome) {
			RegularIncome in = (RegularIncome) income;
			regularChecker.removeIncome(in);
		}else if(income instanceof Loan) {
			Loan l = (Loan) income;
			regularChecker.removeLoan(l);
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
		if(oldIncome instanceof RegularIncome) {
			RegularIncome oldIn = (RegularIncome) oldIncome;
			RegularIncome newIn = (RegularIncome) newInc;
			regularChecker.editIncome(oldIn,newIn);
		}else if(oldIncome instanceof Loan) {
			Loan oldLoan = (Loan) oldIncome;
			Loan newLoan = (Loan) newInc;
			regularChecker.editLoan(oldLoan, newLoan);
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
			ExtraordinaryOutlay eo = new ExtraordinaryOutlay(name, amount, cD, purpose);
			addOutlay(eo);
			break;
			
		case 2:
			HomeOutlay ho = new HomeOutlay(name, amount, cD, purpose);
			addOutlay(ho);
			break;
		
		}

	}

	public void editOutlay(Outlay oldOutlay, Outlay newOut) {
		currentMoney-= oldOutlay.getAmount();
		currentMoney+= newOut.getAmount();
		if(firstOutlay != null) {
			firstOutlay.replace(oldOutlay,newOut);
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
			OutlayNode newNode = new OutlayNode(outlay.getName(), outlay);
			firstOutlay.addNode(newNode);
		}
	}

	public void removeOutlay(Outlay outlay) {
		ArrayList<Outlay> realOutlays = getOutlays();
		if(realOutlays != null) {
			boolean leave = false;
			for (int i = 0; i < realOutlays.size() && !leave; i++) {
				if(realOutlays.get(i) == outlay) {
					long amount = realOutlays.get(i).getAmount();
					currentMoney -= amount;
					realOutlays.remove(i);
					leave = true;
				}
			}
			if(realOutlays.size() == 0) {
				firstOutlay = null;
			}else {
				Collections.sort(realOutlays);
				OutlayNode newFirst = new OutlayNode(realOutlays.get(0).getName(),realOutlays.get(0));
				for(int i=1;i<realOutlays.size();i++) {
					Node newNode = new OutlayNode(realOutlays.get(i).getName(),realOutlays.get(i));
					newFirst.addNode(newNode);
				}
				firstOutlay =  newFirst;
			}
			
		}
	}
	
	public ArrayList<Outlay> getOutlays() {
		if (firstOutlay != null) {
			return firstOutlay.realOutlays();
		} else {
			return null;
		}
	}
	
	public ArrayList<Outlay> getOutlayNameSorted(){
		ArrayList<Outlay> list = getOutlays();
		if(list != null) {
			 int n = list.size();
		        for (int i = 0; i < n-1; i++)
		            for (int j = 0; j < n-i-1; j++)
		                if (list.get(j).getName().compareTo(list.get(j+1).getName())>=0)
		                {
		                	Outlay temp = list.get(j);
		                    list.set(j,list.get(j+1));
		                    list.set(j+1, temp);
		                }
		}
		return list;
	}
	
	public ArrayList<Outlay> getOutlayAmountSorted(){
		ArrayList<Outlay> list = getOutlays();
		if(list != null) {
			int n = list.size();
			for (int i = 0; i < n-1; i++){
				int min_idx = i;
				for (int j = i+1; j < n; j++)
					if (list.get(j).getAmount() < list.get(min_idx).getAmount())
						min_idx = j;
				Outlay temp = list.get(min_idx);
				list.set(min_idx,list.get(i));
				list.set(i, temp);
			}
		}
        return list;
	}
	
	public ArrayList<Outlay> getOutlayDateSorted(){
		ArrayList<Outlay> list = getOutlays();
		try {
			int n = list.size();
	        for (int i = 1; i < n; ++i) {
	        	Outlay key = list.get(i);
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
	
	public ArrayList<Balance> getBalances(){
		if(firstBalance != null) {
			return firstBalance.toArrayList();
		}else {
			return null;
		}
	}
	
	public void generateBalance(int month,int year) {
		if(!checkBalance(month, year)) {
			long incomesAmount = 0;
			long outlaysAmount = 0;
			long loansAmount = 0;
			ArrayList<Income> incomes = getIncomes();
			ArrayList<Outlay> outlays = getOutlays();
			for (int i = 0; i<incomes.size() ; i++) {
				if(incomes.get(i) instanceof RegularIncome) {
					incomesAmount += incomes.get(i).getAmount();
				}else if(incomes.get(i) instanceof Loan) {
					Loan aux = (Loan) incomes.get(i);
					if(aux.getPayDate().get(Calendar.MONTH) == month) {
						loansAmount += aux.getAmount();
					}
				}else {
					if(incomes.get(i).getCreationDate().get(Calendar.MONTH) == month) {
						incomesAmount += incomes.get(i).getAmount();
					}
				}
			}
			for (int i = 0; i < outlays.size(); i++) {
				if(outlays.get(i) instanceof OrdinaryOutlay) {
					outlaysAmount += outlays.get(i).getAmount();
				}else {
					if(outlays.get(i).getCreationDate().get(Calendar.MONTH) == month) {
						outlaysAmount += outlays.get(i).getAmount();
					}
				}
			}
			Calendar date = new GregorianCalendar(year,month,1);
			Balance balance = new Balance(incomesAmount,outlaysAmount,loansAmount,date);
			if(firstBalance != null) {
				balance.addBalance(balance);
			}else {
				firstBalance = balance;
			}
		}else {
			System.out.println("wtfBalance");
		}
		
		
	}
	
	public boolean checkBalance(int month, int year) {
		boolean created= false;
		ArrayList<Balance> balances = getBalances();
		if(balances != null) {
			for (int i = 0; i < balances.size(); i++) {
				if(balances.get(i).getMonth() == month && balances.get(i).getYear() == year) {
					created = true;
				}
			}
		}
		
		return created;
	}
	
	public boolean checkMonthBalance(int month) {
		boolean created = false;
		ArrayList<Balance> balances = getBalances();
		if(balances != null) {
			for (int i = 0; i < balances.size() && !created; i++) {
				if(balances.get(i).getMonth() == month) {
					created = true;
				}
			}
		}
		return created;
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
	
	public void dailyCheck(Calendar today) {
		if(today.get(Calendar.DAY_OF_MONTH) == 1)
		currentMoney += regularChecker.checkIncomes(today);
		currentMoney -= regularChecker.checkOutlays(today);
		currentMoney -= regularChecker.checkLoans(today);
	}

	public Calendar getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Calendar lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public void addML(String name, String last, String phone) {
		MoneyLender ml = new MoneyLender(name, last, phone, this);
		if(firstMoneyLender != null) {
			firstMoneyLender.addMoneyLender(ml);
		}else {
			firstMoneyLender = ml;
		}
	}
	
}
