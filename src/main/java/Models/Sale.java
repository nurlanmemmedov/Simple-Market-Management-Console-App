package Models;

import Utils.CodeGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Sale {
    private String saleNo;
    private double price;
    public ArrayList<SaleItem> saleItems;
    private LocalDate saleDate;
    private boolean softDeleted;

    public Sale(ArrayList<SaleItem> saleItems) {
        this.saleNo = CodeGenerator.generateRandomCode("SL", 10);
        this.saleItems = saleItems;
        this.price = getTotalPrice();
        this.saleDate = LocalDate.now();
        this.softDeleted = false;
    }

    public double getTotalPrice(){
        double sum = 0;
        for (SaleItem saleItem: saleItems){
            sum += saleItem.getPrice();
        }
        return sum;
    }


    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public ArrayList<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(ArrayList<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public boolean isSoftDeleted() {
        return softDeleted;
    }

    public void setSoftDeleted(boolean softDeleted) {
        this.softDeleted = softDeleted;
    }
}
