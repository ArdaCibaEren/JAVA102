import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class User {
    private String name, lastname, email, password, profession;
    private int age;
    private Date lastLogin;
    private final ArrayList<Address> addressList;
    static private final ArrayList<Insurance> insuranceList = new ArrayList<>();

    User(String name, String lastname, String email, String password, String profession, int age){
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.profession = profession;
        this.age = age;
        this.addressList = new ArrayList<>();
        lastLogin = new Date();
        lastLogin = Date.from(Instant.now());
    }

    public ArrayList<Address> getAddressList(){
        return addressList;
    }

    public static ArrayList<Insurance> getInsuranceList(){
        return insuranceList;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getProfession() {
        return profession;
    }

    public int getAge() {
        return age;
    }

    public String getLastLogin() {
        return lastLogin.toString();
    }

    public void setLastLogin(){
        Date.from(Instant.now());
    }
}