package org.elink.database.pname;

import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.mongodb.repository.impl.DBConvertor;
import org.elink.database.pname.model.Pname;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class PnameBeansConfiguration {
	@Bean
	DBConvertor<Pname> pnameConvertor(){
		return new DBConvertor<>(Pname.class);
	}
	@Bean
	BasicRepository<Pname> pnameDao(){
		return new BasicRepository<Pname>(Pname.class,pnameConvertor());
	}
}
