package cabmgmt.service.admin;

import cabmgmt.persistence.dao.domain.UserEntity;
import cabmgmt.service.config.ServiceConfig;
import cabmgmt.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Sundar Gsv
 * @Date 14/10/18
 * @ClassDescription
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {ServiceConfig.class})
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void test001_createUserTest() throws Exception{
        log.info("test create user entity");
        UserEntity testUser = UserEntity.builder()
                                .firstName("Test")
                                .lastName("Test")
                                .phoneNumber("1234567890")
                                .userEmail("test@ctli.com")
                                .build();

        userService.createUser(testUser);
    }
}
