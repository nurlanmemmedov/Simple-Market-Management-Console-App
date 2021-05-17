package Models;

import Enums.BillingType;
import Enums.CheckType;
import Utils.CodeGenerator;

import java.time.LocalDate;

public class Check {
    private String code;
    private double price;
    private CheckType checkType;
    private BillingType billingType;
    private LocalDate date;

    public Check(double price, CheckType checkType, BillingType billingType){
        code = CodeGenerator.generateRandomCode("CH", 10);
        price = price;
        checkType = checkType;
        billingType = billingType;
        date = LocalDate.now();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CheckType getCheckType() {
        return checkType;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }

    public BillingType getBillingType() {
        return billingType;
    }

    public void setBillingType(BillingType billingType) {
        this.billingType = billingType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
