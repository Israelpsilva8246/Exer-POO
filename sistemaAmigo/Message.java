package sistemaAmigo;

public abstract class Message {
	
	private String text;
	private String senderEmail;
	private boolean anonymous;
	
	public Message(String text, String senderEmail, boolean anonymous) {
		this.text = text;
		this.senderEmail = senderEmail;
		this.anonymous = anonymous;
	}

	public String getText() {
		return text;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public boolean isAnonymous() {
		return anonymous;
	}
	
	public abstract String getFullTextToDisplay();
}
