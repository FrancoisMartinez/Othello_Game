public class Board {

    private String name;
    private Position[][] boardPieces;

    //constructors
    public Board() {
        name = "";
        boardPieces = new Position[8][8];
        initializeBoard();
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
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                this.boardPieces[row][col] = boardPieces[row][col];
            }
        }
    }
    public String getName() {
        return name;
    }
    public Position[][] getBoardPieces() {
        Position[][] copy = new Position[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                copy[row][col] = boardPieces[row][col];
            }
        }
        return copy;
    }

    //initialize board with default starting position and unplayable positions
    private void initializeBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boardPieces[row][col] = new PlayablePosition();
            }
        }
        for (int row = 2; row < 6; row++) {
            boardPieces[row][7] = new UnplayablePosition();
        }
        boardPieces[3][3].setPiece(Position.WHITE);
        boardPieces[3][4].setPiece(Position.BLACK);
        boardPieces[4][3].setPiece(Position.BLACK);
        boardPieces[4][4].setPiece(Position.WHITE);

    }

    //draw board with coordinates
    public void drawboard() {

        System.out.print(" ");
        for(char c = 'A'; c <= 'H'; c++) {
            System.out.print(" " + c);
        }
        System.out.println();
        for (int row = 0; row < 8; row++) {
            System.out.print(row + 1);
            for (int col = 0; col < 8; col++) {
                System.out.print(" " + boardPieces[row][col]);
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

