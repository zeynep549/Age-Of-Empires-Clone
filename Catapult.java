import java.util.ArrayList;

public class Catapult extends Soldier{
    private int powerToBuilding;
    private ArrayList<String> attackArea;
    private ArrayList<String> moveArea;


    public Catapult(){
        setLifePoints(10);
        setCost(20,30,5);
        setAttack_distance(1);
        setPowerToBuilding(30);
        setMove_distance(10);
        this.setSymbol("C");
        attackArea = new ArrayList<>();
        moveArea = new ArrayList<>();
    }

    public int getPowerToBuilding() {
        return powerToBuilding;
    }

    public void setPowerToBuilding(int powerToBuilding) {
        this.powerToBuilding = powerToBuilding;
    }

    public void findAttackArea(int x, int y){
        x = this.getX();
        y = this.getY();
        int temp_y = y+10;
        for(int i = 0; i < 11;i++ ){
            int temp_x1 = x;
            int temp_x2 = x;
            attackArea.add(x + "," + temp_y);
            for (int j = 0; j < i; j++){
                attackArea.add(++temp_x1 + "," + temp_y);
                attackArea.add(--temp_x2+ "," + temp_y);
            }
            temp_y--;
        }
        temp_y = y-10;
        for(int i = 0; i < 10;i++ ){
            int temp_x1 = x;
            int temp_x2 = x;
            attackArea.add(x + "," + temp_y);
            for (int j = 0; j < i; j++){
                attackArea.add(++temp_x1 + "," + temp_y);
                attackArea.add(--temp_x2+ "," + temp_y);
            }
            temp_y++;
        }

        ArrayList<String> temp = new ArrayList<>();
        temp_y = y+5;
        for(int i = 0; i < 6;i++ ){
            int temp_x1 = x;
            int temp_x2 = x;
            temp.add(x + "," + temp_y);
            for (int j = 0; j < i; j++){
                temp.add(++temp_x1 + "," + temp_y);
                temp.add(--temp_x2+ "," + temp_y);
            }
            temp_y--;
        }
        temp_y = y-5;
        for(int i = 0; i < 5;i++ ){
            int temp_x1 = x;
            int temp_x2 = x;
            temp.add(x + "," + temp_y);
            for (int j = 0; j < i; j++){
                temp.add(++temp_x1 + "," + temp_y);
                temp.add(--temp_x2+ "," + temp_y);
            }
            temp_y++;
        }

        for(int i = 0; i < temp.size(); i++){
            attackArea.remove(temp.get(i));
        }
    }
    public void move(int x, int y) throws AgeOfEmpiresException {

        if(this.getPlayer().getGame().getPreviousPlayer() != this.getPlayer().getCode()-1)
            throw new AgeOfEmpiresException();
        this.findMoveArea(this.getX(),this.getY());
        if( this.getPlayer().getGame().getKonum().contains(x +","+y))
        {
            throw new AgeOfEmpiresException();
        }
        if(this.getMove_area().contains(x+","+y)){
            String s = this.getX()+","+this.getY();
            if(this.getPlayer().getGame().getKonum().contains(s)
                    && this.getPlayer().getGame().getBasHarf().get(this.getPlayer().getGame().getKonum().indexOf(s)).equals("C")&& !(this.getPlayer().getMainBuilding().getX() == this.getX() &&  this.getPlayer().getMainBuilding().getY() == this.getY())) {
                this.getPlayer().getGame().getKonum().set(this.getPlayer().getGame().getKonum().indexOf(s),x+","+y);}
            else{
                this.getPlayer().getGame().getKonum().add(x+","+y);
                this.getPlayer().getGame().getBasHarf().add("C");
            }
            this.setX(x);
            this.setY(y);
            this.findMoveArea(this.getX(),this.getY());
        }
        else throw new AgeOfEmpiresException();

        this.getPlayer().getGame().setPreviousPlayer(this.getPlayer().getCode());
        this.getPlayer().getGame().newTour();
    }

    private ArrayList<String> getMove_area() {
        return moveArea;
    }

    private void findMoveArea(int x, int y) {
        int temp_x1 = x;
        int temp_y1 = y;
        moveArea.add(++temp_x1+","+ temp_y1);
        temp_x1 = x;
        temp_y1 = y;
        moveArea.add(--temp_x1+","+temp_y1);
        temp_x1 = x;
        temp_y1 = y;
        moveArea.add(temp_x1+"," + ++temp_y1);
        temp_x1 = x;
        temp_y1 = y;
        moveArea.add(temp_x1+"," + --temp_y1);









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
            if( !(item.getPlayer().getMainBuilding().getX() == x && item.getPlayer().getMainBuilding().getY() == y)) {
                if (item instanceof Catapult) {
                    ((Catapult) item).findAttackArea(item.getX(),item.getY());
                    if (((Catapult) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(0);
                    }
                    ((Catapult) item).setLifePoints(0);
                }
                if (item instanceof Spearman) {
                    ((Spearman) item).findAttackArea(item.getX(), item.getY());
                    if (((Spearman) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Spearman) item).getOtherPower());
                    }
                    if (this.getLifePoints() > 0)
                        ((Spearman) item).setLifePoints(0);
                }
                if (item instanceof Archer) {
                    ((Archer) item).findArchAttack(item.getX(),item.getY());
                    ((Archer) item).findSwordAttack(item.getX(),item.getY());
                    if (((Archer) item).getArch_attack().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Archer) item).getArchPowerToHuman());
                    } else if (((Archer) item).getSword_attack().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Archer) item).getSwordPowerToHuman());
                    }
                    ((Archer) item).setLifePoints(0);
                }
                if (item instanceof Swordman) {
                    ((Swordman) item).findAttackArea(item.getX(),item.getY());
                    if (((Swordman) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Swordman) item).getPower());
                    }
                    ((Swordman) item).setLifePoints(0);
                }
                if (item instanceof Cavalry) {
                    ((Cavalry) item).findAttackArea(item.getX(),item.getY());
                    if (((Cavalry) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Cavalry) item).getOtherHumanPower());
                    }
                    ((Cavalry) item).setLifePoints(0);
                }
                if (item instanceof Worker) {
                    ((Worker) item).findAttackArea(item.getX(),item.getY());
                    if (((Worker) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Worker) item).getPower());
                    }
                    ((Worker) item).setLifePoints(item.getLifePoints() - this.getPower());
                }
            } if(item instanceof Tower){
                ((Tower) item).findAttackArea(item.getX(),item.getY());
                if (((Tower)item).getAttackArea().contains(this.getX()+","+this.getY()))
                {
                    this.setLifePoints(this.getLifePoints()-2);
                }
                ((Tower) item).setLifePoints(item.getLifePoints()-this.getPowerToBuilding());
            }
            else if(item instanceof Building){
                ((Building) item).setLifePoints(item.getLifePoints()-this.getPowerToBuilding());
            }
        }
        else throw new AgeOfEmpiresException();

        this.getPlayer().getGame().setPreviousPlayer(this.getPlayer().getCode());
        this.getPlayer().checksItems();
        item.getPlayer().checksItems();
        this.getPlayer().getGame().newTour();
    }

    public ArrayList<String> getAttackArea() {
        return attackArea;
    }


}
