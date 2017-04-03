package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.domain.Order;
import net.hncu.bookstore.domain.OrderItem;
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
@WebServlet("/showOrderInfoServlet")
public class ShowOrderInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order_id = request.getParameter("id");
        OrderService os = new OrderService();
        try {
            Order order = os.getOrderInfoById(order_id);
            request.setAttribute("order",order);
            request.getRequestDispatcher("/orderInfo.jsp").forward(request,response);
        } catch (OrderException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }
    }
}
