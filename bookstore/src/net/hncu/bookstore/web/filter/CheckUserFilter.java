package net.hncu.bookstore.web.filter;

import net.hncu.bookstore.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LY on 2017/4/3.
 */
@WebFilter("/admin/*")
public class CheckUserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //处理业务
        //从session中把用户对象得到
        User user = (User) request.getSession().getAttribute("user");
        //判断当前用户权限
        if(user!=null){
            if(!"admin".equals(user.getRole())){
                response.getWriter().write("权限不足，你无法访问！");
                response.setHeader("refresh", "2;url="+request.getContextPath()+"/index.jsp");
                return;
            }
            //放行
            chain.doFilter(request, response);
        }

        response.sendRedirect(request.getContextPath()+"/login.jsp");


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
