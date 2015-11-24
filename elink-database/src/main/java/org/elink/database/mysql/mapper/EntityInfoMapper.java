package org.elink.database.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.elink.database.model.EntityInfo;
import org.elink.database.model.EntitySource;
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
	@Select("select * from entity_info limit #{bg},#{num}")
	public List<EntityInfo> getByPage(@Param("bg") Integer bg,@Param("num") Integer num);
	
	
}
