package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.domain.User;
import net.hncu.bookstore.exception.UserException;
import org.apache.commons.beanutils.BeanUtils;
import net.hncu.bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

/**
 * 注册Servlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理验证码
        String ckcode=request.getParameter("ckcode");
        String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
       // System.out.print(ckcode);
        //如果验证码不符
        if(!checkcode_session.equals(ckcode)){
            request.setAttribute("ckcode_msg","验证码错误");
            request.getRequestDispatcher("register.jsp").forward(request,response);
            return;
        }
        //获取表单项
        User user = new User();
        try {
            BeanUtils.populate(user,request.getParameterMap());
            user.setActiveCode(UUID.randomUUID().toString());
        //调用业务逻辑
            UserService rs = new UserService();
            rs.register(user);
        //分发转向
            request.getRequestDispatcher("/registersuccess.jsp").forward(request,response);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (UserException e) {
            //插入失败，前台处理
            request.setAttribute("user_msg",e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request,response);
            return;
        }

    }
}
