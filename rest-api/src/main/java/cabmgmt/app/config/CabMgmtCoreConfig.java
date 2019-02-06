package cabmgmt.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cabmgmt.app.annotation.AdminApi;
import cabmgmt.app.annotation.CommonApi;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Slf4j
@Configuration
public class CabMgmtCoreConfig {

	/** Swagger 2 UI docket **/
	@Bean
	public Docket swaggerAdminUI(){
		log.info("Initializing Swagger 2 UI docket for Admin service ...");

		return new Docket(DocumentationType.SWAGGER_2).select()
													  .apis(RequestHandlerSelectors.withClassAnnotation(AdminApi.class))
													  .build()
													  .groupName("maintenance")
													  .apiInfo(new ApiInfoBuilder().title("Cab Management")

															                       .description("Cab Management Administration API")
															                       .build())
													  //don't generate with default response code
													  .useDefaultResponseMessages(false);
	}

	@Bean
	public Docket swaggerUI(){
		log.info("Initializing Swagger 2 UI docket for common service ...");

		return new Docket(DocumentationType.SWAGGER_2).select()
													  .apis(RequestHandlerSelectors.withClassAnnotation(CommonApi.class))
													  .build()
													  .groupName("common")
													  .apiInfo(new ApiInfoBuilder().title("Cab Management")
															  					   .version("1.0.0")
															                       .description("Cab Management Core API")
															                       .build())
													  //don't generate with default response code
													  .useDefaultResponseMessages(false);
	}

}
