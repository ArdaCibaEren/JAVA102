import java.util.Random;

public abstract class BattleLoc extends Location {
    private Monster monster;
    private String loot;
    private int maxMonster;

    public BattleLoc(Player player, String name, Monster monster, String loot, int maxMonster) {
        super(player, name);
        this.monster = monster;
        this.loot = loot;
        this.maxMonster = maxMonster;
    }

    @Override
    public boolean onLocation() {
        int monsterNumber = this.randomMonsterNumber();
        System.out.println("You entered the " + this.getName() + ".");
        if (monsterNumber == 1) {
            System.out.println("There is 1 " + this.getMonster().getName() + " waiting for you!");
        } else {
            System.out.println("There are " + monsterNumber + " " + this.getMonster().getName() + "s waiting for you!");
        }
        System.out.println("Choose fast! <F>ight or <R>un");
        String selectCase = inp.nextLine().toUpperCase();
        if (selectCase.equals("F") && combat(monsterNumber)) {
            System.out.println(this.getName() + " is clear! You killed all the enemies.");
            if (this.loot.equals("food")) {
                this.getPlayer().getInventory().setFood(true);
            }
            if (this.loot.equals("firewood")) {
                this.getPlayer().getInventory().setFirewood(true);
            }
            if (this.loot.equals("water")) {
                this.getPlayer().getInventory().setWater(true);
            }

            System.out.println("You just got " + this.loot + " !");

            return true;
        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("You died!");
            return false;
        }
        return true;
    }

    public boolean combat(int monsterNumber) {
        for (int i = 1; i <= monsterNumber; i++) {
            int x = 0;
            int randomHit = 1;
            this.getMonster().setHealth(this.getMonster().getDefaultHP());
            playerStats();
            enemyStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                System.out.println("<F>ight or <R>un!");
                String selectCombat = inp.nextLine().toUpperCase();

                if (x == 0) {
                    Random a = new Random();
                    randomHit = a.nextInt(2);
                }

                if (selectCombat.equals("F")) {
                    if (randomHit == 1) {
                        x = 2;
                        System.out.println("You hit the enemy!");
                        this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getMonster().getHealth() > 0) {
                            System.out.println();
                            System.out.println("The enemy hit you!");
                            int monsterDamage = this.getMonster().getDamage() - getPlayer().getArmor().getBlock();
                            if (monsterDamage < 0) {
                                monsterDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                            afterHit();

                        }
                    } else {
                        if (this.getMonster().getHealth() > 0) {
                            System.out.println();
                            x = 2;
                            randomHit = 2;
                            System.out.println("The enemy hit you!");
                            int monsterDamage = this.getMonster().getDamage() - getPlayer().getArmor().getBlock();
                            if (monsterDamage < 0) {
                                monsterDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                            afterHit();
                        }
                        System.out.println("You hit the enemy!");
                        this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }
                }
            }
            if (this.getMonster().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("You killed the enemy!");
                if(this.getName().equals("Mine"))
                {
                    Random b = new Random();
                     int pointer = b.nextInt(99);
                     if (pointer<15){
                         int gunPoss = b.nextInt(99);
                         if (gunPoss<20){this.getPlayer().getInventory().setWeapon(Weapon.getWeaponByObjId(1));}
                         else if (gunPoss<50) {this.getPlayer().getInventory().setWeapon(Weapon.getWeaponByObjId(2));}
                         else{this.getPlayer().getInventory().setWeapon(Weapon.getWeaponByObjId(3));}
                     }
                     else if (pointer<30) {
                         int armory = b.nextInt(99);
                         if (armory<20){this.getPlayer().getInventory().setArmor(Armor.getArmorByObjId(3));}
                         else if (armory<50) {this.getPlayer().getInventory().setArmor(Armor.getArmorByObjId(2));}
                         else{this.getPlayer().getInventory().setArmor(Armor.getArmorByObjId(1));}
                     }
                     else if (pointer<55) {
                         int money = b.nextInt(99);
                         if (money<20){this.getPlayer().setCoin(this.getPlayer().getCoin() + 10);}
                         else if (money<50) {this.getPlayer().setCoin(this.getPlayer().getCoin() + 5);}
                         else{this.getPlayer().setCoin(this.getPlayer().getCoin() + 1);}
                     }
                     else {
                         System.out.println("You did not get any loot, keep fighting!");
                     }
                }else {
                System.out.println("You won " + this.getMonster().getLootCoin() + " coins!");
                this.getPlayer().setCoin(this.getPlayer().getCoin() + this.getMonster().getLootCoin());
                System.out.println("Your current coin is " + this.getPlayer().getCoin());
            }} else {
                return false;
            }
        }
        return true;
    }

    public void afterHit() {
        System.out.println("Your HP : " + this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName() + " has " + this.getMonster().getHealth() + " HP");
        System.out.println("---------");
    }

    public void playerStats() {
        System.out.println("Your status :");
        System.out.println("------------");
        System.out.println("Health : " + this.getPlayer().getHealth());
        System.out.println("Weapon : " + this.getPlayer().getWeapon().getName());
        System.out.println("Armor : " + this.getPlayer().getArmor().getName());
        System.out.println("Damage : " + this.getPlayer().getTotalDamage());
        System.out.println("Block : " + this.getPlayer().getArmor().getBlock());
        System.out.println("Coin : " + this.getPlayer().getCoin());
    }

    public void enemyStats(int i) {
        System.out.println(i + "." + this.getMonster().getName() + " info");
        System.out.println("------------");
        System.out.println("Health : " + this.getMonster().getHealth());
        System.out.println("Damage : " + this.getMonster().getDamage());
        System.out.println("Loot coin: " + this.getMonster().getLootCoin());
    }

    public int randomMonsterNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxMonster()) + 1;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getLoot() {
        return loot;
    }

    public void setLoot(String loot) {
        this.loot = loot;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }
}