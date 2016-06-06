package com.lbb.dao;

import com.lbb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by lbb on 2016/6/7.
 */

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String userName, String password) {
        String sql = "select count(0) from t_user where user_name = ? and password = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userName, password}, java.lang.Integer.class);
    }

    public User findUserByUserName(String userName) {
        String sql = "select user_id, user_name, credits from t_user where user_name = ?";
        User user = new User();
        jdbcTemplate
    }

}
