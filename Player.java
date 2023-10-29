
import java.util.ArrayList;

public class Player extends Game implements PlayerInterface{
    private int wood;
    private int gold;
    private int stone;
    private  ArrayList<Tower> towers;
    private  ArrayList<Soldier> soldiers;
    private ArrayList<Worker> workers;
    private ArrayList<Building> buildings;
    private University university;
    private MainBuilding mainBuilding ;
    private ArrayList<Item> items;
    private Game game;
    private  Worker worker;
    private  int PlayerCode;
    private Player Player;





    public  Player()
    {
        this.wood = 100;
        this.gold = 100;
        this.stone = 100;
        this.towers = new ArrayList<>();
        this.soldiers = new ArrayList<>();
        this.workers = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.items = new ArrayList<>();
        this.university = null;
       // this.player = player;
       // this.mainBase = new Base();
        this.mainBuilding = new MainBuilding();
        this.mainBuilding.setPlayer(this);
        this.worker = worker;

    }
    @Override public int getWood() {
        return wood;
    }

    @Override
    public int getGold() {
        return gold;
    }

    @Override
    public int getStone() {
        return stone;
    }

    @Override
    public int getTowerCount() {
        return towers.size();
    }

    @Override
    public int getWorkerCount() {
        return workers.size();
    }

    @Override
    public int getSoldierCount() {
        return soldiers.size();
    }

    @Override
    public void pass() throws AgeOfEmpiresException{
        if(this.getGame().getPreviousPlayer() != this.getCode()-1)
            throw new AgeOfEmpiresException();

        this.getGame().setPreviousPlayer(this.getCode());
        this.getGame().newTour();
    }

    @Override
    public Soldier getSoldier(int index) {
        return soldiers.get(index);
    }

    @Override
    public Worker getWorker(int index) {
        return workers.get(index);
    }

    @Override
    public void purchase(Item item) throws AgeOfEmpiresException{
        if(item instanceof Human && (this.getSoldiers().size() + this.getWorkers().size()  == 20))
            throw new AgeOfEmpiresException();

        if(item instanceof Building) throw new AgeOfEmpiresException();
        if(this.getGame().getPreviousPlayer() != this.getCode()-1) throw new AgeOfEmpiresException();

        if(getGold()-item.getGoldCost() >= 0 && getStone()-item.getStoneCost() >= 0 && getWood()-item.getWoodCost() >= 0){
            setGold(getGold()-item.getGoldCost());
            setWood(getWood()-item.getWoodCost());
            setStone(getStone()-item.getStoneCost());


            item.setX(getMainBuilding().getX());
            item.setY(getMainBuilding().getY());
            item.setPlayer(this);
            items.add(item);

            if(this.university != null){
                if(this.university.getInfantryTrained() >0)
                {
                    if (item instanceof Swordman || item instanceof Archer || item instanceof Spearman) {
                        if (item instanceof Archer) {
                            ((Archer)item).setLifePoints(item.getLifePoints() + this.university.getInfantryTrained());
                            ((Archer) item).setSwordPowerToBuilding(((Archer) item).getSwordPowerToBuilding() + this.university.getInfantryTrained());
                            ((Archer) item).setArchPowerToBuilding(((Archer) item).getArchPowerToBuilding() + this.university.getInfantryTrained());
                            ((Archer) item).setArchPowerToHuman(((Archer) item).getArchPowerToHuman() + this.university.getInfantryTrained());
                            ((Archer) item).setSwordPowerToHuman(((Archer) item).getSwordPowerToHuman() + this.university.getInfantryTrained());
                        } else if (item instanceof Spearman) {
                            ((Spearman)item).setLifePoints(item.getLifePoints() + this.university.getInfantryTrained());
                            ((Spearman) item).setHorsePower(((Spearman) item).getHorsePower() + this.university.getInfantryTrained());
                            ((Spearman) item).setOtherPower(((Spearman) item).getOtherPower() + this.university.getInfantryTrained());
                        } else {
                            ((Swordman)item).setLifePoints(item.getLifePoints() + this.university.getInfantryTrained());
                            ((Swordman) item).setPower(((Swordman) item).getPower() + this.university.getInfantryTrained());
                        }
                    }
                }

                if(this.university.getCavalryTrained()>0)
                {
                    if (item instanceof Cavalry) {
                        ((Cavalry) item).setHorsePower(((Cavalry) item).getHorsePower() + this.university.getCavalryTrained());
                        ((Cavalry) item).setLifePoints(item.getLifePoints() + this.university.getCavalryTrained());
                        ((Cavalry) item).setBuildingPower(((Cavalry) item).getBuildingPower() + this.university.getCavalryTrained());
                        ((Cavalry) item).setOtherHumanPower(((Cavalry) item).getOtherHumanPower() + this.university.getCavalryTrained());
                    }
                }

                if(this.university.getCatapultTrained()>0)
                {
                    if (item instanceof Catapult) {
                        ((Catapult) item).setLifePoints(((Catapult) item).getLifePoints() + this.university.getCatapultTrained());
                        ((Catapult) item).setPowerToBuilding(((Catapult) item).getPowerToBuilding() + this.university.getCatapultTrained());
                        ((Catapult) item).setPower(((Catapult) item).getPower() + this.university.getCatapultTrained());
                    }
                }
            }


            if(item instanceof Soldier) {
                soldiers.add((Soldier) item);
            }

            if (item instanceof Worker)
                workers.add((Worker) item);
        }
        else throw new AgeOfEmpiresException();

        this.getGame().setPreviousPlayer(this.getCode());
        this.getGame().newTour();
    }

