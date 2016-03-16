package org.elink.database.hudong;

import org.elink.database.hudong.model.HuDongEntity;
import org.elink.database.hudong.model.HudongTag;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.mongodb.repository.impl.DBConvertor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class HuDongBeansConfiguration {

	/*
	 * hudong entity 
	 */
	@Bean
	DBConvertor<HuDongEntity> hudongEntityConvertor(){
		return new DBConvertor<>(HuDongEntity.class);
	}
	@Bean
	BasicRepository<HuDongEntity> hudongEntityDao(){
		return new BasicRepository<>(HuDongEntity.class, hudongEntityConvertor());
	}
	
	/*
	 * hudong tag
	 */
	@Bean
	DBConvertor<HudongTag> hudongTagConvertor(){
		return new DBConvertor<>(HudongTag.class);
	}
	@Bean
	BasicRepository<HudongTag> hudongTagDao(){
		return new BasicRepository<>(HudongTag.class, hudongTagConvertor());
	}
}
