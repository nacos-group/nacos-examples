package com.alibaba.nacos.example.spring.service.impl;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.example.spring.model.User;
import com.alibaba.nacos.example.spring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author hexu.hxy
 * @date 2019/1/7
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final JdbcTemplate jdbcTemplate;

    private final RedisTemplate redisTemplate;

    @NacosValue(value = "${app.user.cache}", autoRefreshed = true)
    private boolean cache;

    @Autowired
    public UserServiceImpl(JdbcTemplate jdbcTemplate, RedisTemplate redisTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public User findById(Long id) {
        LOGGER.info("cache: {}", cache);

        if (cache) {
            Object obj = redisTemplate.opsForValue().get(key(id));
            if (obj != null) {
                LOGGER.info("get user from cache, id: {}", id);
                return (User)obj;
            }
        }

        User user = getUser(id);
        if (user != null) {
            if (cache) {
                LOGGER.info("set cache for user, id: {}", id);
                redisTemplate.opsForValue().set(key(id), user);
            }
        }

        return user;
    }

    private User getUser(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT id, name FROM user WHERE id=?", new Object[] {id},
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setId(rs.getLong(1));
                        user.setName(rs.getString(2));
                        return user;
                    }
                });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private String key(Long id) {
        return String.format("nacos-spring-config-multi-data-ids-example:user:%d", id);
    }

}