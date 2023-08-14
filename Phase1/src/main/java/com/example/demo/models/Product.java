package com.example.demo.models;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private String id;

    @Column(nullable = false, unique = true, updatable = false)
    private String name;

    @Column(columnDefinition = "")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;
    private long creationTime;
    private long lastUpdateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(updatable = false)
    private String version;

    public Product() {
    }

    public Product(String id, String name, String description, BigDecimal price, String  category, String version) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.creationTime = Date.valueOf(LocalDate.now()).getTime();
        this.lastUpdateTime = Date.valueOf(LocalDate.now()).getTime();
        this.category = Category.valueOf(category);
        this.version = version;
    }

    public Product(String name, String description, BigDecimal price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.lastUpdateTime = Date.valueOf(LocalDate.now()).getTime();
        this.version = "1.0";
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCategory() {
        return category.name();
    }

    public void setCategory(String category) {
        this.category = Category.valueOf(category);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", creationTime=" + creationTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", category=" + category +
                ", version='" + version + '\'' +
                '}';
    }
}
