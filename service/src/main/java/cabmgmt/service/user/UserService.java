package cabmgmt.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import cabmgmt.persistence.dao.UserDAO;
import cabmgmt.persistence.dao.domain.UserEntity;
import cabmgmt.persistence.dao.exception.UserDAOException;
import cabmgmt.service.exception.UserServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 * User service layer implementation
 * TODO: consider extracted out method as interface if required
 *
 * @author Sundar Gsv
 *
 */
@Slf4j
public class UserService {

	@Autowired
	private UserDAO userDAO;

	//TODO: read from configuration file
	private String defaultPhoneCountryCode = "+91";

	/**
	 *
	 * @param userEntity
	 * @return
	 * @throws UserServiceException
	 */
	public UserEntity createUser(UserEntity userEntity) throws UserServiceException{
		try {
			//1.a active
			userEntity.setActive(true);

			//1.b. defaulting phone countryCode
			if(userEntity.getPhoneCountryCode() == null) {
				userEntity.setPhoneCountryCode(defaultPhoneCountryCode);
			}

			//2. create
			return userDAO.create(userEntity);
		}
		catch(UserDAOException e) {
			throw new UserServiceException(e);
		}
	}

	/**
	 *
	 * @param userEntity
	 * @return
	 * @throws UserServiceException
	 */
	public UserEntity updateUserById(UserEntity userEntity) throws UserServiceException{
		try {
			log.info("update user by id {}",userEntity.getId());

			Optional<UserEntity> userOpt = userDAO.get(userEntity.getId());

			if(!userOpt.isPresent()) {
				throw new UserServiceException("Unable to update none exist user record "+userEntity.getId());
			}
			//1. keep not modifiable attribute
			preserveNotModifiableAttribute(userOpt.get(),userEntity);

			//2. update
			return userDAO.update(userEntity);
		}
		catch(UserDAOException ude) {
			throw new UserServiceException(ude);
		}
	}

	/**
	 *
	 * @param userEntity
	 * @return
	 * @throws UserServiceException
	 */
	public UserEntity updateUserByPhoneNumber(UserEntity userEntity) throws UserServiceException{
		try {
			log.info("update user by phone {}",userEntity.getPhoneNumber());

			String phoneCountryCode = userEntity.getPhoneCountryCode() == null? defaultPhoneCountryCode : userEntity.getPhoneCountryCode();
			List<UserEntity> userEntities = userDAO.findByPhoneNumber(phoneCountryCode, userEntity.getPhoneNumber());

			if(userEntities.isEmpty()) {
				throw new UserServiceException("Unable to update none exist user record by phone number "+userEntity.getPhoneNumber());
			}
			//1.a. keep not modifiable attribute
			preserveNotModifiableAttribute(userEntities.get(0),userEntity);
			//1.b. populate the id from db
			userEntity.setId(userEntities.get(0).getId());

			//2. update
			return userDAO.update(userEntity);
		}
		catch(UserDAOException ude) {
			throw new UserServiceException(ude);
		}
	}

	/**
	 * override none-modifiable db attribute back to change entity
	 * @param dbEntity
	 * @param changeEntity
	 */
	private void preserveNotModifiableAttribute(UserEntity dbEntity, UserEntity changeEntity) {
		//1.a. keep user active value
		changeEntity.setActive(dbEntity.isActive());
		//1.b. keep user phone countryCode
		changeEntity.setPhoneCountryCode(dbEntity.getPhoneCountryCode());
		changeEntity.setPhoneNumber(dbEntity.getPhoneNumber());
		//1.c. keep user's firebase cloud messaging token
		changeEntity.setFcmToken(dbEntity.getFcmToken());
	}

	/**
	 *
	 * @param phoneCountryCode
	 * @param phoneNumber
	 * @return
	 * @throws UserServiceException
	 */
	public UserEntity findUserByPhoneNumber(String phoneCountryCode, String phoneNumber) throws UserServiceException{
		try {
			String countryCode = phoneCountryCode == null? defaultPhoneCountryCode : phoneCountryCode;

			List<UserEntity> userEntities = userDAO.findByPhoneNumber(countryCode, phoneNumber);

			if(userEntities.isEmpty()) {
				throw new UserServiceException("User not found");
			}
			//Note: as the saving enforce user name uniqueness, at most 1 record match
			else {
				return userEntities.get(0);
			}
		}
		catch (UserDAOException e) {
			throw new UserServiceException(e);
		}
	}

