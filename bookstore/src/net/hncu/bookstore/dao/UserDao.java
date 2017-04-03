package net.hncu.bookstore.dao;

import net.hncu.bookstore.domain.User;
import net.hncu.bookstore.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.activation.DataSource;
import java.sql.SQLException;

/**
 * Created by LY on 2017/3/27.
 */
public class UserDao {
    /**
     * 添加用户
     * @param user
     * @throws SQLException
     */
    public void addUser(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        String sql="INSERT INTO users(username, password, gender, email, telephone, introduce, activeCode, state)" +
                " VALUES (?,?,?,?,?,?,?,?)";
        qr.update(sql,user.getUsername(),user.getPassword(),user.getGender(),user.getEmail(),user.getTelephone(),
                user.getIntroduce(),user.getActiveCode(),user.getState());
    }

    /**
     * 根据激活码查找用户
     * @param activeCode
     * @return User
     * @throws SQLException
     */
    public User findUserByActiveCode(String activeCode) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from users where activeCode = ?",new BeanHandler<User>(User.class),activeCode);
    }

    /**
     * 设置用户状态为激活（1）
     * @param activeCode
     * @throws SQLException
     */
    public void activeByActiveCode(String activeCode) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("update user set state = 1 where activeCode=?",activeCode);
    }

    /**
     * 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public User findUserByUserNameAndPassword(String username, String password) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from users where username = ? and password = ?",new BeanHandler<User>(User.class),username,password);
    }

    public User findUserById(String id) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from users where id = ?",new BeanHandler<User>(User.class),id);
    }

    public void modifyUserInfo(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        String sql = "update users set password =?,gender = ?,telephone = ? where id=?";
        qr.update(sql,user.getPassword(),user.getGender(),user.getTelephone(),user.getId());
    }
}
