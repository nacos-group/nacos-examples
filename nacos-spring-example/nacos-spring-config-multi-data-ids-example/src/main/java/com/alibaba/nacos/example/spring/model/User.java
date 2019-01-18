package com.alibaba.nacos.example.spring.model;

import java.io.Serializable;

/**
 * CREATE TABLE `user` (
 *  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
 *  `name` varchar(10) NOT NULL DEFAULT '',
 *  PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class User implements Serializable {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
