

public class Game {

    private Board board;
    private Player first;
    private Player second;
    private Player current;

    public Game() {

    }

    public Game(Player p1, Player p2) {

    }

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


    public void start() {

        System.out.println("""
                \t1. Start a New Game
                \t2. Quit
                \t3. Load a Game""");


    }

    public Board load() {
        return board;
    }

    public void play() {

    }

    private void save() {

    }
}
