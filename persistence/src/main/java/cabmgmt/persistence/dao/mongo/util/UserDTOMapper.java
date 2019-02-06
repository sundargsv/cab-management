package cabmgmt.persistence.dao.mongo.util;

import cabmgmt.persistence.dao.mongo.domain.UserDocument;
import cabmgmt.persistence.dao.domain.UserEntity;

/**
 * utility class for transforming btw DAO domain and mongo DAO implementation domain object
 * @author Sundar Gsv
 *
 */
public class UserDTOMapper {
	//private constructor for util class
	private UserDTOMapper() {};

	/***** DAO domain DTO to implementation domain DTO ***/

	public static UserDocument toMongoDocument(UserEntity userDTO) {
		UserDocument cbDoc = new UserDocument();

		if(userDTO!=null) {
			cbDoc.setId(userDTO.getId());
			cbDoc.setPhoneCountryCode(userDTO.getPhoneCountryCode());
			cbDoc.setPhoneNumber(userDTO.getPhoneNumber());
			cbDoc.setFirstName(userDTO.getFirstName());
			cbDoc.setLastName(userDTO.getLastName());
			cbDoc.setNotify(userDTO.isNotify());
			cbDoc.setUserEmail(userDTO.getUserEmail());
			cbDoc.setActive(userDTO.isActive());
			cbDoc.setFcmToken(userDTO.getFcmToken());
			cbDoc.setImageUrl(userDTO.getImageUrl());
		}

		return cbDoc;
	}

	/***** Implementation domain DTO to DAO domain DTO ***/

	public static UserEntity toDAOEntity(UserDocument userDoc) {
		UserEntity daoEntity = new UserEntity();

		if(userDoc!=null) {
			daoEntity.setId(userDoc.getId());
			daoEntity.setPhoneCountryCode(userDoc.getPhoneCountryCode());
			daoEntity.setPhoneNumber(userDoc.getPhoneNumber());
			daoEntity.setFirstName(userDoc.getFirstName());
			daoEntity.setLastName(userDoc.getLastName());
			daoEntity.setUserEmail(userDoc.getUserEmail());
            daoEntity.setNotify(userDoc.isNotify());
			daoEntity.setActive(userDoc.isActive());
			daoEntity.setFcmToken(userDoc.getFcmToken());
			daoEntity.setImageUrl(userDoc.getImageUrl());
		}

		return daoEntity;
	}
}
