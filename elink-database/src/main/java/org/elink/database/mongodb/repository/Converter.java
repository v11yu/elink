package org.elink.database.mongodb.repository;

import org.springframework.stereotype.Component;

import com.mongodb.DBObject;


public interface Converter<DB,POJO> {

	DB convertToDB(POJO obj);
	POJO convertToPojo(DB obj);
}
