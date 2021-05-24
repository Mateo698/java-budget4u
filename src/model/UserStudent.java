package model;

public class UserStudent extends User{

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
