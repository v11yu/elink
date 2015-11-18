package org.elink.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.elink.mysql.model.EntitySource;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author v11
 *
 */
@Repository
public interface EntitySourceMapper {
	@Select("select * from global_env")
	public List<EntitySource> getAll();
}
