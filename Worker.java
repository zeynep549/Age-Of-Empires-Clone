import java.io.PrintWriter;
import java.util.ArrayList;

public class Worker extends Human  implements WorkerInterface{
    private Item item;
    private ArrayList<String> move_area;
    private ArrayList<String> attackArea;

    public Worker(){
        setLifePoints(2);
        setCost(1,0,0);
        setPower(1);
        setAttack_distance(1);
        setMove_distance(3);
        setDefence(1);
        this.setSymbol("W");
        move_area = new ArrayList<>();
        attackArea = new ArrayList<>();
    }
    @Override
    public void build(Building b) throws AgeOfEmpiresException{
        if (this.getPlayer() == null) throw new AgeOfEmpiresException();
        if(this.getPlayer().getGame().getPreviousPlayer() != this.getPlayer().getCode()-1)
            throw new AgeOfEmpiresException();

        if(b instanceof MainBuilding || (b instanceof University && this.getPlayer().getUniversity()!= null) ){
            throw new AgeOfEmpiresException();
        }

        for(Player p: this.getPlayer().getGame().getPlayerList()){
            for(Building b1: p.getBuildings()){
                if(b1.getX() == this.getX() && b1.getY() == this.getY()){
                    throw new AgeOfEmpiresException();
                }
            }
            for(Soldier s: p.getSoldiers()){
                if(s.getX() == this.getX() && s.getY() == this.getY()){
                    throw new AgeOfEmpiresException();
                }
            }
            for(Worker w: p.getWorkers()){
                if(w != this)
                {
                    if (w.getX() == this.getX() && w.getY() == this.getY()) {
                        throw new AgeOfEmpiresException();
                    }
                }
            }
        }

        if(this.getPlayer().getGold()-b.getGoldCost() >= 0 && this.getPlayer().getWood()-b.getWoodCost() >= 0 &&
                this.getPlayer().getStone()-b.getStoneCost() >= 0 ){
            this.getPlayer().setGold(this.getPlayer().getGold()-b.getGoldCost());
            this.getPlayer().setWood(this.getPlayer().getWood()-b.getWoodCost());
            this.getPlayer().setStone(this.getPlayer().getStone()-b.getStoneCost());

            b.setX(this.getX());
            b.setY(this.getY());
            b.setPlayer(this.getPlayer());
            this.getPlayer().getBuildings().add(b);
            if(b instanceof University){
                this.getPlayer().setUniversity((University)b);
            }
            if(b instanceof Tower)
                this.getPlayer().getTowers().add((Tower) b);

            this.getPlayer().getGame().getBasHarf().set(this.getPlayer().getGame().getKonum().indexOf(b.getX() + "," + b.getY()),b.getSymbol());

        }else throw new AgeOfEmpiresException();

        this.getPlayer().getGame().setPreviousPlayer(this.getPlayer().getCode());
        this.getPlayer().getGame().newTour();
    }

    public void move(int x, int y) throws AgeOfEmpiresException {
        if(this.getPlayer().getGame().getPreviousPlayer() != this.getPlayer().getCode()-1)
            throw new AgeOfEmpiresException();
        this.findMoveArea(this.getX(),this.getY());
        if (this.getPlayer().getGame().getKonum().contains(x +","+y))
        {
            throw new AgeOfEmpiresException();
        }
        if(this.getMove_area().contains(x+","+y)){
            String s = this.getX()+","+this.getY();

            if(this.getPlayer().getGame().getKonum().contains(s) && this.getPlayer().getGame().getBasHarf().get(this.getPlayer().getGame().getKonum().indexOf(s)).equals("W") &&
                    !(this.getPlayer().getMainBuilding().getX() == this.getX() &&  this.getPlayer().getMainBuilding().getY() == this.getY())) {

                this.getPlayer().getGame().getKonum().set(this.getPlayer().getGame().getKonum().indexOf(s), x + "," + y);


            }

            else{
                this.getPlayer().getGame().getKonum().add(x+","+y);
                this.getPlayer().getGame().getBasHarf().add("W");
            }
            this.setX(x);
            this.setY(y);
            this.findMoveArea(this.getX(),this.getY());
        }
        else throw new AgeOfEmpiresException();

        this.getPlayer().getGame().setPreviousPlayer(this.getPlayer().getCode());
        this.getPlayer().getGame().newTour();
    }

