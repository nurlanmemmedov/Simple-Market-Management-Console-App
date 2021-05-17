package Models;
import Enums.Category;
import Utils.InputReader;

import java.time.LocalDate;
import java.util.ArrayList;

public class MarketConsole {

    private Marketable marketable;

    public static void main(String[] args) {
        MarketConsole marketConsole = new MarketConsole();
        marketConsole.marketable = new Marketable();
        Product product = marketConsole.marketable.addProduct("aa", 12, 13,2, LocalDate.now(), Category.FRUIT);
        Product product1 = marketConsole.marketable.addProduct("bb", 12, 13,2, LocalDate.now(), Category.DRINK);
        SaleItem item = new SaleItem(product, 12, 2);
        SaleItem item1 = new SaleItem(product1, 12, 2);
        ArrayList<SaleItem> items = new ArrayList<>();
        items.add(item);
        items.add(item1);
        Sale sale = marketConsole.marketable.addSale(items);
        marketConsole.start();
    }

    private int menu(){
        System.out.println("-------------------------------------------------------------------MARKET MENU-------------------------------------------------------------------");
        System.out.println("1.PROCESSES ON THE PRODUCTS");
        System.out.println("2.PROCESSES ON THE SALES");
        System.out.println("3.LOGOUT FROM SYSTEM");
        System.out.println("-------------------------------------------------------------------MARKET MENU-------------------------------------------------------------------");
        int choice = InputReader.readInt("Enter number of menu: ",1, 3);
        return choice;
    }

    private int productsMenu(){
        System.out.println("-------------------------------------------------------------------PRODUCT MENU-------------------------------------------------------------------");
        System.out.println("1. ADD NEW PRODUCT");
        System.out.println("2. UPDATE PRODUCT");
        System.out.println("3. DELETE PRODUCT");
        System.out.println("4. SHOW ALL PRODUCTS");
        System.out.println("5. SHOW ALL PRODUCTS BY CATEGORY");
        System.out.println("6. SHOW ALL PRODUCTS BY PRICE RANGE");
        System.out.println("7. SEARCH OVER PRODUCTS BY NAME");
        System.out.println("8. GO BACK");
        System.out.println("-------------------------------------------------------------------PRODUCT MENU-------------------------------------------------------------------");

        int choice = InputReader.readInt("Enter number of menu: ", 1, 8);
        return choice;
    }

    private int salesMenu(){
        System.out.println("-------------------------------------------------------------------SALE MENU-------------------------------------------------------------------");
        System.out.println("1. ADD NEW SALE");
        System.out.println("2. REVERT PRODUCT FROM SALE");
        System.out.println("3. DELETE SALE");
        System.out.println("4. SHOW ALL SALES");
        System.out.println("5. SHOW ALL SALES BY DATE RANGE");
        System.out.println("6. SHOW ALL SALES BY PRICE RANGE");
        System.out.println("7. SHOW ALL SALES BY DATE");
        System.out.println("8. GET SALE BY SALE NO");
        System.out.println("9. GO BACK");
        System.out.println("-------------------------------------------------------------------SALE MENU-------------------------------------------------------------------");
        int choice = InputReader.readInt("Enter number of menu: ",1, 10);
        return choice;
    }

    private void start(){
        while (true){
            int choice = menu();
            switch (choice){
                case 1:
                    this.ProcessProduct();
                case 2:
                    this.ProcessSale();
                case 3:
                    System.out.println("Log outed");
                    System.exit(0);
            }
        }
    }

    private void ProcessSale(){
        while (true){
            int choice = salesMenu();
            switch (choice){
                case 1:
                    addSale();
                    break;
                case 2:
                    revertSaleItem();
                    break;
                case 3:
                    deleteSale();
                case 4:
                    showAllSales();
                    break;
                case 5:
                    showSalesByDateRange();
                    break;
                case 6:
                    showSalesByPriceRange();
                    break;
                case 7:
                    showSalesByDate();
                    break;
                case 8:
                    showSalesBySaleNo();
                    break;
                case 9:
                    this.start();
            }
        }
    }

    private void ProcessProduct(){
        while (true){
            int choice = productsMenu();
            switch (choice){
                case 1:
                    this.addProduct();
                    break;
                case 2:
                    this.updateProduct();
                    break;
                case 3:
                    this.deleteProduct();
                    break;
                case 4:
                    this.showAllProducts();
                    break;
                case 5:
                    this.showProductsByCategory();
                    break;
                case 6:
                    this.showProductsByPriceRange();
                    break;
                case 7:
                    showProductsByName();
                    break;
                case 8:
                    this.start();
            }
        }
    }
    //region Product

