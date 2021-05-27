package model;

import java.util.ArrayList;
import java.util.Date;

public class Assistant {
	ArrayList<User> allUsers;
	
	public Assistant() {
		allUsers = new ArrayList<User>();		
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
	public void createIncome(User currentUser, long ammount, String name,int type,Date creation, Date monthly) {
		boolean found = false;
		for (int i = 0; i < allUsers.size() && !found; i++) {
			if(allUsers.get(i).getName().equals(currentUser.getName())) {
				allUsers.get(i).createIncome(name, ammount, type,creation,monthly);
				found = true;
			}
		}
	}
	

}
