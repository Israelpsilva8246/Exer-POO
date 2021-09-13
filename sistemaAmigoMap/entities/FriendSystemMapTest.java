 package model.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistemaAmigo.ExceptionFriendAlreadyExists;
import sistemaAmigo.ExceptionOfUndrawnFriend;
import sistemaAmigo.Friend;
import sistemaAmigo.Message;
import sistemaAmigo.NonExistentFriendException;

class FriendSystemMapTest {
	
	FriendSystemMap sistema;
	
	@BeforeEach
	void setUp()  {
		this.sistema = new FriendSystemMap();
	}

	@Test
	void testFriendRegistration() {
		FriendSystemMap friendSystem = new FriendSystemMap();
		try {
			friendSystem.friendRegistration("Israel", "israel@gmail.com");
		} catch (ExceptionFriendAlreadyExists e) {
			fail("Não deveria ter lançado exceção");
		}
		
		try {
			friendSystem.friendRegistration("Israel", "israel@gmail.com");
			fail("Não deveria ter deixado cadastrar um amigo com o mesmo email");
		} catch (ExceptionFriendAlreadyExists e) {
			System.out.println("Ok");
		}
	}

	@Test
	void testSearchSecretFriend() {
		FriendSystemMap friendSystem = new FriendSystemMap();
		try {
			friendSystem.searchSecretFriend("jose@gmail.com");
			fail("Deveria falhar pois não existe ainda");
		} catch (NonExistentFriendException e) {
			System.out.println("Ok");
		}
	}

	@Test
	void testSearchSecretFriendOf() {
		FriendSystemMap friendSystem = new FriendSystemMap();
		try {
			sistema.friendRegistration("israel", "israel@gmail.com");
			sistema.friendRegistration("jose", "jose@gmail.com");
			sistema.configureFriendSecret("israel@gmail.com", "jose@gmail.com");
			sistema.configureFriendSecret("jose@gmail.com", "israel@gmail.com");
			sistema.searchSecretFriendOf("jose@gmail.com");
			System.out.println("Ok");
		} catch (NonExistentFriendException | ExceptionOfUndrawnFriend | ExceptionFriendAlreadyExists e) {
			fail("Não deveria lançar exceção");
		} 
	}

	@Test
	void testConfigureFriendSecret() {
		FriendSystemMap friendSystem = new FriendSystemMap();
		Friend f = new Friend("Israel", "israel@gmail.com", "jose@gmail.com");
		try {
			friendSystem.friendRegistration("Israel", "israel@gmail.com");
			friendSystem.configureFriendSecret("israel@gmail.com", "jose@gmail.com");
			fail("Não deveria ter configurado pois o email amigo não foi criado");
		} catch (ExceptionOfUndrawnFriend e) {
			System.out.println("ok");
		} catch (ExceptionFriendAlreadyExists e) {
			fail("Não deveria ter lançado a exceção");
		}
	}

	@Test
	void testSendMessageToAll() {
		assertTrue(sistema.searchAllMessages().isEmpty());
		sistema.sendMessageToAll("Israel", "israel@gmail.com", true);
		List<Message> mensagensAchadas = sistema.searchAllMessages();
		assertTrue(mensagensAchadas.size()==1);
		assertTrue(mensagensAchadas.get(0).getSenderEmail().equals("israel@gmail.com"));
	}

	@Test
	void testSendMessageToSomeone() {
		assertTrue(sistema.searchAllMessages().isEmpty());
		sistema.sendMessageToSomeone("texto 1", "israel@gmail.com", false, "jose@gmail.com");
		assertTrue(sistema.searchAnonymousMessage().isEmpty());
		sistema.sendMessageToSomeone("texto 2", "israel@gmail.com", true, "jose@gmail.com");
		assertTrue(sistema.searchAnonymousMessage().size()==1);
	}

	@Test
	void testSearchAllMessages() {
		assertTrue(sistema.searchAllMessages().isEmpty());
		sistema.sendMessageToSomeone("texto 1", "israel@gmail.com", false, "maria@gmail.com");
		assertTrue(sistema.searchAllMessages().size()==1);
		sistema.sendMessageToSomeone("texto 2", "israel@gmail.com", true, "maria@gmail.com");
		assertTrue(sistema.searchAllMessages().size()==2);
	}

	@Test
	void testSearchAnonymousMessage() {
		assertTrue(sistema.searchAllMessages().isEmpty());
		sistema.sendMessageToSomeone("texto 1", "israel@gmail.com", false, "maria@gmail.com");
		assertTrue(sistema.searchAnonymousMessage().isEmpty());
		sistema.sendMessageToSomeone("texto 2", "maria@gmail.com", true, "israel@gmail.com");
		assertTrue(sistema.searchAnonymousMessage().size()==1);
	}

}
