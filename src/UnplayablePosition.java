public class UnplayablePosition extends Position {
    public static final char UNPLAYABLE = '*';

    @Override
    public boolean canPlay() {
        return false;
    }

}
