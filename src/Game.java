import java.io.*;
import java.util.Scanner;
import java.io.IOException;

public class Game {

    private static final Scanner s = new Scanner(System.in);

    private Board board;
    private Player first;
    private Player second;
    private Player current;

    public Game() {
        board = new Board();
        first = new Player("p1", Position.BLACK);
        second = new Player("p2", Position.WHITE);
        current = first;
    }

    public Game(Player p1, Player p2) {
        first = p1;
        second = p2;
        current = p1;
        board = new Board();
    }


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
    public static Board load() {

        System.out.println("Enter filename: ");
        String saveFile = s.nextLine();

        Game game = new Game();

        game.board = new Board(saveFile);

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
        } catch (IOException e) {
            e.printStackTrace();
        }

        game.play();

        return game.board;
    }


    public void play() {


        boolean end = ended();


        while (!end) {

            board.drawboard();

            if (!checkForMoves()) {
                System.out.printf("""
                        Player %s turn
                        1. Save game
                        2. Concede game
                        3. Forfeit turn%n""", current);
                String noMove = s.nextLine();

                switch (noMove) {
                    case "1" -> {
                        System.out.println("Enter FileName:");
                        save(s.nextLine());
                        System.out.println("Saving game...");
                        end = true;
                    }
                    case "2" -> {
                        end = true;
                        System.out.println(current + " has conceded the game.");
                    }
                    case "3" -> {
                        System.out.println("other player turn");
                        current = current == first ? second : first;
                    }
                    default -> System.out.println("Invalid input");
                }

            } else {

                System.out.printf("""
                        Player %s turn
                        1. Save game
                        2. Concede game
                        3. Make move%n""", current);
                String move = s.nextLine();

                switch (move) {
                    case "1" -> {
                        System.out.println("Enter FileName:");
                        save(s.nextLine());
                        System.out.println("Saving game...");
                        end = true;
                    }
                    case "2" -> {
                        end = true;
                        System.out.println(current + " has conceded the game.");
                    }
                    case "3" -> {
                        System.out.println("Enter your move in the format column/row (eg. b2): ");
                        String coor = s.nextLine();
                        if (board.isValid(coor, current.getColor())) {
                            board.takeTurn(coor, current);
                            current = current == first ? second : first;
                        }
                    }
                    default -> System.out.println("Invalid input");
                }
            }
            end = ended();
        }
    }


    private void save(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(first.getName());
            writer.newLine();
            writer.write(first.getName());
            writer.newLine();
            writer.write(current.getName());
            writer.newLine();
            writer.write(board.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    public void makeMove(String coor) {

        int row = Position.coordinates(coor)[0];
        int col = Position.coordinates(coor)[1];


        //set piece of current player based on coordinate if playable
        if (board.getBoardPieces()[row][col].canPlay() && board.flipPiece(coor, (current == first ? Position.BLACK : Position.WHITE))) {
            board.getBoardPieces()[row][col].setPiece((current.equals(first) ? Position.BLACK : Position.WHITE));

        } else {
            System.out.println("unplayable position");
        }

    }

    public boolean checkForMoves() {

        boolean hasMove = false;

        //create array with all coordinates
        String[][] coordinates = new String[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                coordinates[row][col] = "" + ((char) ('A' + col)) + (row + 1);
            }
        }

        //check for all coordinates if ther is a valid move
        outerLoop:
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                if (board.getBoardPieces()[row][col].canPlay() && board.isValid(coordinates[row][col], current.getColor())) {
                    hasMove = true;
                    break outerLoop;
                }

            }
        }
        return hasMove;
    }

    public boolean ended() {

        boolean ended;

        current = current == first ? second : first;
        ended = !checkForMoves();
        current = current == first ? second : first;
        ended |= !checkForMoves();

        if (ended) {
            System.out.println("game has ended");
        } else {
            System.out.println("game has not ended");
        }
        return ended;
    }


}