	/**
	 *
	 * @param userId
	 * @return
	 * @throws UserServiceException
	 */
	public UserEntity findUserById(String userId) throws UserServiceException{
		try {

			Optional<UserEntity> userEntityOpt = userDAO.get(userId);

			if(userEntityOpt.isPresent()) {
				return userEntityOpt.get();
			}
			else {
				return null;
			}
		}
		catch (UserDAOException e) {
			throw new UserServiceException(e);
		}
	}

	/**
	 *
	 * @param id
	 * @return
	 * @throws UserServiceException
	 */
	public UserEntity disableUserById(String id) throws UserServiceException{

		try {
			//1. locate user record by id
			Optional<UserEntity> userOpt = userDAO.get(id);

			if(!userOpt.isPresent()) {
				throw new UserServiceException("User not found for id "+id);
			}

			//2. disable user
			return disableUser(userOpt.get());

		}
		catch (UserDAOException e) {
			throw new UserServiceException(e);
		}
	}

	/**
	 *
	 * @param phoneCountryCode
	 * @param phoneNumber
	 * @return
	 * @throws UserServiceException
	 */
	public UserEntity disableUserByPhoneNumber(String phoneCountryCode, String phoneNumber) throws UserServiceException{
		try {

			String countryCode = phoneCountryCode == null? defaultPhoneCountryCode : phoneCountryCode;
			//1.locate user by phone number
			List<UserEntity> userEntities = userDAO.findByPhoneNumber(countryCode, phoneNumber);

			if(userEntities.isEmpty()) {
				throw new UserServiceException("User not found for phone number "+phoneNumber);
			}
			else if (userEntities.size()>1) {
				throw new UserServiceException("More than one user having same phone number, please use disable by id API");
			}
			//disable the user
			return disableUser(userEntities.get(0));
		}
		catch(UserDAOException e) {
			throw new UserServiceException(e);
		}
	}

	/**
	 *
	 * @param userEntity
	 * @return
	 * @throws UserDAOException
	 */
	private UserEntity disableUser(UserEntity userEntity) throws UserDAOException{
		//1. updated as disabled
		userEntity.setPhoneNumber("DIS_"+userEntity.getPhoneNumber());
		userEntity.setActive(false);

		//2. persist the changes
		return userDAO.update(userEntity);
	}

	/**
	 *
	 * @param id
	 * @param fcmToken
	 * @return
	 * @throws UserServiceException
	 */
	public UserEntity updateUserFCMTokenById(String id, String fcmToken) throws UserServiceException{
		try {
			//1. locate user record by id
			Optional<UserEntity> userOpt = userDAO.get(id);

			if(!userOpt.isPresent()) {
				throw new UserServiceException("User not found for id "+id);
			}

			//2. update user FCM
			return updateUserFCMToken(userOpt.get(), fcmToken);
		}
		catch (UserDAOException e){
			throw new UserServiceException(e);
		}
	}

	/**
	 *
	 * @param phoneCountryCode
	 * @param phoneNumber
	 * @param fcmToken
	 * @return
	 * @throws UserServiceException
	 */
	public UserEntity updateUserFCMTokenByPhone(String phoneCountryCode, String phoneNumber, String fcmToken)
		throws UserServiceException{

		try {

			String countryCode = phoneCountryCode == null? defaultPhoneCountryCode : phoneCountryCode;
			//1.locate user by phone number
			List<UserEntity> userEntities = userDAO.findByPhoneNumber(countryCode, phoneNumber);

			if(userEntities.isEmpty()) {
				throw new UserServiceException("User not found for phone number "+phoneNumber);
			}
			else if (userEntities.size()>1) {
				throw new UserServiceException("More than one user having same phone number, please use update by id API");
			}
			//disable the user
			return updateUserFCMToken(userEntities.get(0), fcmToken);
		}
		catch(UserDAOException e) {
			throw new UserServiceException(e);
		}
	}

	/**
	 *
	 * @param user
	 * @param fcmToken
	 * @return
	 */
	private UserEntity updateUserFCMToken(UserEntity user, String fcmToken) throws UserDAOException{
		//1. updated fcm token
		user.setFcmToken(fcmToken);

		//2. persist change
		return userDAO.update(user);
	}
}
