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

            if (current.equals(first)) {
                current = second;
            } else {
                current = first;
            }

        }



    }

    public void makeMove(String coor) {

        int row = coordinates(coor)[0];
        int col = coordinates(coor)[1];


        //set piece of current player based on coordinate if playable
        if (board.getBoardPieces()[row][col].canPlay() && isValid(coor)) {
            board.getBoardPieces()[row][col].setPiece((current.equals(first) ? Position.BLACK : Position.WHITE));

        } else {
            System.out.println("unplayable position");
        }

    }

    public boolean conversion(String coor) {

        return true;
    }



    private boolean isValid(String coor) {

        int row = coordinates(coor)[0];
        int col = coordinates(coor)[1];

        int i;
        int j;

        //get color of current player and its opposite
        char color = current == first ? Position.BLACK : Position.WHITE;
        char opposite =  current == first ? Position.WHITE : Position.BLACK;

        boolean valid = false;




        //check left
        if (col > 0) {
            j = col - 1;
            while (opposite == board.getBoardPieces()[row][j].getPiece()) {
                j -= 1;
            }
            if (board.getBoardPieces()[row][j].getPiece() == color && j != col && j != col - 1 ) {
                System.out.println("YES left");
                valid = true;
            } else {
                System.out.println("NO left");
            }
        }
        //check right
        if (col < 7) {
            j = col + 1;
            while (opposite == board.getBoardPieces()[row][j].getPiece()) {
                j += 1;
            }
            if (board.getBoardPieces()[row][j].getPiece() == color && j != col && j != col + 1 ) {
                System.out.println("YES right");
                valid = true;
            } else {
                System.out.println("NO right");
            }
        }

        //check top
        if (row > 0) {
            i = row - 1;
            while (opposite == board.getBoardPieces()[i][col].getPiece()) {
                i -= 1;
            }
            if (board.getBoardPieces()[i][col].getPiece() == color && i != row && i != row - 1 ) {
                System.out.println("YES top");
                valid = true;
            } else {
                System.out.println("NO top");
            }
        }
        //check bottom
        if (row < 7) {
            i = row + 1;
            while (opposite == board.getBoardPieces()[i][col].getPiece()) {
                i += 1;
            }
            if (board.getBoardPieces()[i][col].getPiece() == color && i != row && i != row + 1 ) {
                System.out.println("YES bottom");
                valid = true;
            } else {
                System.out.println("NO bottom");
            }
        }

        //check top left
        if (row > 0 && col > 0) {
            i = row - 1;
            j = col - 1;
            while (opposite == board.getBoardPieces()[i][j].getPiece()) {
                i -= 1;
                j -= 1;
            }
            if (board.getBoardPieces()[i][j].getPiece() == color && i != row && i != row - 1 && j != col && j != col - 1) {
                System.out.println("YES top left");
                valid = true;
            } else {
                System.out.println("NO top left");
            }
        }

        //check top right
        if (row > 0 && col < 7) {
            i = row - 1;
            j = col + 1;
            while (opposite == board.getBoardPieces()[i][j].getPiece()) {
                i -= 1;
                j += 1;
            }
            if (board.getBoardPieces()[i][j].getPiece() == color && i != row && i != row - 1 && j != col && j != col + 1) {
                System.out.println("YES top right");
                valid = true;
            } else {
                System.out.println("NO top right");
            }
        }

        //check bottom left
        if (row < 7 && col > 0) {
            i = row + 1;
            j = col - 1;
            while (opposite == board.getBoardPieces()[i][j].getPiece()) {
                i += 1;
                j -= 1;
            }
            if (board.getBoardPieces()[i][j].getPiece() == color && i != row && i != row + 1 && j != col && j != col - 1) {
                System.out.println("YES bottom left");
                valid = true;
            } else {
                System.out.println("NO bottom left");
            }
        }

        //check bottom right
        if (row < 7 && col < 7) {
            i = row + 1;
            j = col + 1;
            while (opposite == board.getBoardPieces()[i][j].getPiece()) {
                i += 1;
                j += 1;
            }
            if (board.getBoardPieces()[i][j].getPiece() == color && i != row && i != row + 1 && j != col && j != col + 1) {
                System.out.println("YES bottom right");
                valid = true;
            } else {
                System.out.println("NO bottom right");
            }
        }
        return valid;
    }

    public boolean checkForMoves() {
        return true;
    }

    //get row and col from coordinate
    private int[] coordinates(String coor) {
        int[] xy = new int[2];
        xy[0] = Character.getNumericValue(coor.charAt(1)) - 1;
        xy[1] = (int) Character.toLowerCase(coor.charAt(0)) - 97;
        return xy;
    }

    private void save() {

    }
}
