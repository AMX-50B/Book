package net.hncu.bookstore.dao;

import net.hncu.bookstore.domain.Order;
import net.hncu.bookstore.domain.OrderItem;
import net.hncu.bookstore.domain.Product;
import net.hncu.bookstore.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LY on 2017/3/27.
 */
public class ProductDao {
    public int booksCount(String category) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        String sql ="select count(*) from products";
        //如果category不是空，就把条件加上
        if(!"".equals(category)){
            sql+=" where category='"+category+"'";
        }
        long l =  (Long)qr.query(sql, new ScalarHandler(1));
        return (int)l;

    }

    public List<Product> searchBooksByCategoryWithLimit(int currentPage, String category, int pageSize) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        String sql = "select * from products where 1=1 ";
        List list =new ArrayList();
        if(!"".equals(category)){
            sql+="and category = ?";
            list.add(category);
        }
        sql+=" limit ?,?";
        list.add((currentPage-1)*pageSize);
        list.add(pageSize);
       // System.out.print(sql+category+currentPage+ pageSize);
        return qr.query(sql,new BeanListHandler<Product>(Product.class),list.toArray());
    }

    public Product searchProductInfoById(String id) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from products where id = ?",new BeanHandler<Product>(Product.class),id);
    }

    public List<Product> searchBookList() throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from products ",new BeanListHandler<Product>(Product.class));
    }

    public void addProduct(Product product) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("INSERT INTO products VALUES (?,?,?,?,?,?,?)", product.getId(),product.getName(),product.getPrice(),
                product.getCategory(),product.getPnum(),product.getImgUrl(),product.getDescription());
    }

    public void deleteProductById(String id) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("delete from products where id = ?",id);
    }

    public List<Product> searchProductByItems(String id, String category, String name,
                         String minprice, String maxprice) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        String sql="select * from products where 1=1 ";
        List<String> list = new ArrayList<String>();
        if(!"".equals(id)){
            sql+="and id like ? ";
            list.add("%"+id+"%");
        }
        if(!"".equals(category)){
            sql+="and category like ? ";
            list.add(category);
        }
        if(!"".equals(name)){
            sql+="and name like ? ";
            list.add("%"+name+"%");
        }
        if(!"".equals(minprice)){
            sql+="and price> ? ";
            list.add(minprice);
        }
        if(!"".equals(maxprice)){
            sql+="and price< ? ";
            list.add(maxprice);
        }
        return qr.query(sql,new BeanListHandler<Product>(Product.class),list.toArray());
    }

    public List<Object> searchBookSByName(String name) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from products where name like ?",new ColumnListHandler(2),"%"+name+"%");
    }

    public Product searchProductByName(String name) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from products where name=?",new BeanHandler<Product>(Product.class),name);
    }

    public void updatePnum(Order order) throws SQLException {
        List<OrderItem> list = order.getList();
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        Object[][] paras = new Object[list.size()][];
        for(int i=0;i<list.size();i++){
            paras[i] = new Object[]{list.get(i).getBuy_num(),list.get(i).getProduct().getId()};
        }
        String sql = "UPDATE products SET pnum = pnum-? WHERE id = ?";
        qr.batch(sql,paras);
    }
}
