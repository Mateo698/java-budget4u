package model;

/** Represents a generic user.
 * @author https://github.com/Mateo698
 * @author https://github.com/KennetSanchez 
 * @version 1.0
*/
public class UserGeneric extends User{
	
	private static final long serialVersionUID = 1;

	/*
	 * Constructor UserGeneric.
	 * This method creates a new generic user. 
	 * @param uname 	contains the name of the new user. String, not empty neither null.
	 * @param upass		contains the password of the new user. String, not empty neither null.
	 * @param utype		contains the type of user. TypesOfUser, cann't be null.
	 * @return UserGeneric, returns a new generic user.
	 */
	public UserGeneric(String uname, String upass, TypesOfUser utype) {
		super(uname, upass, utype);
	}

	@Override
	public String getName() {
		return super.name;
	}
	
	@Override
	public String getPassword() {
		return super.password;
	}
	
	@Override
	public TypesOfUser getType() {
		return super.type;
	}
}
