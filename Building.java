public class Building extends Item implements BuildingInterface {
    private int lifePoints;

    @Override
    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

}
