package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.domain.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by LY on 2017/3/31.
 */
@WebServlet("/changeNumServlet")
public class ChangeNumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String num = request.getParameter("num");
        Product product = new Product();
        product.setId(id);
        HttpSession session = request.getSession();
        Map<Product,String> cart = (Map<Product, String>) session.getAttribute("cart");
        if("0".equals(num)){
            cart.remove(product);
        }
        if(cart.containsKey(product)){
            cart.put(product,num);
        }
        response.sendRedirect(request.getContextPath()+"/cart.jsp");
    }
}
