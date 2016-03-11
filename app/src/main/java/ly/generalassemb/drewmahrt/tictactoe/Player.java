package ly.generalassemb.drewmahrt.tictactoe;

/**
 * Created by anthony on 3/10/16.
 */
public class Player {
    private String name;
    private String gameMarker;

    public Player() {
    }

    public Player(String name, String gameMarker) {
        this.name = name;
        this.gameMarker = gameMarker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameMarker() {
        return gameMarker;
    }

    public void setGameMarker(String gameMarker) {
        this.gameMarker = gameMarker;
    }
}
