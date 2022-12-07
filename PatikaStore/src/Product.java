import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Product {
    private String productName, productBrand;
    private int id, discount, stock, RAM, storage, memory;
    private double unitPrice, screenSize;

    static ArrayList<Integer> idList = new ArrayList<>();
    public static List<Notebook> notebookList = new ArrayList<>();
    public static List<MobilePhone> phoneList = new ArrayList<>();
    static HashMap<String, HashMap<Integer, Product>> productList = new HashMap<>();

    Product(int id, String productName, double unitPrice, String productBrand, int storage, double screenSize, int RAM, int stock, int discount) {
        this.id = id;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.productBrand = productBrand;
        this.storage = storage;
        this.screenSize = screenSize;
        this.RAM = RAM;
        this.stock = stock;
        this.discount = discount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public static ArrayList<Integer> getIdList() {
        return idList;
    }

    public static void setIdList(ArrayList<Integer> idList) {
        Product.idList = idList;
    }

    public static HashMap<String, HashMap<Integer, Product>> getProductList() {
        return productList;
    }

    public static void setProductList(HashMap<String, HashMap<Integer, Product>> productList) {
        Product.productList = productList;
    }
}