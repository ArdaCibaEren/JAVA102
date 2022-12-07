import java.util.Scanner;

public class PatikaStore {
    public static void run() {
        int select, select2, select3;
        Scanner inp = new Scanner(System.in);
        while (true) {
            System.out.println("PatikaStore Ürün Yönetim Paneli\n");
            System.out.println("1 - Notebook İşlemleri");
            System.out.println("2 - Cep Telefonu İşlemleri");
            System.out.println("3 - Marka Listele");
            System.out.println("0 - Çıkış Yap");
            System.out.print("Seçiminiz : ");
            select = inp.nextInt();

            switch (select) {
                case 1:
                    System.out.println("Notebook işlemleri");
                    System.out.println("1-Ürünleri görüntüle");
                    System.out.println("2-Ürün ekle");
                    System.out.println("3- Ürün sil");
                    System.out.println("4- Ürün filtrele");
                    select2 = inp.nextInt();
                    switch (select2) {
                        case 1:
                            Notebook.printNotebook();
                            break;
                        case 2:
                            Notebook.addNewNotebook();
                            break;
                        case 3:
                            Notebook.deleteById();
                            break;
                        case 4:
                            Notebook.searchNotebook();
                            break;
                        default:
                            System.out.println("Hatalı seçim yaptınız, tekrar deneyin.");
                    }
                    break;
                case 2:
                    System.out.println("Telefon işlemleri");
                    System.out.println("1-Ürünleri görüntüle");
                    System.out.println("2-Ürün ekle");
                    System.out.println("3- Ürün sil");
                    System.out.println("4- Ürün filtrele");
                    select3 = inp.nextInt();
                    switch (select3) {
                        case 1:
                            MobilePhone.printPhone();
                            break;
                        case 2:
                            MobilePhone.addNewPhone();
                            break;
                        case 3:
                            MobilePhone.deleteById();
                            break;
                        case 4:
                            MobilePhone.searchPhone();
                            break;
                        default:
                            System.out.println("Hatalı seçim yaptınız, tekrar deneyin.");
                    }
                    break;
                case 3:
                    System.out.println("Markalarımız : ");
                    Brand.printInfo();
                    break;
                case 0:
                    System.out.println("Çıkış yapılıyor.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Hatalı sesçim yaptınız, menüden seçim yapın.");
            }

        }
    }
}
