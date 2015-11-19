package org.elink.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.elink.mysql.model.EntityInfo;
import org.elink.mysql.model.EntitySource;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author v11
 *
 */
@Repository
public interface EntitySourceMapper {
	@Select("select * from entity_source")
	public List<EntitySource> getAll();
	@Select("select * from entity_source where entity_id=#{id}")
	public List<EntitySource> getById(
			@Param("id") Integer id);
	
	@Select("select * from entity_source limit #{bg},#{num}")
	public List<EntitySource> getByPage(@Param("bg") Integer bg,@Param("num") Integer num);
	
}
