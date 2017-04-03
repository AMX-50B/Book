package net.hncu.bookstore.domain;

/**
 * Created by LY on 2017/3/27.
 */
public class OrderItem {
    private Order order;
    private Product product;
    private int buy_num;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getBuy_num() {
        return buy_num;
    }

    public void setBuy_num(int buy_num) {
        this.buy_num = buy_num;
    }
}
