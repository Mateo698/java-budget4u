package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserGenericTest {

	UserGeneric testUser = new UserGeneric("name", "pass", TypesOfUser.GENERIC);
	
	@Test
	void testGetName() {
		String name = testUser.getName();
		assertEquals("name", name);
		fail("Error");
	}

	@Test
	void testGetPassword() {
		String pass = testUser.getPassword();
		assertEquals("pass", pass);
		fail("Not yet implemented");
	}

	@Test
	void testGetType() {
		TypesOfUser userType= testUser.getType();
		assertEquals(TypesOfUser.GENERIC, userType);
		fail("Not yet implemented");
	}
}
