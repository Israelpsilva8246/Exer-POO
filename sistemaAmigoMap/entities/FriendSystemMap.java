package model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistemaAmigo.ExceptionFriendAlreadyExists;
import sistemaAmigo.ExceptionOfUndrawnFriend;
import sistemaAmigo.Friend;
import sistemaAmigo.Message;
import sistemaAmigo.MessageToEveryone;
import sistemaAmigo.MessageToSomeone;
import sistemaAmigo.NonExistentFriendException;

public class FriendSystemMap {

	private Map<String, Friend> friends;
	private List<Message> msg;
	private int maxMessages;
	private Object friend;

	public static final int MAX_MSG = 5000;

	public FriendSystemMap(int maxMessages) {
		this.friends = new HashMap<>();
		this.msg = new ArrayList<>();
	}

	public FriendSystemMap() {
		this(MAX_MSG);
	}

	public Map<String, Friend> getFriends() {
		return friends;
	}

	public List<Message> getMsg() {
		return msg;
	}

	public int getMaxMessages() {
		return maxMessages;
	}

	public void friendRegistration(String friendName, String friendEmail) throws ExceptionFriendAlreadyExists {
		Friend friend = new Friend(friendName, friendEmail, friendEmail);

		if (this.friends.containsKey(friendEmail)) {
			throw new ExceptionFriendAlreadyExists("Friend already exists.");
		}
		this.friends.put(friendEmail, friend);
	}

	public Friend searchSecretFriend(String friendEmail) throws NonExistentFriendException {
		Friend friendFound = this.friends.get(friendEmail);

		if (friendFound == null) {
			throw new NonExistentFriendException("Friend not yet registered in the system");
		}

		return friendFound;
	}

	public String searchSecretFriendOf(String personEmail) throws NonExistentFriendException, ExceptionOfUndrawnFriend {

		Friend secretFriend = this.friends.get(personEmail);

		if (secretFriend.getFriendEmailDrawn() == null) {
			throw new ExceptionOfUndrawnFriend("There are no friends drawn.");
		}

		return secretFriend.getFriendEmailDrawn();
	}

	public void configureFriendSecret(String personEmail, String drawnFriendEmail) throws ExceptionOfUndrawnFriend {
		
		Friend drawnFriend = this.friends.get(drawnFriendEmail);
		
		if (drawnFriend == null) {
			throw new ExceptionOfUndrawnFriend("Friend was not drawn");
			
		}
		
		drawnFriend.setFriendEmailDrawn(drawnFriendEmail);
	}
	
	public void sendMessageToAll(String name, String email, boolean isAnonymous) {
		this.msg.add(new MessageToEveryone(name, email, isAnonymous));
	}
	
	public void sendMessageToSomeone(String text, String senderEmail, boolean isAnonymous, String recipientEmail) {
		this.msg.add(new MessageToSomeone(text, senderEmail, isAnonymous, recipientEmail));
	}
	
	public List<Message> searchAllMessages() {
		return this.msg;
	}
	
	public List<Message> searchAnonymousMessage() {
		List<Message> anonymousMessage = new ArrayList<>();
		
		for (Message msg : this.msg) {
			if (msg.isAnonymous()) {
				anonymousMessage.add(msg);
			}
		}
		return anonymousMessage;
	}
}
