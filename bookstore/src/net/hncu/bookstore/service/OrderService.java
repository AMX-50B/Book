package net.hncu.bookstore.service;

import net.hncu.bookstore.dao.OrderDao;
import net.hncu.bookstore.dao.OrderItemDao;
import net.hncu.bookstore.dao.ProductDao;
import net.hncu.bookstore.domain.Order;
import net.hncu.bookstore.exception.OrderException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by LY on 2017/4/1.
 */

public class OrderService {
    ProductDao pd = new ProductDao();
    OrderDao od = new OrderDao();
    OrderItemDao oid = new OrderItemDao();
    public void addOrder(Order order) throws OrderException {
        try {
            od.addOrder(order);
            oid.addOrdetItem(order);
            pd.updatePnum(order);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderException("get data failed");
        }
    };

    public List<Order> getOrderByUserId(int id) throws OrderException {
        try {
            return od.searchOrderByUserId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderException("get data failed");
        }
    }

    public Order getOrderInfoById(String order_id) throws OrderException {
        try {
            return od.searchOrderInfoById(order_id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderException("get data failed");
        }
    }

    public void modifyOrderState(String r6_order) throws OrderException {
        try {
            od.changeOrderPayState(r6_order);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderException("change data failed");
        }
    }
}
