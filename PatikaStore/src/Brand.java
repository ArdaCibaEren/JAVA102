import java.util.LinkedHashMap;
import java.util.Map;

public class Brand {
    private int id;
    private String name;
    private static LinkedHashMap<Integer, String> list = new LinkedHashMap<Integer, String>();

    public static void info() {
        list.put(1, "Samsung");
        list.put(2, "Lenovo");
        list.put(3, "Apple");
        list.put(4, "Huawei");
        list.put(5, "Casper");
        list.put(6, "Asus");
        list.put(7, "HP");
        list.put(8, "Xiaomi");
        list.put(9, "Monster");
    }

    public static String getNameByID(int i){
        return list.get(i);
    }

    public static void printInfo() {
        list.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach((e) -> System.out.println(e.getValue()));
    }

    public static LinkedHashMap<Integer, String> getBrandList() {
        return list;
    }
}
