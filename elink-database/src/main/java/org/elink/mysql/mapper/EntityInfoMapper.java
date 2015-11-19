package org.elink.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.elink.mysql.model.EntityInfo;
import org.elink.mysql.model.EntitySource;
import org.springframework.stereotype.Repository;
/**
 * entity_info mapper
 * @author v11
 */
@Repository
public interface EntityInfoMapper {
	@Select("select * from entity_info")
	public List<EntityInfo> getAll();
	@Select("select * from entity_info where entity_id=#{id}")
	public List<EntityInfo> getById(
			@Param("id") Integer id);
	
}
