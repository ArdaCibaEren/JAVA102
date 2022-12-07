import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Notebook extends Product {
    public Notebook(int id, String productName, double unitPrice, String productBrand, int storage, double screenSize, int RAM, int stock, int discount) {
        super(id, productName, unitPrice, productBrand, storage, screenSize, RAM, stock, discount);
    }

    public static void addInfo() {
        notebookList.add(new Notebook(1, "Huawei Matebook 14", 7000, "Huawei", 512, 14.0, 16, 16, 8));
        notebookList.add(new Notebook(2, "Lenovo V14 IGL", 3699, "Lenovo", 1024, 14.0, 8, 4, 10));
        notebookList.add(new Notebook(3, "Asus Tuf Gaming", 8199, "Asus", 2048, 15.6, 32, 20, 5));
    }

    public static void printNotebook() {
        System.out.println("| ID | Name of Product               | Price     | Brand     | Storage  | Screen Size | RAM      |");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (int i = 0; i < notebookList.size(); i++) {
            Notebook notebook = notebookList.get(i);
            System.out.format("| %-2d | %-29s | %-9.1f | %-9s | %-7d | %-11.1f | %-8d |", notebook.getId(), notebook.getProductName(), notebook.getUnitPrice(), notebook.getProductName(), notebook.getStorage(), notebook.getScreenSize(), notebook.getRAM());
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------");
        }
    }

    public static void addNewNotebook() {
        int id = notebookList.size() + 1;
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
            notebookList.add(new Notebook(id, productName, unitPrice, productBrand, storage, screenSize, RAM, stock, discount));
        } catch (Exception e) {
            System.out.println("Hatalı giriş yaptınız!");
        }
    }

    public static void deleteById() {
        try {
            for (int i = 0; i < notebookList.size(); i++) {
                Notebook notebook = notebookList.get(i);
                System.out.format("| %-2d | %-29s ", notebook.getId(), notebook.getProductName());
            }
            System.out.println("\n");
            Scanner inp = new Scanner(System.in);
            System.out.println("Silmek istediğiniz ürünün ID'sini giriniz :");
            int selectedID = inp.nextInt();
            notebookList.remove(selectedID - 1);
        } catch (Exception e) {
            System.out.println("Hatalı giriş yaptınız!");
        }
    }

    public static void searchNotebook() {
        LinkedHashMap<Integer, String> list = Brand.getBrandList();

        list.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach((e) -> System.out.println(e.getKey() + " " + e.getValue()));

        Scanner inp = new Scanner(System.in);
        System.out.println("Filtrelemek istediğiniz markanın ID'sini seçiniz : ");
        int filterByBrand = inp.nextInt();
        for (int i = 0; i < notebookList.size(); i++) {
            Notebook notebook = notebookList.get(i);
            if (notebook.getProductBrand().equals(Brand.getNameByID(filterByBrand))) {
                System.out.format("| %-2d | %-29s ", notebook.getId(), notebook.getProductName());
            }
        }
        System.out.println("\n");
    }
}