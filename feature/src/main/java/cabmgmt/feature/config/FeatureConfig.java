package cabmgmt.feature.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cabmgmt.feature.admin.AdminFeatureService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FeatureConfig {

	@Bean
	public AdminFeatureService adminFeature() {
		log.info("initializing admin feature service...");
		return new AdminFeatureService();
	}

}
