import java.util.Random;

public class Mine extends BattleLoc {
    static Random random = new Random();
    public Mine(Player player) {
        super(player, "Mine", new Snake(random.nextInt(4)+3), "coin,weapon or armor", 5);
    }
}

