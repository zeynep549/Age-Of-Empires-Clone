
import java.util.ArrayList;

public class Tower extends Building implements  TowerInterface{

    private ArrayList<String> attackArea;
    private int power;

    public Tower(){
        setLifePoints(50);
        setCost(5,10,40);
        this.setSymbol("T");
        this.setPower(2);
        attackArea = new ArrayList<>();
    }

    @Override

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
                    if (item instanceof Catapult) {
                        ((Catapult) item).findAttackArea(item.getX(), item.getY());
                        if (((Catapult) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                            this.setLifePoints(this.getLifePoints() - ((Catapult) item).getPowerToBuilding());
                        }
                        ((Catapult) item).setLifePoints(item.getLifePoints() - 1);
                    }
                    if (item instanceof Spearman) {
                        ((Spearman) item).findAttackArea(item.getX(),item.getY());
                        if (((Spearman) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                            this.setLifePoints(this.getLifePoints() - ((Spearman) item).getOtherPower());
                        }
                        if (this.getLifePoints() > 0)
                            ((Spearman) item).setLifePoints(item.getLifePoints() - this.getPower());
                    }
                    if (item instanceof Archer) {
                        ((Archer) item).findArchAttack(item.getX(),item.getY());
                        ((Archer) item).findSwordAttack(item.getX(),item.getY());
                        if (((Archer) item).getArch_attack().contains(this.getX() + "," + this.getY())) {
                            this.setLifePoints(this.getLifePoints() - ((Archer) item).getArchPowerToBuilding());
                        } else if (((Archer) item).getSword_attack().contains(this.getX() + "," + this.getY())) {
                            this.setLifePoints(this.getLifePoints() - ((Archer) item).getSwordPowerToBuilding());
                        }
                        ((Archer) item).setLifePoints(item.getLifePoints() - this.getPower());
                    }
                    if (item instanceof Swordman) {
                        ((Swordman) item).findAttackArea(item.getX(), item.getY());
                        if (((Swordman) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                            this.setLifePoints(this.getLifePoints() - ((Swordman) item).getPower());
                        }
                        ((Swordman) item).setLifePoints(item.getLifePoints() - this.getPower());
                    }
                    if (item instanceof Cavalry) {
                        ((Cavalry) item).findAttackArea(item.getX(),item.getY());
                        if (((Cavalry) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                            this.setLifePoints(this.getLifePoints() - ((Cavalry) item).getBuildingPower());
                        }
                        ((Cavalry) item).setLifePoints(item.getLifePoints() - this.getPower());
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
                    ((Tower) item).setLifePoints(item.getLifePoints()-this.getPower());
                }
                else if(item instanceof Building){
                    ((Building) item).setLifePoints(item.getLifePoints()-this.getPower());
                }

            }
            else throw new AgeOfEmpiresException();

            this.getPlayer().getGame().setPreviousPlayer(this.getPlayer().getCode());
            this.getPlayer().checksItems();
            item.getPlayer().checksItems();
            this.getPlayer().getGame().newTour();
        }



    public void findAttackArea(int x, int y){
        x = this.getX();
        y = this.getY();
        int temp_y = y+7;
        for(int i = 0; i < 8;i++ ){
            int temp_x1 = x;
            int temp_x2 = x;
            attackArea.add(x + "," + temp_y);
            for (int j = 0; j < i; j++){
                attackArea.add(++temp_x1 + "," + temp_y);
                attackArea.add(--temp_x2+ "," + temp_y);
            }
            temp_y--;
        }
        temp_y = y-7;
        for(int i = 0; i < 7;i++ ){
            int temp_x1 = x;
            int temp_x2 = x;
            attackArea.add(x + "," + temp_y);
            for (int j = 0; j < i; j++){
                attackArea.add(++temp_x1 + "," + temp_y);
                attackArea.add(--temp_x2+ "," + temp_y);
            }
            temp_y++;
        }
        attackArea.remove(x + "," + y);
    }

    public ArrayList<String> getAttackArea(){
        return attackArea;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }
}
