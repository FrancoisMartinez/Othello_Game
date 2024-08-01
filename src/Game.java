import java.util.Objects;
import java.util.Scanner;


public class Game {

    Scanner s = new Scanner(System.in);

    private Board board;
    private Player first;
    private Player second;
    private Player current;

    public Game() {
        board = new Board();
        first = new Player("p1");
        second = new Player("p2");
        current = first;
    }

    public Game(Player p1, Player p2) {
        first = p1;
        second = p2;
        current = p1;
        board = new Board();
    }

    //setters/getters
    public void setBoard(Board board) {
        this.board = board;
    }
    public void setFirst(Player p1) {
        first = p1;
    }
    public void setSecond(Player p2) {
        second = p2;
    }
    public void setCurrent(Player current) {
        this.current = current;
    }
    public Board getBoard() {
        return board;
    }
    public Player getFirst() {
        return first;
    }
    public Player getSecond() {
        return second;
    }
    public Player getCurrent() {
        return current;
    }


    public void start() {
        System.out.println("""
                \t1. Start a New Game
                \t2. Quit
                \t3. Load a  Game""");

        String in = s.nextLine();

        if (in.equals("1")) {
            System.out.println("Starting a new game...");
            System.out.println("Player 1: ");
            first.setName(s.nextLine());
            System.out.println("Player 2: ");
            second.setName(s.nextLine());
            play();
        } else if (in.equals("2")) {
            System.out.println("Quiting game...");
            System.exit(0);
        } else if (in.equals("3")) {
            System.out.println("Enter file name:");

        } else {
            System.out.println("Invalid input, enter a number from 1 to 3.");
            start();
        }
    }

    public static Board load() {
        Board board = new Board();
        return board;
    }

    public void play() {



        while (checkForMoves()) {

            board.drawboard();

            System.out.println(current + "'s move");

            makeMove(s.nextLine());



        }



    }

    public void makeMove(String coor) {

        int row = Position.coordinates(coor)[0];
        int col = Position.coordinates(coor)[1];


        //set piece of current player based on coordinate if playable
        if (board.getBoardPieces()[row][col].canPlay() && board.flipPiece(coor, (current == first ? Position.BLACK : Position.WHITE))) {
            board.getBoardPieces()[row][col].setPiece((current.equals(first) ? Position.BLACK : Position.WHITE));

        } else {
            System.out.println("unplayable position");
        }

    }




    public boolean checkForMoves() {
        return true;
    }



    private void save() {

    }
}
