package org.elink.database.mongodb;

import org.elink.database.hudong.model.HuDongEntity;
import org.elink.database.hudong.model.HudongTag;
import org.elink.database.model.EntitySource;
import org.elink.database.model.PageInfo;
import org.elink.database.mongodb.repository.impl.BasicRepository;
import org.elink.database.mongodb.repository.impl.DBConvertor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MongoBeansConfiguration {
	/*
	 * entity source
	 */
	@Bean
	DBConvertor<EntitySource> entitySourceConvertor(){
		return new DBConvertor<>(EntitySource.class);
	}
	@Bean
	BasicRepository<EntitySource> entitySourceDao(){
		return new BasicRepository<EntitySource>(EntitySource.class,entitySourceConvertor());
	}

	/*
	 * page information
	 */
	@Bean
	DBConvertor<PageInfo> pageInfoConvertor(){
		return new DBConvertor<>(PageInfo.class);
	}
	@Bean
	BasicRepository<PageInfo> pageInfoDao(){
		return new BasicRepository<PageInfo>(PageInfo.class,pageInfoConvertor());
	}
	
	/*
	 * hudong entity 
	 */
	@Bean
	DBConvertor<HuDongEntity> HudongEntityConvertor(){
		return new DBConvertor<>(HuDongEntity.class);
	}
	@Bean
	BasicRepository<HuDongEntity> HudongEntityDao(){
		return new BasicRepository<>(HuDongEntity.class, HudongEntityConvertor());
	}
	
	/*
	 * hudong tag
	 */
	@Bean
	DBConvertor<HudongTag> HudongTagConvertor(){
		return new DBConvertor<>(HudongTag.class);
	}
	@Bean
	BasicRepository<HudongTag> HudongTagDao(){
		return new BasicRepository<>(HudongTag.class, HudongTagConvertor());
	}
}
