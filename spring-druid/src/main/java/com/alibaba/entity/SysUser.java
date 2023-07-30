package com.alibaba.entity;

import lombok.Data;

/**
 * 用户对象 sys_user
 *
 */
@Data
public class SysUser {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

}
