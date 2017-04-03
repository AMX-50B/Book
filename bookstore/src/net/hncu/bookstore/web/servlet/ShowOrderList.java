package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.domain.Order;
import net.hncu.bookstore.domain.User;
import net.hncu.bookstore.exception.OrderException;
import net.hncu.bookstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by LY on 2017/4/1.
 */
@WebServlet("/showOrderList")
public class ShowOrderList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        int id = user.getId();
        OrderService os = new OrderService();
        try {
            List<Order> list = os.getOrderByUserId(id);
            System.out.println(list.get(0).getRecieverName());
            request.setAttribute("list",list);
            request.getRequestDispatcher("/orderlist.jsp").forward(request,response);
        } catch (OrderException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }
    }
}