    public void addProduct(){
        String name = InputReader.readString("Enter name: ");
        double cost = InputReader.readFloat("Enter cost: ",0, Float.MAX_VALUE);
        double price = InputReader.readFloat("Enter price: ",0, Float.MAX_VALUE);
        Category category = InputReader.readCategory();
        LocalDate buyDate = InputReader.readDate("Enter buying date in dd-mm-yyyy format: ");
        int count = InputReader.readInt("Enter count: ",0, Integer.MAX_VALUE);
        Product product = this.marketable.addProduct(name, cost, price, count, buyDate, category);
        System.out.println(product.getName() + " product is added");
    }

    public void updateProduct(){
        String currentImei = InputReader.readString("Enter imei code of product: ");
        Product currentProduct = this.marketable.getProductByImei(currentImei);
        if(currentProduct == null){
            return;
        }
        System.out.println("You are editing a product has name of "+ currentProduct.getName());
        String name = InputReader.readString("Enter new name: ");
        double cost = InputReader.readFloat("Enter new cost: ",0, Float.MAX_VALUE);
        double price = InputReader.readFloat("Enter new price: ",0, Float.MAX_VALUE);
        Category category = InputReader.readCategory();
        int count = InputReader.readInt("Enter new count: ",0, Integer.MAX_VALUE);
        Product product = this.marketable.updateProductByImei(currentProduct, name, cost, price, count, category);
        System.out.println(product.getName() + " product is edited");
    }

    public void deleteProduct(){
        String currentImei = InputReader.readString("Enter imei code of product: ");
        this.marketable.deleteProductByImei(currentImei);
    }

    public void showAllProducts(){
        this.marketable.getAllProducts();
    }

    public void showProductsByCategory(){
        Category category = InputReader.readCategory();
        this.marketable.getProductsByCategory(category);
    }

    public void showProductsByPriceRange(){
        float minPrice = InputReader.readFloat("Type minimum price ", 0, Float.MAX_VALUE);
        float maxPrice = InputReader.readFloat("Type maximum price ", 0, Float.MAX_VALUE);
        this.marketable.getProductsByPriceRange(minPrice, maxPrice);
    }

    public void showProductsByName(){
        String name = InputReader.readString("Type name of product ");
        this.marketable.getProductsByName(name);
    }
    //endregion

    // region Sale
    public void addSale(){
        ArrayList<SaleItem> saleItems = new ArrayList<>();
        boolean addAnother = false;
        do{
            String imei = InputReader.readString("Enter imei code of product: ");
            int count = InputReader.readInt("Enter count of product: ", 1, Integer.MAX_VALUE);
            Product product = this.marketable.getProductByImei(imei);
            saleItems.add(this.marketable.addSaleItem(product, product.getPrice(), count));
            addAnother = InputReader.readBoolean("Do you want to add another product");
        }while (addAnother);
        Sale sale = this.marketable.addSale(saleItems);
        System.out.println(sale.getSaleNo() + " Sale added");
    }

    public void showAllSales(){
        this.marketable.getAllSales();
    }

    public void showSalesByDateRange(){
        LocalDate startDate = InputReader.readDate("Enter start date: ");
        LocalDate endDate = InputReader.readDate("Enter end date: ");
        this.marketable.getSalesByDateRange(startDate, endDate);
    }

    public void showSalesByDate(){
        LocalDate date = InputReader.readDate("Enter date: ");
        this.marketable.getSalesByDate(date);
    }

    public void showSalesByPriceRange(){
        Float minPrice = InputReader.readFloat("Enter minimum price: ", 0 , Float.MAX_VALUE);
        Float maxPrice = InputReader.readFloat("Enter maximum price: ", 0 , Float.MAX_VALUE);
        this.marketable.getSalesByPriceRange(minPrice, maxPrice);
    }

    public void showSalesBySaleNo(){
        String saleNo = InputReader.readString("Enter sale no: ");
        this.marketable.getSaleByNo(saleNo);
    }

    public void revertSaleItem(){
        String saleNo = InputReader.readString("Please enter number of sale: ");
        if(marketable.getSaleByNumber(saleNo) == null) return;
        String itemCode = InputReader.readString("Please enter code of sale item: ");
        int count = InputReader.readInt("Please enter count of sale item: ", 0, Integer.MAX_VALUE);
        marketable.revertSaleItem(saleNo, itemCode, count);
    }

    public void deleteSale(){
        String saleNo = InputReader.readString("Enter code of sale: ");
        this.marketable.removeSale(saleNo);
    }

    // endregion
}
