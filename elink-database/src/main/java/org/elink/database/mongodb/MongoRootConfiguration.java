package org.elink.database.mongodb;

import org.elink.database.hudong.HuDongBeansConfiguration;
import org.elink.database.mysql.MysqlConfiguration;
import org.elink.database.pname.PnameBeansConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MongoDBConfiguration.class ,MongoBeansConfiguration.class,MysqlConfiguration.class
	,PnameBeansConfiguration.class,HuDongBeansConfiguration.class})
@ComponentScan("org.elink.database")
public class MongoRootConfiguration {

	
}
