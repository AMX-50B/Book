package net.hncu.bookstore.service;

import net.hncu.bookstore.dao.UserDao;
import net.hncu.bookstore.domain.User;
import net.hncu.bookstore.exception.UserException;
import net.hncu.bookstore.utils.SendJMail;

import java.sql.SQLException;

/**
 * 注册服务
 *
 * @author
 * @create 2017-03-27 21:21
 **/
public class UserService  {
    UserDao ud = new UserDao();

    /**
     * 注册
     * @param user
     * @throws UserException
     */
    public void register(User user)throws UserException {
        try {
            ud.addUser(user);
            String emailMsg="注册成功，请<a herf='http:localhost/book/activeServlet?activeCode="
                    + user.getActiveCode()+">激活</a>后登录";
            SendJMail.sendMail(user.getEmail(),emailMsg);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("注册失败");
        }
    }

    /**
     * 激活
     * @param activeCode
     * @throws UserException
     */
    public void activeUser(String activeCode) throws UserException {
        User user = null;
        try {
            user = ud.findUserByActiveCode(activeCode);
            if(user!=null){
                ud.activeByActiveCode(activeCode);
                return;
            }
            throw new UserException("激活失败！");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("激活失败！");
        }
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     * @throws UserException
     */
    public User login(String username, String password) throws UserException {
        User user = null;
        try {
            user= ud.findUserByUserNameAndPassword(username,password);
            if(user==null){
                throw new UserException("用户名和密码错误！");
            }
            if(user.getState()==0){
                throw new UserException("用户未激活！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("登录失败");
        }
        return user;
    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     * @throws UserException
     */
    public User findUserById(String id) throws UserException {
        User user = null;
        try {
            user=  ud.findUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("查询错误");
        }
        return user;
    }

    public void modifyUserInfo(User user) throws UserException {
        try {
            ud.modifyUserInfo(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("修改失败！");
        }
    }
}
