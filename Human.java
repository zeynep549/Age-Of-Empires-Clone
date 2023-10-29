import java.util.ArrayList;

public class Human extends Item implements HumanInterface{

    private int lifePoints;
    private int power;
    private int defence;
    private int attack_distance;
    private int move_distance;
    @Override
    public void attack(int x, int y) throws AgeOfEmpiresException{

    }

    @Override
    public void move(int x, int y) throws AgeOfEmpiresException{


    }

    @Override
    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }


    public int getAttack_distance() {
        return attack_distance;
    }

    public int getDefence() {
        return defence;
    }

    public int getMove_distance() {
        return move_distance;
    }

    public int getPower() {
        return power;
    }

    public void setAttack_distance(int attack_distance) {
        this.attack_distance = attack_distance;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setMove_distance(int move_distance) {
        this.move_distance = move_distance;
    }

    public void setPower(int power) {
        this.power = power;
    }

}
