package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.domain.User;
import net.hncu.bookstore.exception.UserException;
import net.hncu.bookstore.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by LY on 2017/3/28.
 */
@WebServlet("/modifyUserInfoServlet")
public class ModifyUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user= new User();
        try {
            //获取id
            BeanUtils.populate(user,request.getParameterMap());
            //调用逻辑
            UserService us = new UserService();
            us.modifyUserInfo(user);
            request.getSession().invalidate();//注销
            response.sendRedirect(request.getContextPath()+"/modifyUserInfoSuccess.jsp");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (UserException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }
    }
}
