package Models;

import Utils.CodeGenerator;

public class SaleItem {
    private int id;
    private String code;
    private Product product;
    private double price;
    private int count;
    private boolean softDeleted;

    public SaleItem(Product product, double price, int count) {
        code = CodeGenerator.generateRandomCode("SI", 5);
        this.product = product;
        this.price = price;
        this.count = count;
        this.softDeleted = false;
    }

    public String getProductName(){
        if (this.product == null) return null;
        return this.product.getName();
    }

    public int updateCount(int count){
        if (count > this.count){
            System.out.println("Count must be less than the count of item");
        };
        return this.count -= count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price*count;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSoftDeleted() {
        return softDeleted;
    }

    public void setSoftDeleted(boolean softDeleted) {
        this.softDeleted = softDeleted;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
