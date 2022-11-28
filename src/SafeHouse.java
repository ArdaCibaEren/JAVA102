public class SafeHouse extends NormalLoc {
    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    public boolean onLocation() {
        if (this.getPlayer().getInventory().isFood() && this.getPlayer().getInventory().isFirewood() && this.getPlayer().getInventory().isWater()) {
            System.out.println("""
                                        
                    ____    ____  ______    __    __     ____    __    ____  __  .__   __.  __ \s
                    \\   \\  /   / /  __  \\  |  |  |  |    \\   \\  /  \\  /   / |  | |  \\ |  | |  |\s
                     \\   \\/   / |  |  |  | |  |  |  |     \\   \\/    \\/   /  |  | |   \\|  | |  |\s
                      \\_    _/  |  |  |  | |  |  |  |      \\            /   |  | |  . `  | |  |\s
                        |  |    |  `--'  | |  `--'  |       \\    /\\    /    |  | |  |\\   | |__|\s
                        |__|     \\______/   \\______/         \\__/  \\__/     |__| |__| \\__| (__)\s
                                        
                                        -*-*-*-*- BUT YOU CAN KEEP FIGHTING IN THE MINES -*-*-*-*-                
                    """);
        } else {
            System.out.println("You need to clear all of the areas and collect the loots.");
        }
        System.out.println("You are in the safe house now, your health is regenerated.");
        this.getPlayer().setHealth(this.getPlayer().getDefaultHP());
        return true;
    }
}