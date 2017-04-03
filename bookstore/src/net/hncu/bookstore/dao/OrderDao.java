package net.hncu.bookstore.dao;

import net.hncu.bookstore.domain.Order;
import net.hncu.bookstore.domain.OrderItem;
import net.hncu.bookstore.domain.Product;
import net.hncu.bookstore.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LY on 2017/4/1.
 */

public class OrderDao {
    public void addOrder(Order order) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";
        qr.update(sql,order.getId(),order.getMoney(),order.getRecieverAddress(),
                order.getRecieverName(),order.getRecieverPhone(),order.getPayState(),
                order.getOrderTime(),order.getUser().getId());
    }

    public List<Order> searchOrderByUserId(int id) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from orders where user_id = ?",new BeanListHandler<Order>(Order.class),id);
    }

    public Order searchOrderInfoById(String order_id) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        Order order = qr.query("select * from orders where id = ?",new BeanHandler<Order>(Order.class),order_id);
        String sql = "select * from orderitem o,products p where o.product_id=p.id and o.order_id = ?";
        List<OrderItem> list =qr.query(sql, new ResultSetHandler<List<OrderItem>>() {
            @Override
            public List<OrderItem> handle(ResultSet resultSet) throws SQLException {
                List<OrderItem> list = new ArrayList<OrderItem>();
                while (resultSet.next()) {
                    OrderItem oi = new OrderItem();
                    oi.setBuy_num(resultSet.getInt("buy_num"));
                    Product p = new Product();
                    p.setName(resultSet.getString("name"));
                    p.setPrice(resultSet.getDouble("price"));
                    oi.setProduct(p);
                    list.add(oi);
                }
                return list;
            }
        },order_id);
        order.setList(list);
        return order;
    }

    public void changeOrderPayState(String r6_order) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("UPDATE orders SET  payState = 1 WHERE id = ?",r6_order);

    }
}
