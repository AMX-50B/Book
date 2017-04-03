package net.hncu.bookstore.domain;

import java.util.List;

/**
 * Created by LY on 2017/3/27.
 */
public class PageBean {
    private int currentPage;
    private int totalCount;
    private int totalPage;
    private int pageSize;
    private List<Product> ps;
    private String category;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Product> getPs() {
        return ps;
    }

    public void setPs(List<Product> ps) {
        this.ps = ps;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
