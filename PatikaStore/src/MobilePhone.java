import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MobilePhone extends Product {
    private String colour;
    private int battery, camera;

    public MobilePhone(int id, String productName, int unitPrice, String productBrand, int storage, double screenSize, int RAM, int stock, int discount, int battery, int camera, String colour) {
        super(id, productName, unitPrice, productBrand, storage, screenSize, RAM, stock, discount);
        this.battery = battery;
        this.camera = camera;
        this.colour = colour;
    }

    public static void addInfo() {
        phoneList.add(new MobilePhone(1, "SAMSUNG GALAXY A51", 3199, "Samsung", 128, 6.5, 128, 30, 6, 4000, 32, "Siyah"));
        phoneList.add(new MobilePhone(2, "iPhone 11 64 GB", 7379, "Apple", 64, 6.1, 64, 50, 6, 3046, 5, "Mavi"));
        phoneList.add(new MobilePhone(3, "Redmi Note 10 Pro 8GB", 4012, "Xiaomi", 128, 6.5, 128, 25, 12, 4000, 35, "Beyaz"));
    }

    public static void printPhone() {
        System.out.println("| ID | Name of Product               | Price     | Brand     | Memory    | Screen Size | Camera    | Battery   | RAM       | Color      | ");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (int i = 0; i < phoneList.size(); i++) {
            MobilePhone current = phoneList.get(i);
            System.out.format("| %-2d | %-29s | %-9.1f | %-9s | %-9d | %-11.1f | %-9d | %-9d | %-9d | %-10s |", current.getId(), current.getProductName(), current.getUnitPrice(), current.getProductBrand(), current.getStorage(), current.getScreenSize(), current.getRAM(), current.getBattery(), current.getCamera(), current.getColour());
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------");
        }
    }

    public static void addNewPhone() {
        int id = phoneList.size() + 1;
        try {
            Scanner inp = new Scanner(System.in);
            System.out.println("Ürün adını giriniz : ");
            String productName = inp.nextLine();
            System.out.println("Ürünün marka bilgisini giriniz : ");
            String productBrand = inp.nextLine();
            System.out.println("Ürün fiyatını giriniz : ");
            int unitPrice = inp.nextInt();
            System.out.println("Depolama bilgisi giriniz : ");
            int storage = inp.nextInt();
            System.out.println("Ekran ölçüsü giriniz : ");
            double screenSize = inp.nextDouble();
            System.out.println("RAM bilgisini giriniz : ");
            int RAM = inp.nextInt();
            System.out.println("Stok miktarı giriniz : ");
            int stock = inp.nextInt();
            System.out.println("İndirim bilgisi giriniz : ");
            int discount = inp.nextInt();
            System.out.println("Pil bilgisi giriniz : ");
            int battery = inp.nextInt();
            System.out.println("Kamera bilgisi giriniz : ");
            int camera = inp.nextInt();
            System.out.println("Renk bilgisi giriniz : ");
            String colour = inp.next();
            phoneList.add(new MobilePhone(id, productName, unitPrice, productBrand, storage, screenSize, RAM, stock, discount, battery, camera, colour));
        } catch (Exception e) {
            System.out.println("Hatalı giriş yaptınız!");
        }
    }

    public static void searchPhone() {
        LinkedHashMap<Integer, String> list = Brand.getBrandList();

        list.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach((e) -> System.out.println(e.getKey() + " " + e.getValue()));

        Scanner inp = new Scanner(System.in);
        System.out.println("Filtrelemek istediğiniz markanın ID'sini seçiniz : ");
        int filterByBrand = inp.nextInt();
        for (int i = 0; i < phoneList.size(); i++) {
            MobilePhone phone = phoneList.get(i);
            if (phone.getProductBrand().equals(Brand.getNameByID(filterByBrand))) {
                System.out.format("| %-2d | %-29s ", phone.getId(), phone.getProductName());
            }
        }
        System.out.println("\n");
    }

    public static void deleteById() {
        try {
            for (int i = 0; i < phoneList.size(); i++) {
                MobilePhone phone = phoneList.get(i);
                System.out.format("| %-2d | %-29s ", phone.getId(), phone.getProductName());
            }
            System.out.println("\n");
            Scanner inp = new Scanner(System.in);
            System.out.println("Silmek istediğiniz ürünün ID'sini giriniz :");
            int selectedID = inp.nextInt();
            phoneList.remove(selectedID - 1);
        } catch (Exception e) {
            System.out.println("Hatalı giriş yaptınız!");
        }
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public int getCamera() {
        return camera;
    }

    public void setCamera(int camera) {
        this.camera = camera;
    }
}
