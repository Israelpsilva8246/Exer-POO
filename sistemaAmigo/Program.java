package sistemaAmigo;

import java.util.List;

public class Program {

	public static void main(String[] args) throws Exception {

		FriendSystem fs = new FriendSystem(5000);

		fs.friendRegistration("Jose", "jose@gmail.com");
		fs.friendRegistration("Maria", "maria@gmail.com");

		try {
			fs.configureSecretFriend("jose@gmail.com", "maria@gmail.com");
			fs.configureSecretFriend("maria@gmail.com", "jose@gmail.com");
			System.out.println("Successful!");
		} catch (NonExistentFriendException e) {
			System.out.println(e.getMessage());
		}

		fs.sendMessageToSomeone("Bom dia amigo", "Jose@gmail.com", true, "Maria@gmail.com");
		fs.sendMessageToEveryone("Bom dia amigos", "Maria@gmail.com", true);

		List<Message> msgsA = fs.searchingAnonymousMessages();

		for (Message msgA : msgsA) {
			System.out.println(msgA.getFullTextToDisplay());
		}

		try {
			fs.searchSecretFriendOf("jose@gmail.com");
			System.out.println("OK");
		} catch (ExceptionOfUndrawnFriend e) {
			System.out.println(e.getMessage());
		} catch (NonExistentFriendException e) {
			System.out.println(e.getMessage());
		}
	}
}
