package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.domain.Product;
import net.hncu.bookstore.exception.ProductException;
import net.hncu.bookstore.service.ProductService;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by LY on 2017/3/31.
 */
@WebServlet("/findProductByAjax")
public class FindProductByAjax extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name =request.getParameter("name");
        ProductService ps = new ProductService();
        try {
            List<Object> list = ps.findBooksByName(name);
            String str = JSONArray.fromObject(list).toString();
            if("".equals(name)||name==null) {
                str = "";
            }
            response.getWriter().write(str);
        } catch (ProductException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }
    }
}
