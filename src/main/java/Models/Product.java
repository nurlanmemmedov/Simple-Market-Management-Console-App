package Models;

import Enums.Category;
import Utils.CodeGenerator;

import java.time.LocalDate;
import java.util.Date;

public class Product {
    private String name;
    private String imei;
    private double cost;
    private double price;
    private Category category;
    private int count;
    private LocalDate buyDate;
    private boolean softDeleted;

    public Product(String name,  double cost, double price, Category category, int count, LocalDate buyDate) {
        this.name = name;
        this.imei = CodeGenerator.generateRandomCode("IM", 10);
        this.cost = cost;
        this.price = price;
        this.category = category;
        this.count = count;
        this.buyDate = buyDate;
        this.softDeleted = false;
    }

    public void decreaseCount(int count){
        this.count -= count;
    }

    public void increaseCount(int count){
        this.count += count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public boolean isSoftDeleted() {
        return softDeleted;
    }

    public void setSoftDeleted(boolean softDeleted) {
        this.softDeleted = softDeleted;
    }
}
