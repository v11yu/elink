package org.elink.database.mongodb;

import org.elink.database.mysql.MysqlConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MongoDBConfiguration.class ,MongoBeansConfiguration.class,MysqlConfiguration.class})
@ComponentScan("org.elink.database")
public class MongoRootConfiguration {

	
}
