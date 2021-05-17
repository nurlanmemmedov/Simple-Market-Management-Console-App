package Interfaces;
import Enums.Category;
import Models.Product;
import Models.Sale;
import Models.SaleItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IMarketable {
    List<Sale> sales();
    List<Product> products();

    Sale addSale(ArrayList<SaleItem> saleItems);
    void removeSale(String saleNo);
    void revertSaleItem(String saleNo, String itemCode, int count);
    void getAllSales();
    void getSalesByDateRange(LocalDate startDate, LocalDate endDate);
    void getSalesByDate(LocalDate saleDate);
    void getSalesByPriceRange(double minPrice, double maxPrice);
    void getSaleByNo(String saleNo);

    Product addProduct(String name, double cost, double price, int count, LocalDate buyDate, Category category);
    Product updateProductByImei(Product product, String name, double cost, double price, int count, Category category);
    void deleteProductByImei(String imei);
    void getAllProducts();
    void getProductsByCategory(Category category);
    void getProductsByPriceRange(double minPrice, double maxPrice);
    void getProductsByName(String name);
}
