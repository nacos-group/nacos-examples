package com.alibaba.mapper;

import com.alibaba.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * mapper接口
 *
 */
@Mapper
public interface MasterMapper {

    SysUser getMasterInfo(@Param("userId") String userId);

}
