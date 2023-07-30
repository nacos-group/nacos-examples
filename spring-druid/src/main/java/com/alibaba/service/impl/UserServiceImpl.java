package com.alibaba.service.impl;

import com.alibaba.entity.SysUser;
import com.alibaba.mapper.MasterMapper;
import com.alibaba.service.UserService;
import com.alibaba.test.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户业务实现类
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private MasterMapper masterMapper;

    @Resource
    private TestMapper testMapper;

    @Override
    public SysUser getMasterInfo(String userId) {
        return masterMapper.getMasterInfo(userId);
    }

    @Override
    public SysUser getTestInfo(String userId) {
        return testMapper.getTestInfo(userId);
    }

}
