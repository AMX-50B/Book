package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.domain.Product;
import net.hncu.bookstore.exception.ProductException;
import net.hncu.bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LY on 2017/3/30.
 */
@WebServlet("/addProductCartServlet")
public class AddProductCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();

        ProductService ps = new ProductService();
        Product product = null;
        try {
           product = ps.showBooksById(id);
        } catch (ProductException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }
        Map<Product,String> cart = (Map<Product,String>)session.getAttribute("cart");
        int num =1;
        if(cart==null){
            cart = new HashMap<Product,String>();
        }
        if (cart.containsKey(product)){
            num = Integer.parseInt(cart.get(product)+1);
        }
        cart.put(product,num+"");
        session.setAttribute("cart",cart);
        response.sendRedirect(request.getContextPath()+"/addToCartSuccess.jsp");
    }
}
