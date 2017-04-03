package net.hncu.bookstore.web.servlet;

import net.hncu.bookstore.domain.Order;
import net.hncu.bookstore.domain.OrderItem;
import net.hncu.bookstore.domain.Product;
import net.hncu.bookstore.domain.User;
import net.hncu.bookstore.exception.OrderException;
import net.hncu.bookstore.service.OrderService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by LY on 2017/4/1.
 */
@WebServlet("/orderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //package Order object
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setUser((User) request.getSession().getAttribute("user"));
        try {
            BeanUtils.populate(order,request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //get the data of the cart in session
        Map<Product,String> cart = (Map<Product,String>)request.getSession().getAttribute("cart");
        //Traverse the data of the  cart, add to the oderItems
        List<OrderItem> list = new ArrayList<OrderItem>();
        for(Product product : cart.keySet()){
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);//add Product to OrderItem
            orderItem.setBuy_num(Integer.parseInt(cart.get(product)));
            orderItem.setOrder(order);//add order to OrderItem
            list.add(orderItem);
        }
        //Put the collection in Order Object
        order.setList(list);
        //Calling business logic
        OrderService os = new OrderService();
        try {
            os.addOrder(order);
            request.setAttribute("order",order);
            request.getRequestDispatcher("/pay.jsp").forward(request,response);
        } catch (OrderException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }

    }
}
