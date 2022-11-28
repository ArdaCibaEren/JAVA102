import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int defaultHP;
    private int coin;

    private String name;
    private String charName;
    private Inventory inventory;

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    private Scanner inp = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar() {
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println("Choose wisely...");
        for (GameChar gameChar : charList) {
            System.out.println("ID: " + gameChar.getID()
                    + "\t Character: " + gameChar.getName()
                    + "\t Damage: " + gameChar.getDamage()
                    + "\t Health: " + gameChar.getHealth()
                    + "\t Coin: " + gameChar.getCoin());
        }
        int selectChar = inp.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }

        System.out.println("Character : " + this.getCharName() +
                ", Damage : " + this.getDamage() +
                ", Health :" + this.getHealth() +
                ", Coin : " + this.getCoin());
    }

    public void initPlayer(GameChar gameChar) {
        this.setCharName(gameChar.getName());
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setDefaultHP(gameChar.getHealth());
        this.setCoin(gameChar.getCoin());
    }

    public void showInfo() {
        System.out.println(
                "Your weapon : " + this.getInventory().getWeapon().getName() +
                        ", Armor : " + this.getInventory().getArmor().getName() +
                        ", Damage : " + this.getTotalDamage() +
                        ", Health :" + this.getHealth() +
                        ", Block : " + this.getArmor().getBlock() +
                        ", Coin : " + this.getCoin());
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0) {
            health = 0;
        }
        this.health = health;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Weapon getWeapon() {
        return this.getInventory().getWeapon();
    }

    public Armor getArmor() {
        return this.getInventory().getArmor();
    }

    public int getDefaultHP() {
        return defaultHP;
    }

    public void setDefaultHP(int defaultHP) {
        this.defaultHP = defaultHP;
    }
}
