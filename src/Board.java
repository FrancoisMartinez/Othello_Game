import java.util.Scanner;

public class Board {

    Scanner s = new Scanner(System.in);

    private String name;
    private Position[][] boardPieces;

    //constructors
    public Board() {
        name = "";
        boardPieces = new Position[8][8];
        initializeBoard("1");
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
    public void initializeBoard(String pos) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boardPieces[row][col] = new PlayablePosition();
            }
        }
        for (int row = 2; row < 6; row++) {
            boardPieces[row][7] = new UnplayablePosition();
        }

        switch (pos) {
            case "1" -> {
                boardPieces[3][3].setPiece(Position.WHITE);
                boardPieces[3][4].setPiece(Position.BLACK);
                boardPieces[4][3].setPiece(Position.BLACK);
                boardPieces[4][4].setPiece(Position.WHITE);
            }
            case "21" -> {
                boardPieces[2][2].setPiece(Position.WHITE);
                boardPieces[2][3].setPiece(Position.BLACK);
                boardPieces[3][2].setPiece(Position.BLACK);
                boardPieces[3][3].setPiece(Position.WHITE);
            }
            case "22" -> {
                boardPieces[2][4].setPiece(Position.WHITE);
                boardPieces[2][5].setPiece(Position.BLACK);
                boardPieces[3][4].setPiece(Position.BLACK);
                boardPieces[3][5].setPiece(Position.WHITE);
            }
            case "23" -> {
                boardPieces[4][2].setPiece(Position.WHITE);
                boardPieces[4][3].setPiece(Position.BLACK);
                boardPieces[5][2].setPiece(Position.BLACK);
                boardPieces[5][3].setPiece(Position.WHITE);
            }
            case "24" -> {
                boardPieces[4][4].setPiece(Position.WHITE);
                boardPieces[4][5].setPiece(Position.BLACK);
                boardPieces[5][4].setPiece(Position.BLACK);
                boardPieces[5][5].setPiece(Position.WHITE);
            }
        }



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


    public boolean flipPiece(String coor, char color) {

        int row = Position.coordinates(coor)[0];
        int col = Position.coordinates(coor)[1];

        int i;
        int j;

        char opposite =  color == Position.BLACK ? Position.WHITE : Position.BLACK;

        boolean valid = false;

        //for all direction check if move is playable and converts any piece

        //check left
        if (col > 0) {
            j = col - 1;
            while (opposite == boardPieces[row][j].getPiece()) {
                j -= 1;
            }
            if (boardPieces[row][j].getPiece() == color && j != col && j != col - 1 ) {
                valid = true;

                j = col - 1;
                while (opposite == boardPieces[row][j].getPiece()) {
                    boardPieces[row][j].setPiece(color);
                    j -= 1;
                }
            }
        }



        //check right
        if (col < 7) {
            j = col + 1;
            while (opposite == boardPieces[row][j].getPiece()) {
                j += 1;
            }
            if (boardPieces[row][j].getPiece() == color && j != col && j != col + 1) {
                valid = true;

                j = col + 1;
                while (opposite == boardPieces[row][j].getPiece()) {
                    boardPieces[row][j].setPiece(color);
                    j += 1;
                }
            }
        }



        //check top
        if (row > 0) {
            i = row - 1;
            while (opposite == boardPieces[i][col].getPiece()) {
                i -= 1;
            }
            if (boardPieces[i][col].getPiece() == color && i != row && i != row - 1 ) {
                valid = true;

                i = row - 1;
                while (opposite == boardPieces[i][col].getPiece()) {
                    boardPieces[i][col].setPiece(color);
                    i -= 1;
                }
            }
        }



        //check bottom
        if (row < 7) {
            i = row + 1;
            while (opposite == boardPieces[i][col].getPiece()) {
                i += 1;
            }
            if (boardPieces[i][col].getPiece() == color && i != row && i != row + 1 ) {
                valid = true;

                i = row + 1;
                while (opposite == boardPieces[i][col].getPiece()) {
                    boardPieces[i][col].setPiece(color);
                    i += 1;
                }
            }
        }


        //check top left
        if (row > 0 && col > 0) {
            i = row - 1;
            j = col - 1;
            while (opposite == boardPieces[i][j].getPiece()) {
                i -= 1;
                j -= 1;
            }
            if (boardPieces[i][j].getPiece() == color && i != row && i != row - 1 && j != col && j != col - 1) {
                valid = true;

                i = row - 1;
                j = col - 1;
                while (opposite == boardPieces[i][j].getPiece()) {
                    boardPieces[i][j].setPiece(color);
                    i -= 1;
                    j -= 1;
                }
            }
        }


        //check top right
        if (row > 0 && col < 7) {
            i = row - 1;
            j = col + 1;
            while (opposite == boardPieces[i][j].getPiece()) {
                i -= 1;
                j += 1;
            }
            if (boardPieces[i][j].getPiece() == color && i != row && i != row - 1 && j != col && j != col + 1) {
                valid = true;

                i = row - 1;
                j = col + 1;
                while (opposite == boardPieces[i][j].getPiece()) {
                    boardPieces[i][j].setPiece(color);
                    i -= 1;
                    j += 1;
                }
            }
        }


        //check bottom left
        if (row < 7 && col > 0) {
            i = row + 1;
            j = col - 1;
            while (opposite == boardPieces[i][j].getPiece()) {
                i += 1;
                j -= 1;
            }
            if (boardPieces[i][j].getPiece() == color && i != row && i != row + 1 && j != col && j != col - 1) {
                valid = true;

                i = row + 1;
                j = col - 1;
                while (opposite == boardPieces[i][j].getPiece()) {
                    boardPieces[i][j].setPiece(color);
                    i += 1;
                    j -= 1;
                }
            }
        }

        //check bottom right
        if (row < 7 && col < 7) {
            i = row + 1;
            j = col + 1;
            while (opposite == boardPieces[i][j].getPiece()) {
                i += 1;
                j += 1;
            }
            if (boardPieces[i][j].getPiece() == color && i != row && i != row + 1 && j != col && j != col + 1) {
                valid = true;

                i = row + 1;
                j = col + 1;
                while (opposite == boardPieces[i][j].getPiece()) {
                    boardPieces[i][j].setPiece(color);
                    i += 1;
                    j += 1;
                }
            }
        }

        return valid;
    }


    public Board(String saveFile) {

    }

    public void takeTurn(Player current) {

        char color = current.getColor();

        String coor = s.nextLine();

        int row = Position.coordinates(coor)[0];
        int col = Position.coordinates(coor)[1];


        //set piece of current player based on coordinate if playable
        if (boardPieces[row][col].canPlay() && flipPiece(coor, color)) {
            boardPieces[row][col].setPiece(color);

        } else {
            System.out.println("unplayable position");
        }

    }


    //public Game(String name) {

    //}

}

