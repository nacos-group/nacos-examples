package com.liyh.test.mapper;

import com.liyh.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * test接口
 *
 * @Author: liyh
 */
@Mapper
public interface TestMapper {

    SysUser getTestInfo(@Param("userId") String userId);

}
