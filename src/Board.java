
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//handle creating, saving and displaying the board, changing pieces and checking validity of moves
public class Board {

    private String name;
    private Position[][] boardPieces;

    //default constructor initializing with standard starting position
    public Board() {
        name = "";
        boardPieces = new Position[8][8];
        initializeBoard("1");
    }

    //getter for boardPiece by creating a deep copy
    public Position[][] getBoardPieces() {
        Position[][] copy = new Position[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                copy[row][col] = boardPieces[row][col];
            }
        }
        return copy;
    }


    //draw board with coordinates
    public void drawBoard() {

        //columns
        System.out.print(" ");
        for(char c = 'A'; c <= 'H'; c++) {
            System.out.print(" " + c);
        }
        //displaying each row
        System.out.println();
        for (int row = 0; row < 8; row++) {
            System.out.print(row + 1);
            for (int col = 0; col < 8; col++) {
                System.out.print(" " + boardPieces[row][col]);
            }
            System.out.println();
        }
    }


    //load the board from saved file
    public Board(String saveFile) {

        name = saveFile;

        //initializing a Position array for the board piece and creating its new positions
        boardPieces = new Position[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boardPieces[row][col] = new PlayablePosition();
            }
        }
        for (int row = 2; row < 6; row++) {
            boardPieces[row][7] = new UnplayablePosition();
        }



        try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {

            String line;
            int i = 0;

            //reading the board from the file and copying it to the game board
            while ((line = reader.readLine()) != null) {

                if (i > 2) {
                    for (int j = 0; j < line.length(); j++) {

                        if (line.charAt(j) == Position.BLACK) {
                            boardPieces[i - 3][j].setPiece(Position.BLACK);
                        } else if (line.charAt(j) == Position.WHITE) {
                            boardPieces[i - 3][j].setPiece(Position.WHITE);
                        } else if (line.charAt(j) == UnplayablePosition.UNPLAYABLE) {
                            boardPieces[i - 3][j].setPiece(UnplayablePosition.UNPLAYABLE);
                        }  else if (line.charAt(j) == Position.EMPTY) {
                            boardPieces[i - 3][j].setPiece(Position.EMPTY);
                        }
                    }
                }
                i++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Please check the file path and try again.");
        } catch (IOException e) {
            System.out.println("Error: An I/O error occurred while loading the game.");
            e.printStackTrace();
        }
    }


    //make the move for player
    public void takeTurn(String coor, Player current) {

        char color = current.getColor();

        int row = Position.coordinates(coor)[0];
        int col = Position.coordinates(coor)[1];


        //set piece of current player based on coordinate if playable
        if (boardPieces[row][col].canPlay() && flipPiece(coor, color)) {
            boardPieces[row][col].setPiece(color);

        } else {
            System.out.println("unplayable position");
        }

    }

    //did not find a use for this method
    public void Game(String name) {
    }



    //initialize board with default starting position and unplayable positions
    public void initializeBoard(String pos) {

        //initialize board and its positions
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boardPieces[row][col] = new PlayablePosition();
            }
        }
        for (int row = 2; row < 6; row++) {
            boardPieces[row][7] = new UnplayablePosition();
        }

        //set starting pieces according to starting position chosen
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

    //check if a move is valid
    public boolean isValid(String coor, char color) {

        //get position of move
        int row = Position.coordinates(coor)[0];
        int col = Position.coordinates(coor)[1];

        //variable used to in different direction
        int i;
        int j;

        char opposite = color == Position.BLACK ? Position.WHITE : Position.BLACK;

        boolean valid = false;

        //for all direction check if move is playable and converts any piece
        //by checking if adjacent pieces are of opposite color and there is a piece of same color at the end

        //check left
        //check for piece of opposite color to the left and when arriving to a piece of not opposite color, check if same color
        if (col > 1) {
            j = col - 1;
            while (j > 0 && opposite == boardPieces[row][j].getPiece()) {
                j -= 1;
            }
            if (boardPieces[row][j].getPiece() == color && j != col && j != col - 1 ) {
                valid = true;

            }
        }

        //check right
        if (col < 6) {
            j = col + 1;
            while (j < 7 && opposite == boardPieces[row][j].getPiece()) {
                j += 1;
            }
            if (boardPieces[row][j].getPiece() == color && j != col && j != col + 1) {
                valid = true;
            }
        }

        //check top
        if (row > 1) {
            i = row - 1;
            while (i >0 && opposite == boardPieces[i][col].getPiece()) {
                i -= 1;
            }
            if (boardPieces[i][col].getPiece() == color && i != row && i != row - 1 ) {
                valid = true;
            }
        }

        //check bottom
        if (row < 6) {
            i = row + 1;
            while (i < 7 && opposite == boardPieces[i][col].getPiece()) {
                i += 1;
            }
            if (boardPieces[i][col].getPiece() == color && i != row && i != row + 1 ) {
                valid = true;
            }
        }

        //check top left
        if (row > 1 && col > 1) {
            i = row - 1;
            j = col - 1;
            while (i > 0 && j > 0 && opposite == boardPieces[i][j].getPiece()) {
                i -= 1;
                j -= 1;
            }
            if (boardPieces[i][j].getPiece() == color && i != row && i != row - 1 && j != col && j != col - 1) {
                valid = true;
            }
        }

        //check top right
        if (row > 1 && col < 6) {
            i = row - 1;
            j = col + 1;
            while (i > 0 && j < 7 &&opposite == boardPieces[i][j].getPiece()) {
                i -= 1;
                j += 1;
            }
            if (boardPieces[i][j].getPiece() == color && i != row && i != row - 1 && j != col && j != col + 1) {
                valid = true;
            }
        }

        //check bottom left
        if (row < 6 && col > 1) {
            i = row + 1;
            j = col - 1;
            while (i < 7 && j > 0 && opposite == boardPieces[i][j].getPiece()) {
                i += 1;
                j -= 1;
            }
            if (boardPieces[i][j].getPiece() == color && i != row && i != row + 1 && j != col && j != col - 1) {
                valid = true;
            }
        }

        //check bottom right
        if (row < 6 && col < 6) {
            i = row + 1;
            j = col + 1;
            while (i < 7 && j < 7 && opposite == boardPieces[i][j].getPiece()) {
                i += 1;
                j += 1;
            }
            if (boardPieces[i][j].getPiece() == color && i != row && i != row + 1 && j != col && j != col + 1) {
                valid = true;
            }
        }

        return valid;
    }

    //used same logic from isValid and switch the color of middle piece when move was valid
    public boolean flipPiece(String coor, char color) {

        int row = Position.coordinates(coor)[0];
        int col = Position.coordinates(coor)[1];

        int i;
        int j;

        char opposite = color == Position.BLACK ? Position.WHITE : Position.BLACK;

        boolean valid = false;

        //for all direction check if move is playable and converts any piece

        //check left
        //same logic as isValid and if isValid, flip the color of the pieces
        if (col > 1) {
            j = col - 1;
            while (j > 0 && opposite == boardPieces[row][j].getPiece()) {
                j -= 1;
            }
            if (boardPieces[row][j].getPiece() == color && j != col && j != col - 1 ) {
                valid = true;

                j = col - 1;
                while (j > 0 && opposite == boardPieces[row][j].getPiece()) {
                    boardPieces[row][j].setPiece(color);
                    j -= 1;
                }
            }
        }

        //check right
        if (col < 6) {
            j = col + 1;
            while (j < 7 && opposite == boardPieces[row][j].getPiece()) {
                j += 1;
            }
            if (boardPieces[row][j].getPiece() == color && j != col && j != col + 1) {
                valid = true;

                j = col + 1;
                while (j < 7 && opposite == boardPieces[row][j].getPiece()) {
                    boardPieces[row][j].setPiece(color);
                    j += 1;
                }
            }
        }

        //check top
        if (row > 1) {
            i = row - 1;
            while (i > 0 && opposite == boardPieces[i][col].getPiece()) {
                i -= 1;
            }
            if (boardPieces[i][col].getPiece() == color && i != row && i != row - 1 ) {
                valid = true;

                i = row - 1;
                while (i > 0 && opposite == boardPieces[i][col].getPiece()) {
                    boardPieces[i][col].setPiece(color);
                    i -= 1;
                }
            }
        }

        //check bottom
        if (row < 6) {
            i = row + 1;
            while (i < 7 && opposite == boardPieces[i][col].getPiece()) {
                i += 1;
            }
            if (boardPieces[i][col].getPiece() == color && i != row && i != row + 1 ) {
                valid = true;

                i = row + 1;
                while (i < 7 && opposite == boardPieces[i][col].getPiece()) {
                    boardPieces[i][col].setPiece(color);
                    i += 1;
                }
            }
        }

        //check top left
        if (row > 1 && col > 1) {
            i = row - 1;
            j = col - 1;
            while (i > 0 && j > 0 && opposite == boardPieces[i][j].getPiece()) {
                i -= 1;
                j -= 1;
            }
            if (boardPieces[i][j].getPiece() == color && i != row && i != row - 1 && j != col && j != col - 1) {
                valid = true;

                i = row - 1;
                j = col - 1;
                while (i > 0 && j > 0 && opposite == boardPieces[i][j].getPiece()) {
                    boardPieces[i][j].setPiece(color);
                    i -= 1;
                    j -= 1;
                }
            }
        }

        //check top right
        if (row > 1 && col < 6) {
            i = row - 1;
            j = col + 1;
            while (i > 0 && j < 7 && opposite == boardPieces[i][j].getPiece()) {
                i -= 1;
                j += 1;
            }
            if (boardPieces[i][j].getPiece() == color && i != row && i != row - 1 && j != col && j != col + 1) {
                valid = true;

                i = row - 1;
                j = col + 1;
                while (i > 0 && j < 7 &&opposite == boardPieces[i][j].getPiece()) {
                    boardPieces[i][j].setPiece(color);
                    i -= 1;
                    j += 1;
                }
            }
        }

        //check bottom left
        if (row < 6 && col > 1) {
            i = row + 1;
            j = col - 1;
            while (i < 7 && j > 0 && opposite == boardPieces[i][j].getPiece()) {
                i += 1;
                j -= 1;
            }
            if (boardPieces[i][j].getPiece() == color && i != row && i != row + 1 && j != col && j != col - 1) {
                valid = true;

                i = row + 1;
                j = col - 1;
                while (i < 7 && j > 0 && opposite == boardPieces[i][j].getPiece()) {
                    boardPieces[i][j].setPiece(color);
                    i += 1;
                    j -= 1;
                }
            }
        }

        //check bottom right
        if (row < 6 && col < 6) {
            i = row + 1;
            j = col + 1;
            while (i < 7 && j < 7 && opposite == boardPieces[i][j].getPiece()) {
                i += 1;
                j += 1;
            }
            if (boardPieces[i][j].getPiece() == color && i != row && i != row + 1 && j != col && j != col + 1) {
                valid = true;

                i = row + 1;
                j = col + 1;
                while (i < 7 && j < 7 && opposite == boardPieces[i][j].getPiece()) {
                    boardPieces[i][j].setPiece(color);
                    i += 1;
                    j += 1;
                }
            }
        }
        return valid;
    }

    //toString method for saving the board
    public String toString() {

        StringBuilder board = new StringBuilder();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.append(boardPieces[row][col]);
            }
            board.append("\n");
        }
        return board.toString();
    }
}
