package cabmgmt.rest.util;

import cabmgmt.persistence.dao.domain.UserEntity;
import cabmgmt.rest.domain.request.UserRecord;

public class UserRecordDTOMapper {

	//private constructor to prevent instantiation of utility class
	private UserRecordDTOMapper() {};

	/********** REST Domain DTO to persistence domain mapping ****/

	/**
	 *
	 * @param userRecord
	 * @return
	 */
	public static UserEntity toUserEntity(UserRecord userRecord) {
		UserEntity userEntity = null;

		if(userRecord!=null) {
			userEntity = new UserEntity();
			userEntity.setId(userRecord.getId());
			userEntity.setPhoneCountryCode(userRecord.getPhoneCountryCode());
			userEntity.setPhoneNumber(userRecord.getPhoneNumber());
			userEntity.setFirstName(userRecord.getFirstName());
			userEntity.setLastName(userRecord.getLastName());
			userEntity.setNotify(userRecord.isNotify());
			userEntity.setUserEmail(userRecord.getUserEmail());
			userEntity.setImageUrl(userRecord.getImageUrl());
			//upload email not changeable from REST so not mapped
		}
		return userEntity;
	}

	/********** persistence Domain DTO to REST domain mapping ****/

	/**
	 *
	 * @param userEntity
	 * @return
	 */
	public static UserRecord toUserRecord(UserEntity userEntity) {
		UserRecord userRecord = null;

		if(userEntity!=null) {
			userRecord = new UserRecord();

			userRecord.setId(userEntity.getId());
			userRecord.setPhoneCountryCode(userEntity.getPhoneCountryCode());
			userRecord.setPhoneNumber(userEntity.getPhoneNumber());
			userRecord.setFirstName(userEntity.getFirstName());
			userRecord.setLastName(userEntity.getLastName());
			userRecord.setNotify(userEntity.isNotify());
			userRecord.setUserEmail(userEntity.getUserEmail());
			userRecord.setImageUrl(userEntity.getImageUrl());

		}
		return userRecord;
	}
}
