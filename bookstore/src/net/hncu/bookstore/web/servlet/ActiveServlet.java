package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.domain.User;
import net.hncu.bookstore.exception.UserException;
import net.hncu.bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LY on 2017/3/28.
 */
@WebServlet("/activeServlet")
public class ActiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取激活码
        String activeCode = request.getParameter("activeCode");
        //调用逻辑
        UserService us= new UserService();
        try {
            us.activeUser(activeCode);

        } catch (UserException e) {
            e.printStackTrace();
            response.getWriter().write("激活失败！");
        }
    }
}
