package model.entities;

public class ExceptionFriendAlreadyExists extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExceptionFriendAlreadyExists(String msg) {
		super(msg);
	}
}
