package com.lbb.dao;

import com.lbb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        final User user = new User();
        jdbcTemplate.query(sql, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user) {
        String sql = "update t_user set last_visit = ?, last_ip=?, credits=? where user_id = ?";
        jdbcTemplate.update(sql, new Object[]{user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId()});
    }

}
