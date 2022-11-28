public class Store extends NormalLoc {

    public Store(Player player) {
        super(player, "Store");
    }

    @Override
    public boolean onLocation() {
        System.out.println(" ----- Welcome to the store! ----- ");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1- Weapons");
            System.out.println("2- Armors");
            System.out.println("3- Leave the store");
            System.out.println("Please choose from the menu.");
            int selectCase = Location.inp.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                System.out.print("Not on the menu! Please choose again.");
                selectCase = Location.inp.nextInt();
            }
            switch (selectCase) {
                case 1:
                    showWeapons();
                    buyWeapon();
                    break;
                case 2:
                    showArmors();
                    buyArmors();
                    break;
                case 3:
                    System.out.println("Goodbye, do not forget to come by again.");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void showWeapons() {
        System.out.println("---Weapons---");
        for (Weapon w : Weapon.weapon()) {
            System.out.println(w.getId() + " --> "
                    + w.getName()
                    + ", Price : " + w.getPrice()
                    + ", Damage : " + w.getDamage());
        }
        System.out.println("0 - Show the main menu.");
    }

    public void buyWeapon() {
        System.out.println("Choose the weapon you would like to purchase");
        int selectWeaponID = inp.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapon().length) {
            System.out.print("Not on the menu! Please choose again.");
            selectWeaponID = inp.nextInt();
        }
        if (selectWeaponID != 0) {
            Weapon selectedWeapon = Weapon.getWeaponByObjId(selectWeaponID);

            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getCoin()) {
                    System.out.println("You don't have enough coin.");
                } else {
                    System.out.println("You just purchased " + selectedWeapon.getName());
                    int balance = this.getPlayer().getCoin() - selectedWeapon.getPrice();
                    this.getPlayer().setCoin(balance);
                    System.out.println("Your current coin is : " + this.getPlayer().getCoin());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }
        }
    }

    public void showArmors() {
        System.out.println("---Armors---");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + " --> "
                    + a.getName()
                    + ", Block : " + a.getBlock()
                    + ", Price : " + a.getPrice());
        }
        System.out.println("0 - Show the main menu.");
    }

    public void buyArmors() {
        System.out.println("Choose the armor you would like to purchase");
        int selectArmorID = inp.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length) {
            System.out.print("Not on the menu! Please choose again.");
            selectArmorID = inp.nextInt();
        }
        if (selectArmorID != 0) {
            Armor selectedArmor = Armor.getArmorByObjId(selectArmorID);

            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getCoin()) {
                    System.out.println("You don't have enough coin.");
                } else {
                    System.out.println("You just purchased " + selectedArmor.getName());
                    int balance = this.getPlayer().getCoin() - selectedArmor.getPrice();
                    this.getPlayer().setCoin(balance);
                    System.out.println("Your current coin is : " + this.getPlayer().getCoin());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                }
            }
        }
    }
}