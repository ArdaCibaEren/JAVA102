import java.util.Scanner;

public class Game {

    private Scanner inp = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the Adventure Game! \nPlease enter your name : ");
        String playerName = inp.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getName() + " you are about to enter the dark island. Please choose a character if you would like to continue. ");
        player.selectChar();

        Location location = null;
        while (true) {
            player.showInfo();
            System.out.println(" ");
            System.out.println("*****Choose a location*****");
            System.out.println("1- Safe House");
            System.out.println("2- Store");
            if (!player.getInventory().isFood()) {
                System.out.println("3- Cave " + "(area of zombies) Loot : food");
            }
            if (!player.getInventory().isFirewood()) {
                System.out.println("4- Forest " + "(area of vampires) Loot : firewood");
            }
            if (!player.getInventory().isWater()) {
                System.out.println("5- River " + "(area of bears) Loot : water");
            }
            System.out.println("6- Mine " + "(area of snakes Loot : Random");
            System.out.println("0- Quit game");
            System.out.println(" ");
            int selectLoc = inp.nextInt();
            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new Store(player);
                    break;
                case 3:
                    if (player.getInventory().isFood()) {
                        System.out.println("You already collected the food!");
                        location = new SafeHouse(player);
                    } else {
                        location = new Cave(player);
                    }
                    break;
                case 4:
                    if (player.getInventory().isFirewood()) {
                        System.out.println("You already collected the firewood!");
                        location = new SafeHouse(player);
                    } else {
                        location = new Forest(player);
                    }
                    break;
                case 5:
                    if (player.getInventory().isWater()) {
                        System.out.println("You already collected the water!");
                        location = new SafeHouse(player);
                    } else {
                        location = new River(player);
                    }

                    break;
                case 6:
                    location = new Mine(player);
                default:
                    System.out.println("Please choose from the menu.");
            }
            if (location == null) {
                System.out.println("You left the game, the island will be waiting for you.");
                break;
            }
            if (!location.onLocation()) {
                System.out.println("GAME OVER!");
                break;
            }
        }

    }

}