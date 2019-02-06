package cabmgmt.persistence.dao;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cabmgmt.persistence.config.PersistenceMongoConfig;
import cabmgmt.persistence.dao.domain.UserEntity;
import cabmgmt.persistence.dao.exception.UserDAOException;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {PersistenceMongoConfig.class})
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDAOTest {

	@Autowired
	private UserDAO userDAO;

	private static final String PHONE_NUMBER = "3186142388";

	private static final String PHONE_COUNTRY_CODE = "+1";

	@Test
	public void test001_createUserTest() throws Exception{

		UserEntity user = createTestUser(PHONE_NUMBER);

		log.info("user created : {}", user);
 	}

	@Test
	public void test002_getUserTest() throws Exception{

		UserEntity ref = getNCreateUser(PHONE_COUNTRY_CODE, PHONE_NUMBER);
		Optional<UserEntity> userOpt = userDAO.get(ref.getId());

		Assert.assertTrue("User Record must exist and return", userOpt.isPresent());
	}

	@Test
	public void test003_updateUserTest() throws Exception{
		UserEntity ref = getNCreateUser(PHONE_COUNTRY_CODE, PHONE_NUMBER+3);

		ref.setPhoneNumber("00000000001");

		UserEntity userResult = userDAO.update(ref);

		log.info("updated : {}", userResult);

		Assert.assertEquals("User phone number must be updated", "00000000001", userResult.getPhoneNumber());
	}

	@Test
	public void test004_deleteUserTest() throws Exception{
		List<UserEntity> refs = userDAO.findAll();

		Assert.assertNotNull("User Record must exist and return", refs);

		for(UserEntity ref : refs) {
			userDAO.delete(ref);
		}
	}

	private UserEntity getNCreateUser(String phoneCountryCode, String phoneNumber) throws UserDAOException {
		UserEntity refUser = null;

		List<UserEntity> users = userDAO.findByPhoneNumber(phoneCountryCode, phoneNumber);

		if(users.isEmpty()) {
			refUser = createTestUser(phoneNumber);
		}
		else {
			refUser = users.get(0);
		}

		return refUser;
	}

	private UserEntity createTestUser(String phoneNumber) throws UserDAOException {
		UserEntity user = new UserEntity();
		user.setPhoneNumber(phoneNumber);

		return userDAO.create(user);
	}
}
