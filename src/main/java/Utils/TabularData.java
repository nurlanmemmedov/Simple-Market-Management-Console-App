package Utils;

import Models.Product;
import Models.Sale;
import Models.SaleItem;

import java.util.ArrayList;
import java.util.List;

public class TabularData {

    public static void printProductHeader() {
        System.out.println(String.format("%10s %10s %10s %10s %10s %10s %10s %10s %10s %10s %10s", "Imei", "|", "Name", "|", "Category", "|", "Count", "|", "Cost", "|", "Price"));
        System.out.println(String.format("%s", "--------------------------------------------------------------------------------------------------------------------------------"));
    }

    public static void printProduct(Product product) {
        System.out.println(String.format("%10s %10s %10s %10s %10s %10s %10s %10s %10s %10S %10S",  product.getImei(), "|", product.getName(), "|", product.getCategory(), "|", product.getCount(), "|", product.getCost(),  "|", product.getPrice()));
    }
    public static void showProducts(List<Product> products){
        printProductHeader();
        for (Product product : products){
            printProduct(product);
        }
    }

    public static void printSaleHeader() {
        System.out.println(String.format("%10s %10s %10s %10s %10s %10s %10s", "NO", "|", "Price", "|", "Item Count", "|", "Date"));
        System.out.println(String.format("%s", "--------------------------------------------------------------------------------------------------------------------------------"));
    }

    public static void printSale(Sale sale) {
        System.out.println(String.format("%10s %10s %10s %10s %10s %10s %10s",  sale.getSaleNo(), "|", sale.getPrice(), "|", sale.getSaleItems().size(), "|", sale.getSaleDate()));
    }
    public static void showSales(List<Sale> sales){
        printSaleHeader();
        for (Sale sale : sales){
            printSale(sale);
        }
    }

    public static void printDetailedSaleHeader() {
        System.out.println(String.format("%10s %10s %10s %10s %10s %10s %10s %10s %10s", "NO", "|", "Price", "|", "Item Count", "|", "Date", "|", "Items"));
        System.out.println(String.format("%s", "--------------------------------------------------------------------------------------------------------------------------------"));
    }
    public static String printSaleItem(Sale sale){
        StringBuilder itemInfo = new StringBuilder();
        for (SaleItem item : sale.getSaleItems()){
            itemInfo.append("No: "+item.getCode() + " Product: "+ item.getProductName() + " count "+ item.getCount()+"   ");
        }
        return itemInfo.toString();
    }
    public static void printDetailedSale(Sale sale) {
        System.out.println(String.format("%10s %10s %10s %10s %10s %10s %10s %10s %10s",  sale.getSaleNo(), "|", sale.getPrice(), "|", sale.getSaleItems().size(), "|", sale.getSaleDate(), "|", printSaleItem(sale)));
    }
    public static void showDetailedSales(List<Sale> sales){
        printDetailedSaleHeader();
        for (Sale sale : sales){
            printDetailedSale(sale);
        }
    }
}
