package com.goods.shop.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @OneToMany
    @JoinTable(name = "shopping_carts_products",
            joinColumns = @JoinColumn(name = "shopping_cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        ShoppingCart shoppingCart = (ShoppingCart) object;

        return Objects.equals(id, shoppingCart.id)
                && Objects.equals(user, shoppingCart.user)
                && Objects.equals(products, products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, products != null ? products : 0);
    }

    @Override
    public String toString() {
        return "ShoppingCart{"
                + "id=" + id
                + ", products=" + products
                + ", user=" + user
                + '}';
    }
}
