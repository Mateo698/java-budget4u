package model;

public class UserStudent extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	/*
	 * Constructor UserStudent.
	 * This method creates a new student user. 
	 * @param uname 	contains the name of the new user. String, not empty neither null.
	 * @param upass		contains the password of the new user. String, not empty neither null.
	 * @param utype		contains the type of user. TypesOfUser, cann't be null.
	 * @return UserStudent, returns a new student user.
	 */
	public UserStudent(String uname, String upass, TypesOfUser utype) {
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
