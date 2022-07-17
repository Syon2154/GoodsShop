package com.goods.shop.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    @Column(name = "order_time")
    private LocalDateTime orderTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Order order = (Order) object;

        return Objects.equals(id, order.id)
                && Objects.equals(user, order.user)
                && Objects.equals(products, products)
                && Objects.equals(status, order.status)
                && Objects.equals(orderTime, order.orderTime);
    }

    @Override
    public int hashCode() {
       return Objects.hash(id, user, products,
               status, orderTime);
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", user=" + user
                + ", products=" + products
                + ", status=" + status
                + ", orderTime=" + orderTime
                + '}';
    }

    public enum OrderStatus {
        PAID,
        UNPAID
    }
}
