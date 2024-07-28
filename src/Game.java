
public class Game {

    private Board board;
    private Player first;
    private Player second;
    private Player current;

    public Game() {
        board = new Board();
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

    }

    public void makeMove(String coor) {

        int row = Character.getNumericValue(coor.charAt(1)) - 1;
        int col = (int) Character.toLowerCase(coor.charAt(0)) - 97;

        board.getBoardPieces()[row][col].setPiece(Position.BLACK);
    }

    public boolean conversion(String coor) {

        return true;
    }

    public boolean moves() {
        return true;
    }

    private void save() {

    }
}
