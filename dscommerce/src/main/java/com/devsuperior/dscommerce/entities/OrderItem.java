package com.devsuperior.dscommerce.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private Double price;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        id.setOrder(order); // ATRIBUINDO PARA A CLASSE PK
        id.setProduct(product); // ATRIBUINDO PARA A CLASSE PK
        this.quantity = quantity;
        this.price = price;
    }

    // feito na mão
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Product getProduct() {
        return id.getProduct();
    }
    // fechando feito na mão

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
