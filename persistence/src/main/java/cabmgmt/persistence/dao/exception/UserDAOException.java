package cabmgmt.persistence.dao.exception;

public class UserDAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public UserDAOException(){
		super();
	}
	
	/**
	 * 
	 * @param msg
	 */
	public UserDAOException(String msg){
		super(msg);
	}
	
	/**
	 * 
	 * @param t
	 */
	public UserDAOException(Throwable t){
		super(t);
	}
	
	/**
	 * 
	 * @param msg
	 * @param t
	 */
	public UserDAOException(String msg, Throwable t){
		super(msg,t);
	}
}
