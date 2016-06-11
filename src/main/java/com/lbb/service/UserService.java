package com.lbb.service;

import com.lbb.dao.LoginLogDao;
import com.lbb.dao.UserDao;
import com.lbb.model.LoginLog;
import com.lbb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lbb on 2016/6/11.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;

    /**
     * 推荐使用Md5加密方式存储密码，并且为了防止暴力破解，可以通过图片验证码的方式来防止
     * @param userName
     * @param password
     * @return
     */
    public boolean hasMatchUser(String userName, String password) {
        int count = userDao.getMatchCount(userName, password);
        return count > 0;
    }

    public User findUserByuserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    public void loginSuccess(User user) {
        user.setCredits(user.getCredits() + 5);
        userDao.updateLoginInfo(user);

        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        loginLogDao.insertLoginLog(loginLog);
    }
}
