
public class PlayablePosition extends Position {

    public PlayablePosition() {
        super();
    }

    public PlayablePosition(char piece) {
        super(piece);
    }
    @Override
    public boolean canPlay() {
        return getPiece() == EMPTY;
    }
}
