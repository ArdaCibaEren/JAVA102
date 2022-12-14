import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

public class InsuranceManager {
    InsuranceManager() {}

    public Insurance createInsurance(User user) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Select the insurance type from below : ");
        System.out.println("1- Health\n2- Residence\n3- Travel\n4- Car");
        String select = inp.nextLine();
        System.out.println("Enter the amount : ");
        double price = inp.nextDouble();

        if(select.equals("1")) return new HealthInsurance(user, "Health Insurance", price*1.23, Date.from(Instant.now()));
        if(select.equals("2")) return new ResidenceInsurance(user, "Residence Insurance", price*1.32, Date.from(Instant.now()));
        if(select.equals("3")) return new TravelInsurance(user, "Travel Insurance", price*2.4, Date.from(Instant.now()));
        return new CarInsurance(user, "Car Insurace", price*1.62, Date.from(Instant.now()));
    }
}
class HealthInsurance extends Insurance {

    HealthInsurance(User user, String name, double price, Date date) {
        super(user,name, price, date);
    }
    @Override
    public double calculate(){
        return super.getPrice();
    }
}

class ResidenceInsurance extends Insurance {

    ResidenceInsurance(User user,String name, double price, Date date) {
        super(user, name, price, date);
    }
    @Override
    public double calculate(){
        return super.getPrice();
    }
}

class TravelInsurance extends Insurance {

    TravelInsurance(User user,String name, double price, Date date) {
        super(user, name, price, date);
    }
    @Override
    public double calculate(){
        return super.getPrice();
    }
}

class CarInsurance extends Insurance {

    CarInsurance(User user,String name, double price, Date date) {
        super(user, name, price, date);
    }

    @Override
    public double calculate(){
        return super.getPrice();
    }
}