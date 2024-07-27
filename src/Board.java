public class Board {

    private String name;
    private Position[][] boardPieces;

    //constructors
    public Board() {
        name = "";
        boardPieces = new Position[8][8];

    }
    public Board(String name, Position[][] boardPieces) {

    }
    public Board(Board board) {
        this.setName(board.name);
        this.setBoardPieces(board.boardPieces);
    }


    //setters/getters
    public void setName(String name) {
        this.name = name;
    }
    public void  setBoardPieces(Position[][] boardPieces) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.boardPieces[i][j] = boardPieces[i][j];
            }
        }
    }
    public String getName() {
        return name;
    }
    public Position[][] getBoardPieces() {
        Position[][] copy = new Position[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copy[i][j] = boardPieces[i][j];
            }
        }
        return copy;
    }


    public void drawboard() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(boardPieces[i][j]);
            }
            System.out.println();
        }


    }

    public Board(String saveFile) {

    }

    public void takeTurn(Player current) {

    }

    //public Game(String name) {

    //}

}

