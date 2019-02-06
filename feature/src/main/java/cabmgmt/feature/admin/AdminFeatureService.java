package cabmgmt.feature.admin;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cabmgmt.feature.exception.AdminFeatureException;
import cabmgmt.persistence.dao.domain.UserEntity;
import cabmgmt.service.exception.UserServiceException;
import cabmgmt.service.user.UserService;

/**
 * Admin feature service
 *
 * TODO: consider extract out as interface if required
 * @author Sundar Gsv
 *
 */
public class AdminFeatureService {

	@Autowired
	private UserService userService;

	/**
	 *
	 * @param userEntity
	 * @return
	 * @throws AdminFeatureException
	 */
	public UserEntity createUser(UserEntity userEntity) throws AdminFeatureException{

		try {
			if(StringUtils.isBlank(userEntity.getFirstName())) {
				userEntity.setFirstName(userEntity.getPhoneNumber());
			}
			if(StringUtils.isBlank(userEntity.getLastName())) {
				userEntity.setLastName(userEntity.getPhoneNumber());
			}

			return userService.createUser(userEntity);
		}
		catch(UserServiceException use) {
			throw new AdminFeatureException(use);
		}
	}

	/**
	 *
	 * @param id
	 * @throws AdminFeatureException
	 */
	public void disableUserById(String id) throws AdminFeatureException{
		try {
			//1. mark user record disabled
			UserEntity disabledUser = userService.disableUserById(id);

			//2. look up user's specific entity(s) to delete/ backup (disable)

		}
		catch(UserServiceException  use) {
			throw new AdminFeatureException(use);
		}
	}

	/**
	 *
	 * @param phoneCountryCode
	 * @param phoneNumber
	 * @throws AdminFeatureException
	 */
	public void disableUserByPhoneNumber(String phoneCountryCode, String phoneNumber) throws AdminFeatureException{
		try {
			//1. mark user record as disabled
			UserEntity disabledUser = userService.disableUserByPhoneNumber(phoneCountryCode, phoneNumber);

			//2. look up user's specific entity(s) to delete/ backup (disable)

		}
		catch(UserServiceException use) {
			throw new AdminFeatureException(use);
		}
	}

	/**
	 *
	 * @param phoneCountryCode
	 * @param phoneNumber
	 * @return
	 * @throws AdminFeatureException
	 */
	public Optional<UserEntity> getUserByPhoneNumber(String phoneCountryCode,
													 String phoneNumber)throws AdminFeatureException{
		try {
			return Optional.of(userService.findUserByPhoneNumber(phoneCountryCode, phoneNumber));
		}
		catch(UserServiceException use) {
			if("User not found".equalsIgnoreCase(use.getMessage())) {
				return Optional.empty();
			}
			else {
				throw new AdminFeatureException(use);
			}
		}
	}
	/**
	 *
	 * @param userEntity
	 * @return
	 * @throws AdminFeatureException
	 */
	public UserEntity updateUser(UserEntity userEntity) throws AdminFeatureException{

		try {

			if(userEntity.getId()!=null) {
				return userService.updateUserById(userEntity);
			}
			else {
				return userService.updateUserByPhoneNumber(userEntity);
			}
		}
		catch(UserServiceException use) {
			throw new AdminFeatureException(use);
		}
	}
}
