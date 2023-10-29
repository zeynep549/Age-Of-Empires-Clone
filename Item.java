import java.io.Serializable;
import java.util.ArrayList;

public class Item implements ItemInterface, Serializable {
    private Player player;
    private int goldCost;
    private int woodCost;
    private int stoneCost;
    private String symbol;

    private int x;
    private int y;
    @Override
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getLifePoints() {
        return 0;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setCost(int gold, int wood, int stone) {
        this.goldCost = gold;
        this.woodCost = wood;
        this.stoneCost = stone;
    }


    public int getGoldCost() {
        return goldCost;
    }

    public int getStoneCost() {
        return stoneCost;
    }

    public int getWoodCost() {
        return woodCost;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


}
