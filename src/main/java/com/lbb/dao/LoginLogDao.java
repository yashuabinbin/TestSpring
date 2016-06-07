package com.lbb.dao;

import com.lbb.model.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by lbb on 2016/6/8.
 */
@Repository
public class LoginLogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertLoginLog(LoginLog loginLog) {
        String sql = "insert into t_login_log(user_id, ip, login_datetime) values(?, ?, ?)";

        jdbcTemplate.update(sql, new Object[]{loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate()});
    }

}
