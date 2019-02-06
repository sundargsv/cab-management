package cabmgmt.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

import cabmgmt.rest.domain.ResponseMessage;

/**
 * 
 * @author Sundar Gsv
 *
 */
public abstract class AbstractRESTController {
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	protected ResponseEntity<ResponseMessage> ok(String message){
		
		ResponseMessage body = new ResponseMessage();
		
		body.setMessage(message);
		return ok(body);
	}
	
	/**
	 * 
	 * @param body
	 * @return
	 */
	protected <T>ResponseEntity<T> ok(T body){
		return buildResponseEntity(body, HttpStatus.OK);
	}

	
	/**
	 * 
	 * @param message
	 * @return
	 */
	protected ResponseEntity<ResponseMessage> internalServerError(String message){
		
		ResponseMessage body = new ResponseMessage();
		
		body.setMessage(message);
		return internalServerError(body);
	}
	
	/**
	 * 
	 * @param body
	 * @return
	 */
	protected <T>ResponseEntity<T> internalServerError(T body){
		return buildResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	protected ResponseEntity<ResponseMessage> badRequestError(String message){
		
		ResponseMessage body = new ResponseMessage();
		
		body.setMessage(message);
		return badRequestError(body);
	}
	
	/**
	 * 
	 * @param body
	 * @return
	 */
	protected <T>ResponseEntity<T> badRequestError(T body){
		return buildResponseEntity(body, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param body
	 * @param status
	 * @return
	 */
	private <T>ResponseEntity<T> buildResponseEntity(T body, HttpStatus status){
		BodyBuilder builder = ResponseEntity.status(status);
		
		return builder.body(body);
	}
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	protected ResponseEntity<ResponseMessage> unauthorizedError(String message){
		
		ResponseMessage body = new ResponseMessage();
		
		body.setMessage(message);
		return unauthorizedError(body);
	}
	
	/**
	 * 
	 * @param body
	 * @return
	 */
	protected <T>ResponseEntity<T> unauthorizedError(T body){
		return buildResponseEntity(body, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * 
	 * @param message
	 * @return
	 */
	protected ResponseEntity<ResponseMessage> notFoundError(String message){
		
		ResponseMessage body = new ResponseMessage();
		
		body.setMessage(message);
		return notFoundError(body);
	}
	
	/**
	 * 
	 * @param body
	 * @return
	 */
	protected <T>ResponseEntity<T> notFoundError(T body){
		return buildResponseEntity(body, HttpStatus.NOT_FOUND);
	}
}