    public void attack(int x,int y) throws AgeOfEmpiresException{
        this.findAttackArea(this.getX(),this.getY());
        if(this.getPlayer() == null) throw new AgeOfEmpiresException();
        if(this.getPlayer().getGame().getPreviousPlayer() != this.getPlayer().getCode()-1)
            throw new AgeOfEmpiresException();
        Item item = null;
        Game g = this.getPlayer().getGame();
        for (int i = 0; i < g.getPlayerCount();i++){
            Player p = g.getPlayer(i);
            if (this.getPlayer() != p) {
                for (int j = 0; j < p.getItems().size(); j++) {
                    Item item2 = (Item) p.getItems().get(i);
                    if (item2.getX() == x && item2.getY() == y) {
                        item = item2;
                        break;
                    }
                }
            }
        }
        if (item == null) throw new AgeOfEmpiresException();

        if(attackArea.contains(x+","+y)){

            if(!(item.getPlayer().getMainBuilding().getX() == x && item.getPlayer().getMainBuilding().getY() == y)) {
                if (item instanceof Catapult) {
                    ((Catapult) item).findAttackArea(item.getX(),item.getY());
                    if (((Catapult) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(0);
                    }
                    ((Catapult) item).setLifePoints(item.getLifePoints() - 1);
                }
                if (item instanceof Spearman) {
                    ((Spearman) item).findAttackArea(item.getX(),item.getY());
                    if (((Spearman) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Spearman) item).getOtherPower());
                    }
                    if (this.getLifePoints() > 0)
                        ((Spearman) item).setLifePoints(item.getLifePoints() - 1);
                }
                if (item instanceof Archer) {
                    ((Archer) item).findArchAttack(item.getX(),item.getY());
                    ((Archer) item).findSwordAttack(item.getX(),item.getY());

                    if (((Archer) item).getArch_attack().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Archer) item).getArchPowerToHuman());
                    } else if (((Archer) item).getSword_attack().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Archer) item).getSwordPowerToHuman());
                    }
                    ((Archer) item).setLifePoints(item.getLifePoints() - 1);
                }
                if (item instanceof Swordman) {
                    ((Swordman) item).findAttackArea(item.getX(),item.getY());
                    if (((Swordman) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Swordman) item).getPower());
                    }
                    ((Swordman) item).setLifePoints(item.getLifePoints() - 1);
                }
                if (item instanceof Cavalry) {
                    ((Cavalry) item).findAttackArea(item.getX(),item.getY());
                    if (((Cavalry) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Cavalry) item).getOtherHumanPower());
                    }
                    ((Cavalry) item).setLifePoints(item.getLifePoints() - 1);
                }
                if (item instanceof Worker) {
                    ((Worker) item).findAttackArea(item.getX(),item.getY());
                    if (((Worker) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Worker) item).getPower());
                    }
                    ((Worker) item).setLifePoints(item.getLifePoints() - this.getPower());
                }
            }if(item instanceof Tower){
                ((Tower) item).findAttackArea(item.getX(),item.getY());
                if (((Tower)item).getAttackArea().contains(this.getX()+","+this.getY()))
                {
                    this.setLifePoints(this.getLifePoints()-2);
                }
                ((Tower) item).setLifePoints(item.getLifePoints()-1);
            }
            else if(item instanceof Building){
                ((Building) item).setLifePoints(item.getLifePoints()-1);
            }

        }
        else throw new AgeOfEmpiresException();

        this.getPlayer().getGame().setPreviousPlayer(this.getPlayer().getCode());
        this.getPlayer().checksItems();
        item.getPlayer().checksItems();
        this.getPlayer().getGame().newTour();
    }

    public void findMoveArea(int x,int y){

        x = this.getX();
        y = this.getY();
        move_area.clear();
        int temp_y = y+3;
        for(int i = 0; i < 4;i++ ){
            int temp_x1 = x;
            int temp_x2 = x;
            move_area.add(x + "," + temp_y);
            for (int j = 0; j < i; j++){
                move_area.add(++temp_x1 + "," + temp_y);
                move_area.add(--temp_x2 + "," + temp_y);
            }
            temp_y--;
        }
        temp_y = y-3;
        for(int i = 0; i < 3;i++ ){
            int temp_x1 = x;
            int temp_x2 = x;
            move_area.add(x + "," + temp_y);
            for (int j = 0; j < i; j++){
                move_area.add(++temp_x1 + "," + temp_y);
                move_area.add(--temp_x2+ "," + temp_y);
            }
            temp_y++;
        }
        move_area.remove(x + "," + y);


    }

    public void findAttackArea(int x, int y){
        attackArea.clear();
        int temp_x = x;
        int temp_y = y;
        attackArea.add(temp_x+","+ ++temp_y);
        temp_x = x;
        temp_y = y;
        attackArea.add(temp_x+","+ --temp_y);
        temp_x = x;
        temp_y = y;
        attackArea.add(++temp_x+","+ temp_y);
        temp_x = x;
        temp_y = y;
        attackArea.add(--temp_x+","+ temp_y);
        temp_x = x;
        temp_y = y;
        attackArea.add(++temp_x+","+ ++temp_y);
        temp_x = x;
        temp_y = y;
        attackArea.add(--temp_x+","+ --temp_y);
        temp_x = x;
        temp_y = y;
        attackArea.add(++temp_x+","+ --temp_y);
        temp_x = x;
        temp_y = y;
        attackArea.add(--temp_x+","+ ++temp_y);
    }

    public ArrayList<String> getAttackArea() {
        return attackArea;
    }

    public ArrayList<String> getMove_area(){
        return move_area;
    }





}
