package org.elink.database.mongodb;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;




@Configuration
@EnableMongoRepositories
@PropertySource("classpath:mongodb.properties")
public class MongoDBConfiguration extends AbstractMongoConfiguration {
	@Autowired
	Environment env;
	
    @Override
    protected String getDatabaseName() {
        return env.getProperty("dbName");
    }
    @Override
    public Mongo mongo() throws Exception {
    	return new MongoClient(env.getProperty("ip"),Integer.parseInt(env.getProperty("port")));

    }
    @Override
    protected String getMappingBasePackage() {
        return "org.elink.database.model";
    }
    /**
     * can remove _class
     */
	public @Bean MongoTemplate mongoTemplate() throws Exception {
		// remove _class
		MappingMongoConverter converter = new MappingMongoConverter(
				mongoDbFactory(), new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(),
				converter);
		return mongoTemplate;

	}
	
}

