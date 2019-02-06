package cabmgmt.persistence.dao.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import cabmgmt.persistence.dao.UserDAO;
import cabmgmt.persistence.dao.common.UUIDGenerator;
import cabmgmt.persistence.dao.mongo.domain.UserDocument;
import cabmgmt.persistence.dao.mongo.repo.UserDocumentRepository;
import cabmgmt.persistence.dao.mongo.util.UserDTOMapper;
import cabmgmt.persistence.dao.domain.UserEntity;
import cabmgmt.persistence.dao.exception.UserDAOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private UserDocumentRepository userDocRepo;
	
	@Override
	public UserEntity create(UserEntity entity) throws UserDAOException {
		try{
			//0. check if record exist (name)
			List<UserDocument> checkUsers = userDocRepo.findByPhoneCountryCodeAndPhoneNumber(entity.getPhoneCountryCode()==null ? "" : entity.getPhoneCountryCode(),
																							 entity.getPhoneNumber());
			
			if(!checkUsers.isEmpty()){
				throw new UserDAOException("User phone number ["+entity+"] already taken");
			}
			
			//1. convert to mongo document
			UserDocument userDoc = UserDTOMapper.toMongoDocument(entity);
			
			//2. initial new record attirbutes
			//2.a Generate spatial sensitive UUID
			userDoc.setId(UUIDGenerator.getTimebaseUUIDGenerator().toString());
			
			//3. persist into document repo
			userDoc = userDocRepo.save(userDoc);
			
			//4. convert mongo implementation DTO to component DTO
			UserEntity result = UserDTOMapper.toDAOEntity(userDoc);
			
			log.info("user created : {}",result);
			
			return result;
		}
		catch(UserDAOException mde){
			log.warn("user phone number {} already taken", entity.getPhoneNumber(), mde);
			throw mde;
		}
		catch(Exception e){
			log.warn("error creating user {} ", entity.getPhoneNumber(), e);
			throw new UserDAOException("Create user error",e);
		}
	}

	@Override
	public void delete(UserEntity entity) throws UserDAOException {
		try {
			//0. check if record exists
			Optional<UserDocument> cbDocOpt = userDocRepo.findById(entity.getId());
			
			if(!cbDocOpt.isPresent()) {
				throw new UserDAOException("User entity id "+entity.getId()+" not found");
			}
			
			//1. delete the document
			userDocRepo.delete(cbDocOpt.get());
		}
		catch(IllegalArgumentException iae) {
			log.warn("error deleting entity {}", entity.getId(), iae);
			throw new UserDAOException("error deleting entity "+entity.getId(), iae);
		}
	}

	@Override
	public List<UserEntity> findAll() throws UserDAOException {
		List<UserEntity> results = new ArrayList<>();
		
		Iterable<UserDocument> cbDocIter = userDocRepo.findAll();
		
		for(UserDocument cbDoc: cbDocIter) {
			results.add(UserDTOMapper.toDAOEntity(cbDoc));
		}
		return results;
	}

	@Override
	public Optional<UserEntity> get(String entityId) throws UserDAOException {
		Optional<UserDocument> cbDocOpt = userDocRepo.findById(entityId);

		if(cbDocOpt.isPresent()) {
			UserEntity result = UserDTOMapper.toDAOEntity(cbDocOpt.get());
			log.info("retrieved {}", result);
			
			return Optional.ofNullable(result);
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public UserEntity update(UserEntity entity) throws UserDAOException {
		try {
			//0. check if record exist
			Optional<UserDocument> cbDocOpt = userDocRepo.findById(entity.getId());

			if(!cbDocOpt.isPresent()) {
				throw new UserDAOException("Entity not exist for id "+entity.getId());
			}
			
			//1. convert entity to doc (assume all attribute override when id match
			UserDocument cbDoc = UserDTOMapper.toMongoDocument(entity);
			
			//Note: upper layer response to keep not modifiable attribute(s), persistence layer not contain other business logic

			//2. persist the changes
			cbDoc = userDocRepo.save(cbDoc);
			
			//3. convert back doc to entity
			UserEntity result = UserDTOMapper.toDAOEntity(cbDoc);
			
			log.info("Entity updated : {}", result);
			
			return result;
		}
		catch(UserDAOException mde){
			log.warn("user {} not found", entity.getId(), mde);
			throw mde;
		}
		catch(Exception e){
			log.warn("error updating user {} ", entity.getId(), e);
			throw new UserDAOException("Update user error",e);
		}
	}

	@Override
	public List<UserEntity> findByPhoneNumber(String phoneCountryCode, String phoneNumber) throws UserDAOException {
		List<UserEntity> results = new ArrayList<>();
		try {

			List<UserDocument> cbDocs = userDocRepo.findByPhoneCountryCodeAndPhoneNumber(phoneCountryCode == null? "": phoneCountryCode,
					phoneNumber);

			if(cbDocs!=null && !cbDocs.isEmpty()) {
				for(UserDocument cbDoc: cbDocs) {
					results.add(UserDTOMapper.toDAOEntity(cbDoc));
				}
			}

		}catch (NullPointerException npe){
			npe.printStackTrace();
		}

		
		return results;
	}

}
