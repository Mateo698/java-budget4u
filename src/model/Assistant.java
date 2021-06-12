package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

/** This is the main class of this package. Contains the associations with the other classes so the responsibilities are properly separated. 
* @author https://github.com/Mateo698
* @author https://github.com/KennetSanchez
* @version 1.0
*/
public class Assistant{
	
	ArrayList<User> allUsers;
	private String NAME_FILE = "data/users.ickkck";
	
	
	 /** Constructor assistant.
	 * This method creates a new assistant, and initialize the array with all the users.
	 * @return Assistant, returns a new assistant with an empty array which contains all the registered users.
	 */
	public Assistant() {
		allUsers = new ArrayList<User>();		
	}
	
	//------------------------------------------------------  Users code ------------------------------------------------------ 
	
	
	/** Save data.
	 * This method saves and serialize the data of the users. 
	 */
	public void saveData() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NAME_FILE));
		oos.writeObject(allUsers);
	    oos.close();
	}
	
	
	 /** Load data.
	 * This method loads the data so the program "remembers" all the registered users. 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadData() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(NAME_FILE);
	    
	    if(f.exists()){
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      allUsers = (ArrayList) ois.readObject();
	      ois.close();
	    }
	    
	}
	
	
	 /** Create user.
	 * This method compares the name of two outlays, and picks which one should go first.. 
	 * @param name 	contains the name of the new user. String, cann't be empty neither null.
	 * @param pass	contains the password of the new user. String, cann't be empty neither null.
	 * @param type	contains the type of user. TypesOfUser, cann't be null.
	 * @return created, this boolean shows if the user was added or not.
	 */
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
	
	/** Login.
	 * This method searches an user, to verify it's name and password. Finally it decided if the user data was right or not. 
	 * @param name 	contains the name of the searched user. String, cann't be empty neither null.
	 * @param pass	contains the password of the searched user. String, cann't be empty neither null.
	 * @return founded, this boolean shows if the user data was right and if he can access or not.
	 */
	public boolean login(String name, String pass) {
		boolean founded = false;
		
		for(int i = 0; i < allUsers.size() && !founded; i++) {
			if(allUsers.get(i).getName().equals(name) && allUsers.get(i).getPassword().equals(pass)) {
				founded = true;
			}
		}
		return founded;
	}
	
	public User getUser(String name) {
		User u = null;
		boolean found = false;
		for (int i = 0; i < allUsers.size() && !found; i++) {
			if(allUsers.get(i).getName().equals(name)) {
				u = allUsers.get(i);
				found = true;
			}
		}
		return u;
	}
	
	//------------------------------------------------------  Income code ------------------------------------------------------ 
	
	/** Create a new income - regular.
	 * This method creates a new regular income. 
	 * @param currentUser 	contains the logged user. User, cann't be null.
	 * @param name			contains the name of the new income. String, cann't be empty neither null.
	 * @param amount		contains the amount of the new income. Long, cann't be null.
	 * @param creation		contains the creation date of the income. Calendar, cann't be null.
	 * @param monthly		contains the date when the income should be added again to the balance. Calendar, cann't be null.
	 */
	public void createIncome(User currentUser,String name,long ammount,Calendar creation, Calendar monthly) {
		currentUser.createIncome(name, ammount, creation, monthly);
	}
	
	/** Create a new income - irregular.
	 * This method creates a new irregular income. 
	 * @param currentUser 	contains the logged user. User, cann't be null.
	 * @param name			contains the name of the new income. String, cann't be empty neither null.
	 * @param amount		contains the amount of the new income. Long, cann't be null.
	 * @param creation		contains the creation date of the income. Calendar, cann't be null.
	 * @param purpose		contains the reason of this new income. String, cann't be empty neither null.
	 */
	
	public void createIncome(User currentUser, String name, long amount, Calendar creation, String purpose) {
		currentUser.createIncome(name, amount, creation, purpose);
	}
	
	/** Create a new income - loan.
	 * This method creates a new loan income. 
	 * @param currentUser 	contains the logged user. User, cann't be null.
	 * @param name			contains the name of the new income. String, cann't be empty neither null.
	 * @param amount		contains the amount of the new income. Long, cann't be null.
	 * @param creation		contains the creation date of the income. Calendar, cann't be null.
	 * @param lender		contains the money lender who lends the money. MoneyLender, cann't be null.
	 * @param payDay		contains the pay date of the loan. Calendar, cann't be null.
	 */
	public void createIncome(User currentUser, String name, long amount, Calendar creation, MoneyLender lender, Calendar payDay) {
		currentUser.createIncome(name, amount, creation, lender, payDay);
	}
	
	//------------------------------------------------------  Outlay code ------------------------------------------------------ 
	
	/** Create a new outlay - regular.
	 * This method creates a new regular outlay. 
	 * @param currentUser 	contains the logged user. User, cann't be null.
	 * @param name			contains the name of the new outlay. String, cann't be empty neither null.
	 * @param amount		contains the amount of the new outlay. Long, cann't be null.
	 * @param creation		contains the creation date of the outlay. Calendar, cann't be null.
	 * @param monthly		contains the date when this outlay should be discounted. Calendar, cann't be null.
	 */
	public void createOutlay(User currentUser, String name, long ammount, Calendar creation, Calendar monthly) {
		currentUser.createOutlay(name, ammount, creation, monthly);
	}
		
	/** Create a new outlay which can be irregular or a home outlay.
	 * This method creates a new irregular or home outlay. 
	 * @param currentUser 	contains the logged user. User, cann't be null.
	 * @param name			contains the name of the new outlay. String, cann't be empty neither null.
	 * @param amount		contains the amount of the new outlay. Long, cann't be null.
	 * @param creation		contains the creation date of the outlay. Calendar, cann't be null.
	 * @param purpose		contains the reason of this outlay. String, cann't be empty neither null.
	 * @param type			contains an integer which helps the program to know if it's an irregular or home outlay.
	 */
	public void createOutlay(User currentUser, String name, long amount, Calendar creation, String purpose, int type) {
		currentUser.createOutlay(name, amount, creation, purpose, type);
	}
	//------------------------------------------------------  MoneyLender code ------------------------------------------------------ 
	
	/** Create a new money lender.
	 * This method creates a new money lender. 
	 * @param currentUser 	contains the logged user. User, cann't be null.
	 * @param name			contains the name of the new money lender. String, cann't be empty neither null.
	 * @param amount		contains the last name of the new money lender. String, cann't be empty neither null.
	 * @param phone			contains the phone number of the money lender. String, cann't be empty neither null.
	 */
	public void createMoneyLender(User currentUser, String name, String lastName, String phone) {
		currentUser.createMoneyLender(name, lastName, phone, currentUser);
	}	
}
