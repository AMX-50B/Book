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
@WebServlet("/findUserByIdServlet")
public class FindUserByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取元素
        String id = request.getParameter("id");
        //调用逻辑
        UserService us = new UserService();
        try {
            User user = us.findUserById(id);
            request.setAttribute("user",user);
            request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request,response);
        } catch (UserException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }
    }
}
