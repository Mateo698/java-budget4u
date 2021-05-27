package model;

import java.util.ArrayList;
import java.util.Date;

public class Assistant {
	ArrayList<User> allUsers;
	IncomeNode firstIncome;
	IncomeNode lastIncome;
	
	OutlayNode firstOutlay;
	OutlayNode lastOutlay;
	
	
	
	public Assistant() {
		allUsers = new ArrayList<User>();
		firstIncome = null;
		firstOutlay = null;
		
		lastIncome = null;
		lastOutlay = null;
	}
	
	//------------------------------------------------------  Users code ------------------------------------------------------ 
	public boolean createUser(String name, String pass, TypesOfUser type) {
		boolean created = false;
		boolean repited = false;
		
		if(allUsers.size() == 0) {
			created = true;
		}
		for(int i = 0; i < allUsers.size() && !repited; i++) {
			if(allUsers.get(i).getName().equals(name)) {
				repited = true;
				created = false;
				
			}else {
				created = true;
			}
		}
		
		
		if(created && type.equals(TypesOfUser.EMPLOYEE)) {
			UserEmployee newUser = new UserEmployee(name, pass, type);
			allUsers.add(newUser);
		}else if(created && type.equals(TypesOfUser.STUDENT)) {
			UserStudent newUser = new UserStudent(name, pass, type);
			allUsers.add(newUser);
		}else if(created && type.equals(TypesOfUser.GENERIC)) {
			UserGeneric newUser = new UserGeneric(name, pass, type);
			allUsers.add(newUser);
		}
		
		return created;
	}
	
	public boolean login(String name, String pass) {
		boolean founded = false;
		
		for(int i = 0; i < allUsers.size() && !founded; i++) {
			if(allUsers.get(i).getName().equals(name) && allUsers.get(i).getPassword().equals(pass)) {
				founded = true;
			}
		}
		return founded;
	}
	
	//------------------------------------------------------  Income code ------------------------------------------------------ 
	
	//Works as a linked list.
	public void addIncome(User currentUser, long ammount, String name) {
		if(firstIncome == null) {
			String id = "1";
			Date currentDate = new Date();
			Income newIncome = new Income(name, ammount, currentDate);
			
			IncomeNode newNode = new IncomeNode(id, newIncome);
			lastIncome = newNode;
		}else {
			String id = 1+ (Integer.parseInt(lastIncome.getIdentifier())) + "";
			Date currentDate = new Date();
			Income newIncome = new Income(name, ammount, currentDate);
			
			IncomeNode newNode = new IncomeNode(id, newIncome);
			
			lastIncome = newNode;
		}
	}
	
}
