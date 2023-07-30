package com.alibaba.service;

import com.alibaba.entity.SysUser;

/**
 * 用户业务接口
 *
 */
public interface UserService {

    SysUser getMasterInfo(String userId);

    SysUser getTestInfo(String userId);

}
