
import java.io.*;
import java.util.Scanner;

//handle creating a new game, loading a game, saving a game, and playing it (checking for moves and for a winner)
public class Game {

    private static final Scanner s = new Scanner(System.in);

    private Board board;
    private Player first;
    private Player second;
    private Player current;

    //default constructor
    public Game() {
        board = new Board();
        first = new Player("p1", Position.BLACK);
        second = new Player("p2", Position.WHITE);
        current = first;
    }
    //constructor
    public Game(Player p1, Player p2) {
        first = p1;
        second = p2;
        current = p1;
        board = new Board();
    }

    //the start method ask the user the starting position for the new game
    public void start() {

        System.out.println("""
                \t1. Standard Positions
                \t2. Offset Starting Position""");

        String pos = s.nextLine();
        String offset = "";
        if (pos.equals("1")) {
            play();
        } if (pos.equals("2")) {
            System.out.println("""
                \t1. Offset top right
                \t2. Offset top left
                \t3. Offset bottom right
                \t4. Offset bottom left""");
            offset = s.nextLine();
        }
        board.initializeBoard(pos + offset);

        play();
    }

    //uml says Board return type, but could be void
    //load game from file
    public static Board load() {

        System.out.println("Enter filename: ");
        String saveFile = s.nextLine();

        Game game = new Game();

        //call the method to set the game
        game.board = new Board(saveFile);

        //set the first, second and current player
        try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (i == 0) {
                    game.first = new Player(line, Position.BLACK);
                } else if (i == 1) {
                    game.second = new Player(line, Position.WHITE);
                } else if (i == 2) {
                    game.current = line.equals(game.first.getName()) ? game.first : game.second;
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Please check the file path and try again.");
        } catch (IOException e) {
            System.out.println("Error: An I/O error occurred while loading the game.");
            e.printStackTrace();
        }

        //start the game
        game.play();

        return game.board;
    }

    //handle playing, saving and conceding the game
    public void play() {


        boolean end = false;


        while ((!end) && (!ended())) {

            board.drawBoard();

            //current player has no move available
            if (!checkForMoves()) {
                System.out.printf("""
                        Player %s turn
                        1. Save game
                        2. Concede game
                        3. Forfeit turn%n""", current);
                String noMove = s.nextLine();


                switch (noMove) {
                    //save
                    case "1" -> {
                        System.out.println("Enter FileName:");
                        save(s.nextLine());
                        System.out.println("Saving game...");
                        end = true;
                    }
                    //concede
                    case "2" -> {
                        end = true;
                        System.out.println(current + " has conceded the game.");
                        System.exit(0);
                    }
                    //next turn
                    case "3" -> {
                        //switch player
                        current = current == first ? second : first;
                    }
                    default -> System.out.println("Invalid input");
                }
            //player can move
            } else {

                System.out.printf("""
                        Player %s turn
                        1. Save game
                        2. Concede game
                        3. Make move%n""", current);
                String move = s.nextLine();

                switch (move) {
                    //save
                    case "1" -> {
                        System.out.println("Enter FileName:");
                        save(s.nextLine());
                        System.out.println("Saving game...");
                        end = true;
                    }
                    //concede
                    case "2" -> {
                        end = true;
                        System.out.println(current + " has conceded the game.");
                        System.exit(0);
                    }
                    //play
                    case "3" -> {
                        System.out.println("Enter your move in the format column/row (eg. b2): ");
                        String coor = s.nextLine();
                        //verify move validity and switch player
                        if (board.isValid(coor, current.getColor())) {
                            board.takeTurn(coor, current);
                            current = current == first ? second : first;
                        } else {
                            System.out.println("Invalid move");
                        }
                    }
                    default -> System.out.println("Invalid input");
                }
            }
        }
    }

    //save game to a file
    private void save(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(first.getName());
            writer.newLine();
            writer.write(second.getName());
            writer.newLine();
            writer.write(current.getName());
            writer.newLine();
            writer.write(board.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //check if current player can move a piece
    private boolean checkForMoves() {

        boolean hasMove = false;

        //create array with all coordinates
        String[][] coordinates = new String[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                coordinates[row][col] = "" + ((char) ('A' + col)) + (row + 1);
            }
        }

        //check for all coordinates if there is a valid move
        outerLoop:
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                //check if playable square and if valid move
                if (board.getBoardPieces()[row][col].canPlay() && board.isValid(coordinates[row][col], current.getColor())) {
                    hasMove = true;
                    break outerLoop;
                }

            }
        }
        return hasMove;
    }

    //check if game has ended and handle the winner and end message
    private boolean ended() {

        boolean ended;
        //checks move for both player
        current = current == first ? second : first;
        ended = !checkForMoves();
        current = current == first ? second : first;
        ended |= !checkForMoves();

        //if ended, count the pieces of both player
        if (ended) {
            int black = 0;
            int white = 0;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.getBoardPieces()[i][j].getPiece() == Position.BLACK) {
                        black++;
                    } else if (board.getBoardPieces()[i][j].getPiece() == Position.WHITE) {
                        white++;
                    }
                }
            }


            String winner = "";

            boolean tie = false;
            //determine winner
            if (black > white) winner = first.getName();
            else if (white > black) winner = second.getName();
            else if (white == black) tie = true;

            board.drawBoard();

            if (tie) {
                System.out.println("The game has ended in a tie.\n" + first.getName() + ": " + black + "\n" + second.getName() + ": " + white);
            } else {
                System.out.println("The game has ended.\n" + winner + " won the game\n" + first.getName() + ": " + black + "\n" + second.getName() + ": " + white);
            }
        }

        return ended;
    }

}
