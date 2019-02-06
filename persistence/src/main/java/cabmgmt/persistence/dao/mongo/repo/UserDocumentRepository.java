package cabmgmt.persistence.dao.mongo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.repository.CrudRepository;

import cabmgmt.persistence.dao.mongo.domain.UserDocument;
import org.springframework.data.mongodb.repository.Query;

public interface UserDocumentRepository extends MongoRepository<UserDocument, String> {
	
	/**
	 * 
	 * @param phoneCountryCode
	 * @param phoneNumber
	 * @return
	 */
	@Query(value = "{ 'phoneCountryCode' : ?0, 'phoneNumber' : ?1 }")
	List<UserDocument> findByPhoneCountryCodeAndPhoneNumber(String phoneCountryCode, String phoneNumber);

}
