package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.domain.Product;
import net.hncu.bookstore.exception.ProductException;
import net.hncu.bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LY on 2017/3/29.
 */
@WebServlet("/showBookListForAdmin")
public class ShowBookListForAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService ps = new ProductService();
        List<Product> list = new ArrayList<Product>();
        try {
            list= ps.showBookListForAdmin();
            request.setAttribute("list",list);
            request.getRequestDispatcher("/admin/products/list.jsp").forward(request,response);
        } catch (ProductException e) {
            e.printStackTrace();
            response.setContentType("text/html,charset=utf-8");
            response.getWriter().write(e.getMessage());
        }
    }
}
