import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        Game game = new Game();

        game.start();

        String in = s.nextLine();

        if (in.equals("1")) {
            Player p1 = new Player();
            System.out.println("Player 1: ");
            p1.setName(s.nextLine());
            Player p2 = new Player();
            System.out.println("Player 2: ");
            p2.setName(s.nextLine());
            
        }




    }
}