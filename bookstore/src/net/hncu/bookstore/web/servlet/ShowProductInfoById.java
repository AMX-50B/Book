package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.domain.Product;
import net.hncu.bookstore.exception.ProductException;
import net.hncu.bookstore.service.ProductService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Created by LY on 2017/3/29.
 */
@WebServlet("/showProductInfoById")
public class ShowProductInfoById extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //get parameter
        String id = request.getParameter("id");
        //do service
        ProductService pd = new ProductService();
        try {
            Product product = pd.showBooksById(id);
            //do dispacher
            //System.out.print(product.getName());
            request.setAttribute("book",product);
            request.getRequestDispatcher("/product_info.jsp").forward(request,response);
        } catch (ProductException e) {
            e.printStackTrace();
            //exception handling
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(e.getMessage());
        }

    }
}
