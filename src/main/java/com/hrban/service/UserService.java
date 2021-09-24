package com.hrban.service;

import com.hrban.dao.LoginLogDao;
import com.hrban.dao.UserDao;
import com.hrban.domain.LoginLog;
import com.hrban.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginLogDao loginLogDao;

    /**
     * 查看用户是否存在
     *
     * @param userName
     * @param password
     * @return
     */
    public boolean hasMatchUser(String userName, String password) {
        int mainCount = userDao.getMatchCount(userName, password);
        return mainCount > 0;
    }

    /**
     * 通过用户名查找用户
     *
     * @param userName
     * @return
     */
    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    @Transactional
    public void loginSuccess(User user) {
        user.setCredits(5 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());

        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
