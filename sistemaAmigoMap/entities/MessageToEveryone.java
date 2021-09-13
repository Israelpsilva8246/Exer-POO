package model.entities;

public class MessageToEveryone extends Message {
	
	public MessageToEveryone(String text, String senderEmail, boolean anonymous) {
		super(text, senderEmail, anonymous);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getFullTextToDisplay() {
		if(super.isAnonymous()) {
			return "message for everyone: " + getText();
		} 
		else {
			return "Message from: " + getSenderEmail() + " For all. "+ 
				"\nText: " + getText();
		}
	}
}
