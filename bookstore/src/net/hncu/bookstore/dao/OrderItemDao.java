package net.hncu.bookstore.dao;

import net.hncu.bookstore.domain.Order;
import net.hncu.bookstore.domain.OrderItem;
import net.hncu.bookstore.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by LY on 2017/4/1.
 */

public class OrderItemDao {
    public void addOrdetItem(Order order) throws SQLException {
        List<OrderItem> orderItem = order.getList();
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        String sql = "insert into orderitem values(?,?,?)";
        Object[][] paras = new Object[orderItem.size()][];
        for(int i=0;i<orderItem.size();i++){
            paras[i] = new Object[]{order.getId(),orderItem.get(i).getProduct().getId(),orderItem.get(i).getBuy_num()};
        }
        qr.batch(sql,paras);
    }
}
