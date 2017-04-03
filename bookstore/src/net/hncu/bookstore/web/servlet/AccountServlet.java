package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LY on 2017/3/28.
 */
@WebServlet("/accountServlet")
public class AccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //从Session中取到user
        User user = (User)request.getSession().getAttribute("user");
        //分发转向
        if(user==null){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else{
            String path = "/myAccount.jsp";
            if(user.getRole().equals("admin"))
                path = "/admin/login/home.jsp";
            request.getRequestDispatcher(path).forward(request,response);
        }
    }
}
