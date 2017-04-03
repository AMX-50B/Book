package net.hncu.bookstore.domain;

import java.util.Date;
import java.util.List;

/**
 * Created by LY on 2017/3/27.
 */
public class Order {
    private String id;
    private double money;
    private String recieverAddress;
    private String recieverName;
    private String recieverPhone;
    private String payState;
    private Date orderTime;
    private List<OrderItem> list;
    private User user;

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public List<OrderItem> getList() {
        return list;
    }

    public void setList(List<OrderItem> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getRecieverAddress() {
        return recieverAddress;
    }

    public void setRecieverAddress(String receiverAddress) {
        this.recieverAddress = receiverAddress;
    }

    public String getRecieverPhone() {
        return recieverPhone;
    }

    public void setRecieverPhone(String recieverPhone) {
        this.recieverPhone = recieverPhone;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
