package cabmgmt.feature.exception;

public class AdminFeatureException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public AdminFeatureException(){
		super();
	}
	
	/**
	 * 
	 * @param msg
	 */
	public AdminFeatureException(String msg){
		super(msg);
	}
	
	/**
	 * 
	 * @param t
	 */
	public AdminFeatureException(Throwable t){
		super(t);
	}
	
	/**
	 * 
	 * @param msg
	 * @param t
	 */
	public AdminFeatureException(String msg, Throwable t){
		super(msg,t);
	}
}
