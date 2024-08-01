public class Player {

    private String name;
    private char color;

    public Player() {
        name = "player";
        color = Position.EMPTY;
    }
    public Player(String name, char color) {
        this.name = name;
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public char getColor() {
        return color;
    }

    public String toString() {
        return name;
    }

    /**
    public boolean equals(Player player) {
        return this.name.equals(player.name);
    }
     */

}

