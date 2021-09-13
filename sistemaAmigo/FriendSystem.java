package sistemaAmigo;

import java.util.ArrayList;
import java.util.List;

public class FriendSystem {

	private List<Friend> friends;
	private List<Message> msg;
	private int maxMessages;

	public static final int MAX_MSG = 5000;

	public FriendSystem(int maxMessages) {
		this.friends = new ArrayList<>();
		this.msg = new ArrayList<>();
		this.maxMessages = maxMessages;
	}

	public FriendSystem() {
		this(MAX_MSG);
	}

	public List<Friend> getFriend() {
		return friends;
	}

	public List<Message> getMsg() {
		return msg;
	}

	public int getMaxMessages() {
		return maxMessages;
	}

	public List<Message> searchAllMessages() {
		return msg;
	}

	public void friendRegistration(String friendName, String friendEmail) throws ExceptionFriendAlreadyExists {

		Friend friend = new Friend(friendName, friendEmail, friendEmail);

		if (friend.getEmail().equalsIgnoreCase(friendEmail)) {
			this.friends.add(friend);
		} else {
			throw new ExceptionFriendAlreadyExists("Friend already exists!");
		}
	}

	public List<Message> searchingAnonymousMessages() {
		ArrayList<Message> anonymousMessages = new ArrayList<>();

		for (Message msgA : msg) {
			if (msgA.isAnonymous()) {
				anonymousMessages.add(msgA);
			}
		}
		return anonymousMessages;
	}

	public void configureSecretFriend(String personsEmail, String drawnFriendEmail) throws NonExistentFriendException {
		boolean friendFound = false;

		for (Friend f : friends) {
			if (f.getEmail().equalsIgnoreCase(personsEmail) || f.getFriendEmailDrawn().equalsIgnoreCase(drawnFriendEmail)) {
				friendFound = true;
			} else {
				throw new NonExistentFriendException("Friend not registered in the system");
			}
		}
	}

	public String searchSecretFriendOf(String personsEmail) throws NonExistentFriendException, ExceptionOfUndrawnFriend {
		boolean friendFound = false;

		for (Friend f : friends) {
			if (f.getEmail().equalsIgnoreCase(personsEmail)) {
				friendFound = true;
				if (f.getFriendEmailDrawn() == null) {
					throw new ExceptionOfUndrawnFriend("Friend is not configured");
				}
				return f.getFriendEmailDrawn();
			}
			throw new NonExistentFriendException("Friend not registered in the system");
		}
		return null;
	}

	public void sendMessageToEveryone(String text, String senderEmail, boolean isAnonymous) {
		this.msg.add(new MessageToEveryone(text, senderEmail, isAnonymous));
	}

	public void sendMessageToSomeone(String text, String senderEmail, boolean isAnonymous, String recipientEmail) {
		this.msg.add(new MessageToSomeone(text, senderEmail, isAnonymous, recipientEmail));
	}
	
	public List<Friend> allParticipants() throws NonExistentFriendException {
		List<Friend> friend = new ArrayList<Friend>();
		
		for (Friend f : friend) {
			System.out.println(f.getName());
		}
		
		throw new NonExistentFriendException("Non-existent friends");
	}
	
	public List<Friend> searchAllParticipants() {
		List<Friend> participants = friends;
		participants.addAll(friends);
		return participants;
	}
}
