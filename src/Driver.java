import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        Game game = new Game();

        /**
        System.out.println("""
                \t1. Start a New Game
                \t2. Quit
                \t3. Load a  Game""");

        String in = s.nextLine();



        if (in.equals("1")) {
            System.out.println("Starting a new game...");
            Player p1 = new Player();
            System.out.println("Player 1: ");
            p1.setName(s.nextLine());
            Player p2 = new Player();
            System.out.println("Player 2: ");
            p2.setName(s.nextLine());

        }

        */
        game.getBoard().drawboard();


        game.makeMove("b2");

        game.getBoard().drawboard();



    }
}