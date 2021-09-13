package model.entities;

public class MessageToSomeone extends Message {
	
	private String addresseeEmail;

	public MessageToSomeone(String text, String senderEmail, boolean anonymous, String recipientEmail) {
		super(text, senderEmail, anonymous);
		this.addresseeEmail = recipientEmail;
	}

	public String getAddresseeEmail() {
		return addresseeEmail;
	}

	@Override
	public String getFullTextToDisplay() {
		if(super.isAnonymous()) {
			return "message for everyone: " + getText();
		} 
		else {
			return "Message from: " + getSenderEmail() + 
				"\nFor: " + getAddresseeEmail() + 
				"\nText: " + getText();
		}
	}
	
}
