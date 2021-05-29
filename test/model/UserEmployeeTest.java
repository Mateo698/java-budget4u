package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserEmployeeTest {

	UserEmployee testUser = new UserEmployee("name", "pass", TypesOfUser.EMPLOYEE);
	
	@Test
	void testGetName() {
		String name = testUser.getName();
		assertEquals("name", name);
	}

	@Test
	void testGetPassword() {
		String pass = testUser.getPassword();
		assertEquals("pass", pass);
	}

	@Test
	void testGetType() {
		TypesOfUser userType= testUser.getType();
		assertEquals(TypesOfUser.EMPLOYEE, userType);
	}
}
