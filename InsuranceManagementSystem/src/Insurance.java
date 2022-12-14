import javax.naming.InsufficientResourcesException;
import java.util.Date;

public class Insurance {
    private final String insuranceName;
    private final double price;
    private final Date date;
    private final User user;

    Insurance(User user, String name, double price, Date date) {
        this.insuranceName = name;
        this.price = price;
        this.date = date;
        this.user = user;
    }

    public double calculate(){
        return price;
    }

    public double getPrice(){
        return price;
    }

    public String getInsuranceName(){
        return insuranceName;
    }

    public String getCustomerName(){
        return user.getName();
    }
}