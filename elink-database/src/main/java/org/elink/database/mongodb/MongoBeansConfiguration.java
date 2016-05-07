package org.elink.database.mongodb;

import org.elink.database.hudong.model.HuDongEntity;
import org.elink.database.hudong.model.HudongTag;
import org.elink.database.model.AttributeNameInfo;
import org.elink.database.model.AttributePairsInfo;
import org.elink.database.model.EntityInfo;
import org.elink.database.model.EntitySource;
import org.elink.database.model.MultiEntityInfo;
import org.elink.database.model.PageInfo;
import org.elink.database.model.TemplateInfo;
import org.elink.database.model.cluster.AttrInfo;
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
	 * EntityInfo
	 */
	@Bean
	DBConvertor<EntityInfo> entityInfoConvertor(){
		return new DBConvertor<>(EntityInfo.class);
	}
	@Bean
	BasicRepository<EntityInfo> entityInfoDao(){
		return new BasicRepository<>(EntityInfo.class, entityInfoConvertor());
	}
	
	/*
	 * attribute info
	 */
	@Bean
	DBConvertor<AttributePairsInfo> attributePairsInfoConvertor(){
		return new DBConvertor<>(AttributePairsInfo.class);
	}
	@Bean
	BasicRepository<AttributePairsInfo> attributePairsInfoDao(){
		return new BasicRepository<>(AttributePairsInfo.class, attributePairsInfoConvertor());
	}
	
	/*
	 * attribute name
	 */
	@Bean
	DBConvertor<AttributeNameInfo> attributeNameInfoConvertor(){
		return new DBConvertor<>(AttributeNameInfo.class);
	}
	@Bean
	BasicRepository<AttributeNameInfo> attributeNameInfoDao(){
		return new BasicRepository<>(AttributeNameInfo.class, attributeNameInfoConvertor());
	}
	
	/*
	 * multi entity info
	 */
	@Bean
	DBConvertor<MultiEntityInfo> multiEntityInfoConvertor(){
		return new DBConvertor<>(MultiEntityInfo.class);
	}
	@Bean
	BasicRepository<MultiEntityInfo> multiEntityInfoDao(){
		return new BasicRepository<>(MultiEntityInfo.class, multiEntityInfoConvertor());
	}
	/*
	 * multi entity info
	 */
	@Bean
	DBConvertor<AttrInfo> attrInfoConvertor(){
		return new DBConvertor<>(AttrInfo.class);
	}
	@Bean
	BasicRepository<AttrInfo> attrInfoDao(){
		return new BasicRepository<>(AttrInfo.class, attrInfoConvertor());
	}
	
	/*
	 * attribute template
	 */
	@Bean
	DBConvertor<TemplateInfo> templateInfoConvertor(){
		return new DBConvertor<>(TemplateInfo.class);
	}
	@Bean
	BasicRepository<TemplateInfo> templateInfoDao(){
		return new BasicRepository<>(TemplateInfo.class, templateInfoConvertor());
	}
	
}
