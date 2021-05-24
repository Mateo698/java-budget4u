package model;

public abstract class User {
	String name, password;
	TypesOfUser type;
	
	public User(String uname, String upass, TypesOfUser utype) {
		name = uname;
		password = upass;
		type = utype;
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
}
