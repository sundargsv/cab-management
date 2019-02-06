package cabmgmt.persistence.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cabmgmt.persistence.dao.UserDAO;
import cabmgmt.persistence.dao.mongo.UserDAOImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Slf4j
/**
 * NOTE:
 * ====
 * mongo.properties will not be packaged in this module,
 * It expects the consuming module to have the properties file in their classpath
 *
 * A copy of application.yml can be found under /test/resources for testing purpose
 *
 */
@EnableMongoRepositories(basePackages= {"cabmgmt.persistence.dao.mongo.repo"})
public class PersistenceMongoConfig extends AbstractMongoConfiguration {

	@Value("${spring.data.mongodb.uri}")
	private String uri;

	@Value("${spring.data.mongodb.database}")
	private String databaseName;

	/**
	 *  {@inheritDoc}
	 */
	@Override
	protected String getDatabaseName() {
		return this.databaseName;
	}


	@Override
	public MongoClient mongoClient()
	{
		return new MongoClient(new MongoClientURI(uri));
	}


	@Bean
	public MongoDbFactory getMongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(mongoClient(), getDatabaseName());
	}

	@Bean
	public MongoTemplate getMongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(getMongoDbFactory());
		return mongoTemplate;
	}

	/********************* DAO implementation ************************/
	/**
	 *
	 * @return
	 */
	@Bean
	public UserDAO userDAO() {
		log.info("Using Mongo implementation for User DAO");

		return new UserDAOImpl();
	}
}
