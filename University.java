public class University extends Building implements UniversityInterface{

    private int infantryTrained;
    private int catapultTrained;
    private int cavalryTrained;
    public University(){
        this.setSymbol("U");
        this.setLifePoints(30);
        this.setCost(50,30,5);
    }


    @Override
    public void trainInfantry() throws AgeOfEmpiresException {

        if(this.getPlayer().getGame().getPreviousPlayer() != this.getPlayer().getCode()-1)
            throw new AgeOfEmpiresException();

        if(this.getPlayer().getGold()-50 < 0 ){
            throw new AgeOfEmpiresException();
        }

        this.getPlayer().setGold(this.getPlayer().getGold()-50);

        for(Soldier s: getPlayer().getSoldiers()){
            if(s instanceof Swordman || s instanceof Archer || s instanceof Spearman ) {
                s.setLifePoints(s.getLifePoints() + 1);
                if(s instanceof Archer) {
                    ((Archer) s).setSwordPowerToBuilding(((Archer) s).getSwordPowerToBuilding() + 1);
                    ((Archer) s).setArchPowerToBuilding(((Archer) s).getArchPowerToBuilding()+1);
                    ((Archer) s).setArchPowerToHuman(((Archer) s).getArchPowerToHuman()+1);
                    ((Archer) s).setSwordPowerToHuman(((Archer) s).getSwordPowerToHuman()+1);
                }
                else if(s instanceof Spearman){
                    ((Spearman) s).setHorsePower(((Spearman) s).getHorsePower()+1);
                    ((Spearman) s).setOtherPower(((Spearman) s).getOtherPower()+1);
                }
                else
                    s.setPower(s.getPower()+1);
            }
        }
        this.setInfantryTrained(this.getInfantryTrained()+1);
        this.getPlayer().getGame().setPreviousPlayer(this.getPlayer().getCode());
        this.getPlayer().getGame().newTour();
    }

    @Override
    public void trainCavalry() throws AgeOfEmpiresException{
        if(this.getPlayer().getGame().getPreviousPlayer() != this.getPlayer().getCode()-1)
            throw new AgeOfEmpiresException();

        if(this.getPlayer().getGold()-50 < 0 ){
            throw new AgeOfEmpiresException();
        }

        this.getPlayer().setGold(this.getPlayer().getGold()-50);

        for(Soldier s: getPlayer().getSoldiers()){
            if(s instanceof Cavalry){
                ((Cavalry) s).setHorsePower(((Cavalry) s).getHorsePower()+1);
                s.setLifePoints(s.getLifePoints()+1);
                ((Cavalry) s).setBuildingPower(((Cavalry) s).getBuildingPower()+1);
                ((Cavalry) s).setOtherHumanPower(((Cavalry) s).getOtherHumanPower()+1);
            }
        }
        this.setCavalryTrained(this.getCavalryTrained()+1);
        this.getPlayer().getGame().setPreviousPlayer(this.getPlayer().getCode());

        this.getPlayer().getGame().newTour();
    }

    @Override
    public void trainCatapult()throws AgeOfEmpiresException {
        if(this.getPlayer().getGame().getPreviousPlayer() != this.getPlayer().getCode()-1)
            throw new AgeOfEmpiresException();

        if(this.getPlayer().getGold()-50 < 0 ){
            throw new AgeOfEmpiresException();
        }

        this.getPlayer().setGold(this.getPlayer().getGold()-50);

        for(Soldier s: getPlayer().getSoldiers()){
            if(s instanceof Catapult){
                s.setLifePoints(s.getLifePoints()+1);
                ((Catapult) s).setPowerToBuilding(((Catapult) s).getPowerToBuilding()+1);
                s.setPower(s.getPower()+1);
            }
        }
        this.setCatapultTrained(this.getCavalryTrained()+1);
        this.getPlayer().getGame().setPreviousPlayer(this.getPlayer().getCode());

        this.getPlayer().getGame().newTour();
    }

    public void setCatapultTrained(int catapultTrained) {
        this.catapultTrained = catapultTrained;
    }

    public void setCavalryTrained(int cavalryTrained) {
        this.cavalryTrained = cavalryTrained;
    }

    public void setInfantryTrained(int infantryTrained) {
        this.infantryTrained = infantryTrained;
    }

    public int getCatapultTrained() {
        return catapultTrained;
    }

    public int getCavalryTrained() {
        return cavalryTrained;
    }

    public int getInfantryTrained() {
        return infantryTrained;
    }
}
