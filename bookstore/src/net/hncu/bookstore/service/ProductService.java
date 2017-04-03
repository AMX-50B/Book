package net.hncu.bookstore.service;

import net.hncu.bookstore.dao.ProductDao;
import net.hncu.bookstore.domain.PageBean;
import net.hncu.bookstore.domain.Product;
import net.hncu.bookstore.exception.ProductException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LY on 2017/3/28.
 */

public class ProductService {
    ProductDao pd = new ProductDao();

    /**
     * find books by category with limit
     * @param category
     * @param currPage
     * @return
     * @throws ProductException
     */
    public PageBean showBooksByPage(String category, String currPage) throws ProductException {
        int pageSize = 4;
        int currentPage = 1;
        PageBean pageBean = new PageBean();
        if(category==null){
            category = "";
        }
        if(currPage!=null&&!"".equals(currPage)){
            currentPage=Integer.parseInt(currPage);
        }
        int count = 0 ;
        int tatolPage = 0;
        List<Product> list = new ArrayList<Product>();
        try {
            count = pd.booksCount(category);

            tatolPage = (int)Math.ceil(count*1.0/pageSize);
            //System.out.println(currentPage);
             list= pd.searchBooksByCategoryWithLimit(currentPage,category,pageSize);
            //System.out.print(list.get(0).getName());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ProductException("显示错误！"+category);
        }
        pageBean.setCategory(category);
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPs(list);
        pageBean.setTotalCount(count);
        pageBean.setTotalPage(tatolPage);
        return pageBean;
    }

    /**
     * find book by id
     * @param id
     * @return
     * @throws ProductException
     */
    public Product showBooksById(String id) throws ProductException {
        Product book =null;
        try {
            book = pd.searchProductInfoById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ProductException("search datasource failed!");
        }
        return book;
    }

    //get book list
    public List<Product> showBookListForAdmin() throws ProductException {
        List<Product> list = new ArrayList<>();
        try {
            list = pd.searchBookList();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ProductException("search datasource failed");
        }
        return list;
    }

    public void addBooks(Product book) throws ProductException {
        try {
            pd.addProduct(book);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ProductException("add book failed");
        }
    }

    public void deleteProductByIds(String[] ids) throws ProductException {
        for(int i =0;i<ids.length;i++) {
            try {
                pd.deleteProductById(ids[i]);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ProductException("delete from datasource failed");
            }
        }
    }

    public List<Product> getProductByItems(String id, String category, String name, String minprice, String maxprice) throws ProductException {
        //List<Product> list = new ArrayList<Product>();
        try {
            return pd.searchProductByItems(id,category,name,minprice,maxprice);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ProductException("get data failed");
        }
    }

    public List<Object> findBooksByName(String name) throws ProductException {
        try {
          return  pd.searchBookSByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ProductException("get data failed");
        }
    }

    public Product getBookByName(String name) throws ProductException {
        try {
            return pd.searchProductByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ProductException("get data failed");
        }
    }
}
