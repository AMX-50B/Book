package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.exception.ProductException;
import net.hncu.bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LY on 2017/3/30.
 */
@WebServlet("/deleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ids = request.getParameterValues("ids");
        System.out.println(ids.toString());
        ProductService ps =new ProductService();
        try {
            ps.deleteProductByIds(ids);
            request.getRequestDispatcher("showBookListForAdmin").forward(request,response);
        } catch (ProductException e) {
            e.printStackTrace();
            response.setContentType("text/html,charset=utf-8");
            response.getWriter().write(e.getMessage());
        }
    }
}
