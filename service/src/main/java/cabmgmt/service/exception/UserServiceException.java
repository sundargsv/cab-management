package cabmgmt.service.exception;

public class UserServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public UserServiceException(){
		super();
	}
	
	/**
	 * 
	 * @param msg
	 */
	public UserServiceException(String msg){
		super(msg);
	}
	
	/**
	 * 
	 * @param t
	 */
	public UserServiceException(Throwable t){
		super(t);
	}
	
	/**
	 * 
	 * @param msg
	 * @param t
	 */
	public UserServiceException(String msg, Throwable t){
		super(msg,t);
	}
}
