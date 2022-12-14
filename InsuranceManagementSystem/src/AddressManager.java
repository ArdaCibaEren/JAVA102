import java.util.Scanner;

class HomeAddress implements Address {
    private String address;

    public HomeAddress(String address) {
        this.address = address;
    }

    @Override
    public void setAddress(String name) {
        this.address = address;
    }

    @Override
    public String getAddressInfo() {
        return address;
    }
}

class BusinessAddress implements Address {
    @Override
    public void setAddress(String name) {
    }

    @Override
    public String getAddressInfo() {
        return null;
    }
}

public class AddressManager {
    public static void addAddress(User user, Address address) {
        user.getAddressList().add(address);
    }

    public static void removeAddress(User user, Address address){
        user.getAddressList().remove(address);
    }

    public static Address createAddress(){
        Scanner inp = new Scanner(System.in);
        System.out.println("Add new address");
        String newAddress = inp.nextLine();
        return new HomeAddress(newAddress);
    }
}