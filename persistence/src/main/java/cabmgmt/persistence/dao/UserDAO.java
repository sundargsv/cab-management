package cabmgmt.persistence.dao;

import java.util.List;

import cabmgmt.persistence.dao.domain.UserEntity;
import cabmgmt.persistence.dao.exception.UserDAOException;


public interface UserDAO extends BaseDAO<UserEntity, UserDAOException> {
	/**
	 * 
	 * @param phoneCountryCode composite info for user identity
	 * @param phoneNumber composite info for user identity
	 * @return
	 * @throws UserDAOException
	 */
	List<UserEntity> findByPhoneNumber(String phoneCountryCode, String phoneNumber) throws UserDAOException;
}
