package com.hrban.dao;

import com.hrban.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String userName, String password) {
        String MATCH_COUNT_SQL = " SELECT count(*) FROM t_user  " +
                " WHERE user_name =? and password=? ";

        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, Integer.class, userName, password);
    }

    public User findUserByUserName(final String userName) {
        String sqlStr = " SELECT user_id,user_name,credits "
                + " FROM t_user WHERE user_name =? ";
        final User user = new User();
        jdbcTemplate.query(sqlStr,
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setUserId(rs.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(rs.getInt("credits"));
                    }
                }, userName);
        return user;
    }

    public void updateLoginInfo(User user) {
        String UPDATE_LOGIN_INFO_SQL = " UPDATE t_user SET " +
                " last_visit=?,last_ip=?,credits=?  WHERE user_id =?";

        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, user.getLastVisit(),
                user.getLastIp(), user.getCredits(), user.getUserId());
    }
}
