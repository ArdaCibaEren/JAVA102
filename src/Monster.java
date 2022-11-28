public class Monster {
    private int id;
    private String name;
    private int damage;
    private int health;
    private int lootCoin;
    private int defaultHP;

    public Monster(int id, String name, int damage, int health, int lootCoin) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.defaultHP = health;
        this.lootCoin = lootCoin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getDefaultHP() {
        return defaultHP;
    }

    public void setDefaultHP(int defaultHP) {
        this.defaultHP = defaultHP;
    }

    public int getLootCoin() {
        return lootCoin;
    }

    public void setLootCoin(int lootCoin) {
        this.lootCoin = lootCoin;
    }
}
