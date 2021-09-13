package sistemaAmigo;

public class Friend {

	private String name;
	private String email;
	private String friendEmailDrawn;

	public Friend() {
		this.name = "";
		this.email = "";
		this.friendEmailDrawn = "";
	}

	public Friend(String name, String email, String friendEmailDrawn) {
		this.name = name;
		this.email = email;
		this.friendEmailDrawn = friendEmailDrawn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFriendEmailDrawn() {
		return friendEmailDrawn;
	}

	public void setFriendEmailDrawn(String friendEmailDrawn) {
		this.friendEmailDrawn = friendEmailDrawn;
	}

}
