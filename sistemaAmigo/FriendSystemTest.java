package sistemaAmigo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class FriendSystemTest {
	
	FriendSystem sistema;

	@Test
	void testFriendSystem() {
		assertTrue(sistema.searchAllMessages().isEmpty());
		assertThrows(ExceptionFriendAlreadyExists.class, 
				()-> sistema.searchSecretFriendOf("ayla@teste.com"));
	}

	@Test
	void testSearchAllMessages() {
		fail("Not yet implemented");
	}

	@Test
	void testFriendRegistration() {
		
	}

	@Test
	void testSearchingAnonymousMessages() {
		fail("Not yet implemented");
	}

	@Test
	void testConfigureSecretFriend() {
		fail("Not yet implemented");
	}

	@Test
	void testSearchSecretFriendOf() {
		fail("Not yet implemented");
	}

	@Test
	void testSendMessageToEveryone() {
		fail("Not yet implemented");
	}

	@Test
	void testSendMessageToSomeone() {
		fail("Not yet implemented");
	}

	@Test
	void testAllParticipants() {
		fail("Not yet implemented");
	}

	@Test
	void testSearchAllParticipants() {
		fail("Not yet implemented");
	}

}
