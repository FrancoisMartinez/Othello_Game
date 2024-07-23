abstract public class Position {
    private char piece;
    public static final char EMPTY = ' ';
    public static final char BLACK = 'B';
    public static final char WHITE = 'W';

    public boolean canPlay() {
        return false;
    }
}
