package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserStudentTest {

UserStudent testUser = new UserStudent("name", "pass", TypesOfUser.STUDENT);
	
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
		assertEquals(TypesOfUser.GENERIC, userType);
	}

}