    @Override
    public University getUniversity() {
        return university;
    }

    @Override
    public Tower getTower(int index) throws AgeOfEmpiresException{
        if ( index <towers.size())
            return towers.get(index);
        else throw new AgeOfEmpiresException();
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }

    public void setWood(int wood){
        this.wood = wood;
    }
    public Game getGame(){
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public int getCode() {
        return super.getCode();
    }
    public ArrayList getItems(){
        return items;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }
    public void checksItems(){
        for(Worker w: workers){
            if(w.getLifePoints() <= 0) {
                this.getGame().getBasHarf().remove(this.getGame().getKonum().indexOf(w.getX() + "," + w.getY()));
                this.getGame().getKonum().remove(w.getX() + "," + w.getY());
            }
        }
        for(Soldier s: soldiers){
            if(s.getLifePoints() <= 0) {
                this.getGame().getBasHarf().remove(this.getGame().getKonum().indexOf(s.getX() + "," + s.getY()));
                this.getGame().getKonum().remove(s.getX() + "," + s.getY());
            }
        }
        for(Building b: buildings){
            if(b.getLifePoints() <= 0) {
                this.getGame().getBasHarf().remove(this.getGame().getKonum().indexOf(b.getX() + "," + b.getY()));
                this.getGame().getKonum().remove(b.getX() + "," + b.getY());
            }
        }
        boolean hasUniversity = false;
        boolean stillHasUniversity = false;
        int infantry = 0;
        int cavalry = 0;
        int catapult = 0;
        if(this.university != null) {
            hasUniversity = true;
            if(this.university.getCatapultTrained()>0)
                catapult = this.getUniversity().getCatapultTrained();
            if(this.university.getCavalryTrained()>0)
                cavalry = this.getUniversity().getCavalryTrained();
            if(this.university.getInfantryTrained()>0)
                infantry = this.getUniversity().getInfantryTrained();
        }
        workers.removeIf(w -> w.getLifePoints() <= 0);
        soldiers.removeIf(s -> s.getLifePoints() <= 0);
        buildings.removeIf(b -> b.getLifePoints() <= 0);
        towers.removeIf(t -> t.getLifePoints() <= 0);
        if(this.getBuildings().size()>0 &&!(this.getBuildings().get(0) instanceof MainBuilding) ){
            this.mainBuilding = null;
        }
        else if (this.getBuildings().size() == 0)
            this.mainBuilding = null;

        for(Building b: buildings)
        {
            if(b instanceof University){
                stillHasUniversity = true;
            }
        }

        if(hasUniversity && !stillHasUniversity ){
            for(Soldier s : soldiers){
                if(infantry>0)
                {
                    if (s instanceof Swordman || s instanceof Archer || s instanceof Spearman) {
                        s.setLifePoints(s.getLifePoints() - infantry);

                        if (s instanceof Archer) {
                            ((Archer) s).setSwordPowerToBuilding(((Archer) s).getSwordPowerToBuilding() - infantry);
                            ((Archer) s).setArchPowerToBuilding(((Archer) s).getArchPowerToBuilding() - infantry);
                            ((Archer) s).setArchPowerToHuman(((Archer) s).getArchPowerToHuman() - infantry);
                            ((Archer) s).setSwordPowerToHuman(((Archer) s).getSwordPowerToHuman() - infantry);
                        } else if (s instanceof Spearman) {
                            ((Spearman) s).setHorsePower(((Spearman) s).getHorsePower() - infantry);
                            ((Spearman) s).setOtherPower(((Spearman) s).getOtherPower() - infantry);
                        } else
                            s.setPower(s.getPower() - infantry);
                    }
                }

                if(cavalry>0)
                {
                    if (s instanceof Cavalry) {
                        ((Cavalry) s).setHorsePower(((Cavalry) s).getHorsePower() - cavalry);
                        s.setLifePoints(s.getLifePoints() - cavalry);
                        ((Cavalry) s).setBuildingPower(((Cavalry) s).getBuildingPower() - cavalry);
                        ((Cavalry) s).setOtherHumanPower(((Cavalry) s).getOtherHumanPower() - cavalry);
                    }
                }

                if(catapult>0)
                {
                    if (s instanceof Catapult) {
                        s.setLifePoints(s.getLifePoints() - catapult);
                        ((Catapult) s).setPowerToBuilding(((Catapult) s).getPowerToBuilding() - catapult);
                        s.setPower(s.getPower() - catapult);
                    }
                }

            }
            for(Worker w: workers){
                if(w.getLifePoints() <= 0) {
                    this.getGame().getBasHarf().remove(this.getGame().getKonum().indexOf(w.getX() + "," + w.getY()));
                    this.getGame().getKonum().remove(w.getX() + "," + w.getY());
                }
            }
            for(Soldier s: soldiers){
                if(s.getLifePoints() <= 0) {
                    this.getGame().getBasHarf().remove(this.getGame().getKonum().indexOf(s.getX() + "," + s.getY()));
                    this.getGame().getKonum().remove(s.getX() + "," + s.getY());
                }
            }
            for(Building b: buildings){
                if(b.getLifePoints() <= 0) {
                    this.getGame().getBasHarf().remove(this.getGame().getKonum().indexOf(b.getX() + "," + b.getY()));
                    this.getGame().getKonum().remove(b.getX() + "," + b.getY());
                }
            }
            workers.removeIf(w -> w.getLifePoints() <= 0);
            soldiers.removeIf(s -> s.getLifePoints() <= 0);
            buildings.removeIf(b -> b.getLifePoints() <= 0);
            towers.removeIf(t -> t.getLifePoints() <= 0);
        }


    }

    public MainBuilding getMainBuilding() {
        return mainBuilding;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public void setSoldiers(ArrayList<Soldier> soldiers) {
        this.soldiers = soldiers;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public void setWorkers(ArrayList<Worker> workers) {
        this.workers = workers;
    }

    public void setTowers(ArrayList<Tower> towers) {
        this.towers = towers;
    }

    public void setMainBuilding(MainBuilding mainBuilding) {
        this.mainBuilding = mainBuilding;
    }
}
