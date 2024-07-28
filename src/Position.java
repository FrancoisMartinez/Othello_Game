abstract public class Position {

    private char piece;
    public static final char EMPTY = ' ';
    public static final char BLACK = 'B';
    public static final char WHITE = 'W';

    //constructors
    public Position() {
        piece = EMPTY;
    }
    public Position(char piece) {
        this.piece = piece;
    }


    //setters/getters
    public void setPiece(char piece) {
        this.piece = piece;
    }
    public char getPiece() {
        return piece;
    }

    public abstract boolean canPlay();

    public String toString() {
        return "" + piece;
    }

}
