import java.util.Scanner;
import java.util.TreeSet;

public class AccountManager {
    TreeSet<Account> accounts;
    User currentUser;
    Account account;

    AccountManager() {
        accounts = new TreeSet<>();
        run();
    }

    public void run() {
        Scanner inp = new Scanner(System.in);
        String select = "Welcome!";
        while (!select.equals("3")) {
            System.out.println("1- Create an account");
            System.out.println("2- Login");
            System.out.println("3- Quit");
            System.out.println("4- Admin login");
            System.out.print("Please choose");
            select = inp.nextLine();

            if (select.equals("1")) {
                createAccount();
            } else if (select.equals("2")) {
                System.out.print("Enter your e-mail address");
                String email = inp.nextLine();
                System.out.print("Enter your password");
                String password = inp.nextLine();

                login(email, password);
            } else if (select.equals("4")) {
                System.out.println("All the information will be shown");
                for (Account a : getAccounts())
                    System.out.println("E-mail : " + a.getUser().getEmail() + " Password : " + a.getUser().getPassword());

                System.out.println("Showing all the insurance information");
                for (Insurance a : User.getInsuranceList())
                    System.out.println("Customer name : " + a.getCustomerName() + " Insurance Name : " + a.getInsuranceName() + " Total Price : " + a.getPrice() + "\n");
            }
        }
    }

    public void createAccount() {
        Scanner inp = new Scanner(System.in);

        String name, lastname, email, password, profession;
        int age;
        System.out.print("Enter your name : ");
        name = inp.nextLine();
        System.out.print("Enter your lastname : ");
        lastname = inp.nextLine();
        System.out.print("Enter your email : ");
        email = inp.nextLine();
        System.out.print("Enter your password : ");
        password = inp.nextLine();
        System.out.print("Enter your profession : ");
        profession = inp.nextLine();
        System.out.print("Enter your age : ");
        age = inp.nextInt();
        User user = new User(name, lastname, email, password, profession, age);

        System.out.println("Are you an individual? (1 YES, 0 NO)");
        name = inp.nextLine();
        Account prop;
        if (name.equals("1")) {
            prop = new Individual(user);
        } else {
            prop = new Enterprise(user);
        }
        accounts.add(prop);
    }

    public void login(String email, String password) {
        for (Account account : accounts) {
            try {
                account.login(email, password);
                if (account.isLogin()) {
                    currentUser = account.getUser();
                    this.account = account;
                    if (account.getType() == 1) {
                        individualUserProcess();
                    } else {
                        individualUserProcess();
                        break;
                    }
                }
            } catch (InvalidAuthenticationException e) {
            }
        }
    }

    public void individualUserProcess() {
        System.out.println("Welcome dear " + currentUser.getName());
        String select = "1";
        while (!select.equals("q")) {
            Scanner inp = new Scanner(System.in);

            System.out.println("\n1-) Show account info");
            System.out.println("2-) Get Insurance");
            System.out.println("3-) Show Insurance List");
            System.out.println("4-) Add Address");
            System.out.println("5-) Show All Addresses");
            select = inp.nextLine();

            if (select.equals("1")) {
                account.showUserInfo();
            } else if (select.equals("2")) {
                InsuranceManager insuranceManager = new InsuranceManager();
                account.addInsurance(insuranceManager.createInsurance(currentUser));
            } else if (select.equals("3")) {
                for (Insurance insurance : account.getUser().getInsuranceList()) {
                    System.out.print("\n\nName : " + insurance.getInsuranceName());
                    System.out.println("Price : " + insurance.getPrice());
                }
            } else if (select.equals("4")) {
                account.addAddress(AddressManager.createAddress());
            } else if (select.equals("5")) {
                for (Address adr : currentUser.getAddressList()) {
                    System.out.println(adr.getAddressInfo());
                }
            }
        }
    }

    public void enterpriseUserProcess() {
    }

    public TreeSet<Account> getAccounts() {
        return accounts;
    }
}