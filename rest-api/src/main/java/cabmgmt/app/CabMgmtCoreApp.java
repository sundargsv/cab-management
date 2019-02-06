package cabmgmt.app;

import cabmgmt.service.config.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import cabmgmt.app.config.CabMgmtCoreConfig;
import cabmgmt.feature.config.FeatureConfig;
import cabmgmt.persistence.config.PersistenceMongoConfig;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(scanBasePackages= {"cabmgmt.rest.controller"})
@Slf4j
public class CabMgmtCoreApp {

	public static final void main(String[] args) throws Exception{
		log.info("Starting standalone spring boot application for Cab Management Core ...");

		new SpringApplicationBuilder(CabMgmtCoreApp.class).sources(CabMgmtCoreConfig.class,
																	  FeatureConfig.class,
																	  ServiceConfig.class,
																	  PersistenceMongoConfig.class)
															 .run(args);

	}
}
