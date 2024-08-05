import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("""
                \t1. Start a New Game
                \t2. Quit
                \t3. Load a  Game""");

        String in = s.nextLine();

        do {
            switch (in) {
                case "1" -> {

                    System.out.println("Starting a new game...");
                    System.out.println("Player 1: ");
                    String p1 = s.nextLine();
                    System.out.println("Player 2: ");
                    String p2 = s.nextLine();

                    Game game = new Game(new Player(p1, Position.BLACK), new Player(p2, Position.WHITE));

                    game.start();
                }
                case "2" -> {
                    System.out.println("Quiting...");
                    System.exit(0);
                }
                case "3" -> {
                    Game.load();
                }
                default -> System.out.println("Invalid input, enter a number from 1 to 3.");
            }
        } while (!in.equals("1") && !in.equals("2") && !in.equals("3"));


    }
}