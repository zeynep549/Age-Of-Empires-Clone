import java.util.ArrayList;

public class Cavalry extends Soldier{
    private int horsePower;
    private int otherHumanPower;
    private int buildingPower;
    private ArrayList<String> move_area;
    private ArrayList<String> attackArea;
    public Cavalry(){
        setLifePoints(20);
        setCost(10,3,0);
        setAttack_distance(1);
        setMove_distance(9);
        setBuildingPower(5);
        setHorsePower(5);
        setOtherHumanPower(10);
        this.setSymbol("A");
        move_area = new ArrayList<>();
        attackArea = new ArrayList<>();
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public void setBuildingPower(int buildingPower) {
        this.buildingPower = buildingPower;
    }

    public void setOtherHumanPower(int otherHumanPower) {
        this.otherHumanPower = otherHumanPower;
    }

    public int getBuildingPower() {
        return buildingPower;
    }
    public int getHorsePower(){
        return horsePower;
    }

    public int getOtherHumanPower(){
        return otherHumanPower;
    }

    public void findMoveArea(int x,int y){
        x = this.getX();
        y = this.getY();
        move_area.clear();
        int temp_y = y+9;
        for(int i = 0; i < 10;i++ ){
            int temp_x1 = x;
            int temp_x2 = x;
            move_area.add(x + "," + temp_y);
            for (int j = 0; j < i; j++){
                move_area.add(++temp_x1 + "," + temp_y);
                move_area.add(--temp_x2 + "," + temp_y);
            }
            temp_y--;
        }
        temp_y = y-9;
        for(int i = 0; i < 9;i++ ){
            int temp_x1 = x;
            int temp_x2 = x;
            move_area.add(x + "," + temp_y);
            for (int j = 0; j < i; j++){
                move_area.add(++temp_x1 + "," + temp_y);
                move_area.add(--temp_x2 + "," + temp_y);
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

            if(this.getPlayer().getGame().getKonum().contains(s) && this.getPlayer().getGame().getBasHarf().get(this.getPlayer().getGame().getKonum().indexOf(s)).equals("A")&& !(this.getPlayer().getMainBuilding().getX() == this.getX() &&  this.getPlayer().getMainBuilding().getY() == this.getY())) {

                this.getPlayer().getGame().getKonum().set(this.getPlayer().getGame().getKonum().indexOf(s), x + "," + y);


            }

            else{
                this.getPlayer().getGame().getKonum().add(x+","+y);
                this.getPlayer().getGame().getBasHarf().add("A");
            }
            this.setX(x);
            this.setY(y);
            this.findMoveArea(this.getX(),this.getY());
        }
        else throw new AgeOfEmpiresException();

        this.getPlayer().getGame().setPreviousPlayer(this.getPlayer().getCode());
        this.getPlayer().getGame().newTour();
    }

    public ArrayList<String> getMove_area() {
        return move_area;
    }
    public ArrayList<String> getAttackArea() {
        return attackArea;
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
                for (int j = 0; j < p.getWorkers().size(); j++) {
                    Item item2 = p.getWorkers().get(j);
                    if (item2.getX() == x && item2.getY() == y) {
                        item = item2;
                    }
                }
                for (int j = 0; j < p.getSoldiers().size(); j++) {
                    Item item2 = p.getSoldiers().get(j);
                    if (item2.getX() == x && item2.getY() == y) {
                        item = item2;
                    }
                }
                for (int j = 0; j < p.getBuildings().size(); j++) {
                    Item item2 = p.getBuildings().get(j);
                    if (item2.getX() == x && item2.getY() == y) {
                        item = item2;
                    }
                }
            }
        }
        if (item == null) throw new AgeOfEmpiresException();

        if(attackArea.contains(x+","+y)){
            if(!(item.getPlayer().getMainBuilding().getX() == x && item.getPlayer().getMainBuilding().getY() == y)) {
                if (item instanceof Catapult)
                {((Catapult) item).findAttackArea(item.getX(), item.getY());
                    if (((Catapult) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(0);
                    }
                    ((Catapult) item).setLifePoints(item.getLifePoints() - this.getOtherHumanPower());
                }
                if (item instanceof Spearman) {
                    ((Spearman) item).findAttackArea(item.getX(),item.getY());
                    if (((Spearman) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Spearman) item).getOtherPower());
                    }
                    if (this.getLifePoints() > 0)
                        ((Spearman) item).setLifePoints(item.getLifePoints() - this.getOtherHumanPower());
                }
                if (item instanceof Archer) {
                    ((Archer) item).findArchAttack(item.getX(), item.getY());
                    ((Archer) item).findSwordAttack(item.getX(), item.getY());

                    if (((Archer) item).getArch_attack().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Archer) item).getArchPowerToHuman());
                    }  else  if (((Archer) item).getSword_attack().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Archer) item).getSwordPowerToHuman());
                    }
                    ((Archer) item).setLifePoints(item.getLifePoints() - this.getOtherHumanPower());
                }
                if (item instanceof Swordman) {
                    ((Swordman) item).findAttackArea(item.getX(),item.getY());
                    if (((Swordman) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Swordman) item).getPower());
                    }
                    ((Swordman) item).setLifePoints(item.getLifePoints() - this.getOtherHumanPower());
                }
                if (item instanceof Worker) {
                    ((Worker) item).findAttackArea(item.getX(),item.getY());
                    if (((Worker) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Worker) item).getPower());
                    }
                    ((Worker) item).setLifePoints(item.getLifePoints() - this.getOtherHumanPower());
                }
                if (item instanceof Cavalry) {
                    ((Cavalry) item).findAttackArea(item.getX(), item.getY());
                    if (((Cavalry) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Cavalry) item).getOtherHumanPower());
                    }
                    ((Cavalry) item).setLifePoints(item.getLifePoints() - this.getHorsePower());
                }
            } if(item instanceof Tower){
                ((Tower) item).findAttackArea(item.getX(),item.getY());
                if (((Tower)item).getAttackArea().contains(this.getX()+","+this.getY()))
                {
                    this.setLifePoints(this.getLifePoints()-2);
                }
                ((Tower) item).setLifePoints(item.getLifePoints()-this.getBuildingPower());
            }
            else if(item instanceof Building){
                ((Building) item).setLifePoints(item.getLifePoints()-this.getBuildingPower());
            }

        }
        else throw new AgeOfEmpiresException();

        this.getPlayer().getGame().setPreviousPlayer(this.getPlayer().getCode());
        this.getPlayer().checksItems();
        item.getPlayer().checksItems();
        this.getPlayer().getGame().newTour();
    }
}