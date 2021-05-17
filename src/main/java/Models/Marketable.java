package Models;

import Enums.BillingType;
import Enums.Category;
import Enums.CheckType;
import Interfaces.IMarketable;
import Utils.InputReader;
import Utils.TabularData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Marketable implements IMarketable {
    private ArrayList<Product> products;
    private ArrayList<Sale> sales;
    private ArrayList<Check> checks;


    public Marketable(){
        this.products = new ArrayList<Product>();
        this.sales = new ArrayList<Sale>();
        this.checks = new ArrayList<Check>();
    }

    @Override
    public List<Sale> sales() {
        return this.sales.stream().filter(
                p -> p.isSoftDeleted() == false).collect(Collectors.toList());
    }

    @Override
    public List<Product> products() {
        return this.products.stream().filter(
                p -> p.isSoftDeleted() == false).collect(Collectors.toList());
    }

    @Override
    public Sale addSale(ArrayList<SaleItem> saleItems) {
        Sale sale = new Sale(saleItems);
        this.sales.add(sale);
        Check check = new Check(sale.getPrice(), CheckType.SALE, BillingType.DEBIT);
        this.checks.add(check);
        return sale;
    }

    @Override
    public void removeSale(String saleNo) {
        Sale sale = getSaleByNumber(saleNo);
        sale.setSoftDeleted(true);
        Check check = new Check(sale.getPrice(), CheckType.SALEDELETE, BillingType.CREDIT);
        this.checks.add(check);
    }

    @Override
    public void revertSaleItem(String saleNo, String itemCode, int count) {
        Sale sale = getSaleByNumber(saleNo);
        SaleItem item = getSaleItemByCode(sale, itemCode);
        if (item == null) return;
        item.updateCount(count);
        sale.setPrice(sale.getTotalPrice());
        Check check = new Check(item.getPrice()*item.getCount(), CheckType.REVERT, BillingType.CREDIT);
        this.checks.add(check);
    }

    @Override
    public void getAllSales() {
        TabularData.showSales(sales());
    }

    @Override
    public void getSalesByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Sale> sales = this.sales().stream().filter(s ->
                s.getSaleDate().isAfter(startDate) && s.getSaleDate().isBefore(endDate)).collect(Collectors.toList());
        TabularData.showSales(sales);
    }

    @Override
    public void getSalesByDate(LocalDate saleDate) {

        List<Sale> sales = this.sales().stream().filter(s ->
                s.getSaleDate().getDayOfYear() == saleDate.getDayOfYear() && s.getSaleDate().getYear() == saleDate.getYear()).collect(Collectors.toList());
        TabularData.showSales(sales);
    }

    @Override
    public void getSalesByPriceRange(double minPrice, double maxPrice) {
        List<Sale> sales = this.sales().stream().filter(s ->
                s.getPrice() > minPrice && s.getPrice() < maxPrice).collect(Collectors.toList());
        TabularData.showSales(sales);
    }

    @Override
    public void getSaleByNo(String saleNo) {
        List<Sale> sales = this.sales().stream().filter(s ->
                s.getSaleNo().equals(saleNo)).collect(Collectors.toList());
        TabularData.showDetailedSales(sales);
    }

    @Override
    public Product addProduct(String name,  double cost, double price, int count, LocalDate buyDate, Category category) {
        Product product = new Product(name, cost, price, category,  count, buyDate);
        this.products.add(product);
        return product;
    }

    @Override
    public Product updateProductByImei(Product product, String name, double cost, double price, int count, Category category) {
        product.setName(name);
        product.setPrice(price);
        product.setCost(cost);
        product.setCategory(category);
        product.setCount(count);
        return product;
    }

    @Override
    public void getAllProducts() {
        TabularData.showProducts(products());
    }

    @Override
    public void getProductsByCategory(Category category) {
        List<Product> products = this.products().stream().filter(
                p -> p.getCategory().equals(category)).collect(Collectors.toList());
        TabularData.showProducts(products);
    }

    @Override
    public void getProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = this.products().stream().filter(
                p -> p.getPrice() > minPrice && p.getPrice() < maxPrice).collect(Collectors.toList());
        TabularData.showProducts(products);
    }

    @Override
    public void getProductsByName(String name) {
        List<Product> products = this.products().stream().filter(
                p -> p.getName().contains(name)).collect(Collectors.toList());
        TabularData.showProducts(products);
    }



    @Override
    public void deleteProductByImei(String imei) {
        Product product = getProductByImei(imei);
        if (product == null){
            return;
        }
        product.setSoftDeleted(true);
        System.out.println("Successfully deleted!");
    }

    public SaleItem addSaleItem(Product product, double price, int count){
        SaleItem saleItem = new SaleItem(product, price, count);
        product.decreaseCount(count);
        return saleItem;
    }

    public Product getProductByImei(String imei){
        for(Product pr: this.products){
            if (pr.getImei().equals(imei)){
                return pr;
            }
        }
        System.out.println("Can't find such product, please type again");
        return null;
    }

    public Sale getSaleByNumber(String saleNo){
        for (Sale sale: this.sales){
            if (sale.getSaleNo().equals(saleNo)){
                return sale;
            }
        }
        System.out.println("Can't find such sale, please type again");
        return null;
    }

    public SaleItem getSaleItemByCode(Sale sale, String itemCode){
        for (SaleItem item: sale.saleItems){
            if (item.getCode().equals(itemCode)){
                return item;
            }
        }
        System.out.println("Can't find such sale item");
        return null;
    }

}
