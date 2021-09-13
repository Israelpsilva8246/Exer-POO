package sistemaAmigo;

import java.util.List;

import javax.swing.JOptionPane;

public class TestFriendSystemGUI {
	public static void main(String[] args) {
		int maxMsg = Integer.parseInt(JOptionPane.showInputDialog("How many messages?"));
		FriendSystem system = new FriendSystem(maxMsg);
		
		int qtdOfParticipants = Integer.parseInt(JOptionPane.showInputDialog("Quantity of participants?"));
		
		for (int i = 0; i < qtdOfParticipants; i++) {
			String friendName = JOptionPane.showInputDialog("Qual o seu nome?");
			String fiendEmail = JOptionPane.showInputDialog("Qual o seu email?");
			
			try {
				system.friendRegistration(friendName, fiendEmail);
			}
			catch (ExceptionFriendAlreadyExists e) {
				JOptionPane.showMessageDialog(null, "It is not possible to register");
				e.printStackTrace();
			}
		}
		
		List<Friend> allParticipants = system.searchAllParticipants();
		for (Friend a : allParticipants) {
			String drawnEmail = JOptionPane.showInputDialog("Who is the friend of? " + a.getEmail());
			
			try {
				system.configureSecretFriend(a.getEmail(), drawnEmail);
			} catch (NonExistentFriendException e) {
				JOptionPane.showMessageDialog(null, "Problem found");
				e.printStackTrace();
			}
		}
		
		String text = JOptionPane.showInputDialog("What is the text of the message? ");
		String emailAddress = JOptionPane.showInputDialog("What is your email registered in the system? ");
		String messageIsAnonymous = JOptionPane.showInputDialog("Is the message anonymous? Yes (Y) or No (N)?");
		boolean isAnonymous;
		
		if (messageIsAnonymous.equalsIgnoreCase("S")) {
			isAnonymous = true;
		} else {
			isAnonymous = false;
		}
		
		system.sendMessageToEveryone(text, emailAddress, isAnonymous);
		
	}

	
	
}
