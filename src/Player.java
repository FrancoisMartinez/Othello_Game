public class Player {

    private String name;

    public Player() {
        name = "player";
    }
    public Player(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
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

