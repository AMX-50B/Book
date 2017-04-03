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
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取元素
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //调用逻辑
        UserService us = new UserService();
        try {
            String path="/index.jsp";
            User user = us.login(username,password);
            if(user.getRole().equals("admin")) {
                path="/admin/login/home.jsp";
            }
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher(path).forward(request, response);

        } catch (UserException e) {
            e.printStackTrace();
            request.setAttribute("user_msg",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
