public class Loot {
    private int id;
    private String name;

    public Loot(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Loot[] loot() {
        Loot[] lootList = new Loot[3];
        lootList[0] = new Loot(1, "food");
        lootList[1] = new Loot(2, "firewood");
        lootList[2] = new Loot(3, "water");
        return lootList;
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

    public static Loot getLootByObjName(String name) {
        for (Loot l : Loot.loot()) {
            if (l.getName().equals(name)) {
                return l;
            }
        }
        return null;
    }
}