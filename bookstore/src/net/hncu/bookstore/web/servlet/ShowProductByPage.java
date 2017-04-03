package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.domain.PageBean;
import net.hncu.bookstore.exception.ProductException;
import net.hncu.bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LY on 2017/3/28.
 */
@WebServlet("/showProductByPage")
public class ShowProductByPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get parameter
       //request.setCharacterEncoding("utf-8");
       String category = request.getParameter("category");
       //System.out.print(category);
       String currPage = request.getParameter("currPage");
        //doservice
        ProductService ps = new ProductService();
        try {
            PageBean pb = ps.showBooksByPage(category,currPage);
            request.setAttribute("pb",pb);
            request.getRequestDispatcher("/product_list.jsp").forward(request,response);
        } catch (ProductException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }

    }
}
