package cabmgmt.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cabmgmt.service.user.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ServiceConfig {

	@Bean
	public UserService userService() {
		log.info("initializing user service...");

		return new UserService();
	}
}
