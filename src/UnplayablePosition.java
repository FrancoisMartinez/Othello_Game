public class UnplayablePosition extends Position {

    public static final char UNPLAYABLE = '*';

    public UnplayablePosition() {
        super(UNPLAYABLE);
    }

    @Override
    public boolean canPlay() {
        return false;
    }

}
