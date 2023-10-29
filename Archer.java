import java.lang.reflect.Array;
import java.util.ArrayList;

public class Archer extends Soldier{
    private int archPowerToHuman;
    private int archPowerToBuilding;
    private int swordPowerToBuilding;
    private int swordPowerToHuman;
    private int archDistane;
    private int swordDistance;
    private ArrayList<String> sword_attack;
    private ArrayList<String> arch_attack;
    private ArrayList<String> move_area;
    public Archer(){
        setLifePoints(5);
        setCost(2,5,0);
        setAttack_distance(1);
        setMove_distance(2);
        setArchPowerToBuilding(1);
        setSwordPowerToBuilding(2);
        setArchPowerToHuman(2);
        setSwordPowerToHuman(2);
        this.setSymbol("O");
        move_area = new ArrayList<>();
        arch_attack = new ArrayList<>();
        sword_attack = new ArrayList<>();
    }

    public void setArchPowerToBuilding(int archPowerToBuilding) {
        this.archPowerToBuilding = archPowerToBuilding;
    }

    public void setSwordPowerToBuilding(int swordPowerToBuilding) {
        this.swordPowerToBuilding = swordPowerToBuilding;
    }

    public void setSwordPowerToHuman(int swordPowerToHuman) {
        this.swordPowerToHuman = swordPowerToHuman;
    }

    public void setArchPowerToHuman(int archPowerToHuman) {
        this.archPowerToHuman = archPowerToHuman;
    }

    public int getArchPowerToBuilding() {
        return archPowerToBuilding;
    }

    public int getSwordPowerToBuilding() {
        return swordPowerToBuilding;
    }

    public int getArchPowerToHuman() {
        return archPowerToHuman;
    }

    public int getSwordPowerToHuman() {
        return swordPowerToHuman;
    }

    public void findMoveArea(int x,int y){
        x = this.getX();
        y = this.getY();
        move_area.clear();
        int temp_y = y+2;
        for(int i = 0; i < 3;i++ ){
            int temp_x1 = x;
            int temp_x2 = x;
            move_area.add(x + "," + temp_y);
            for (int j = 0; j < i; j++){
                move_area.add(++temp_x1 + "," + temp_y);
                move_area.add(--temp_x2+ "," + temp_y);
            }
            temp_y--;
        }
        temp_y = y-2;
        for(int i = 0; i < 2;i++ ){
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

    public void findSwordAttack(int x, int y){
            sword_attack.clear();
            int temp_x = x;
            int temp_y = y;
            sword_attack.add(temp_x+","+ ++temp_y);
            temp_x = x;
            temp_y = y;
            sword_attack.add(temp_x+","+ --temp_y);
            temp_x = x;
            temp_y = y;
            sword_attack.add(++temp_x+","+ temp_y);
            temp_x = x;
            temp_y = y;
            sword_attack.add(--temp_x+","+ temp_y);
            temp_x = x;
            temp_y = y;
            sword_attack.add(++temp_x+","+ ++temp_y);
            temp_x = x;
            temp_y = y;
            sword_attack.add(--temp_x+","+ --temp_y);
            temp_x = x;
            temp_y = y;
            sword_attack.add(++temp_x+","+ --temp_y);
            temp_x = x;
            temp_y = y;
            sword_attack.add(--temp_x+","+ ++temp_y);
    }

    public void findArchAttack(int x, int y){
        x = this.getX();
        y = this.getY();
        arch_attack.clear();
        int temp_y = y+5;
        for(int i = 0; i < 6;i++ ){
            int temp_x1 = x;
            int temp_x2 = x;
            arch_attack.add(x + "," + temp_y);
            for (int j = 0; j < i; j++){
                arch_attack.add(++temp_x1 + "," + temp_y);
                arch_attack.add(--temp_x2+"," + temp_y);
            }
            temp_y--;
        }
        temp_y = y-5;
        for(int i = 0; i < 5;i++ ){
            int temp_x1 = x;
            int temp_x2 = x;
            arch_attack.add(x + "," + temp_y);
            for (int j = 0; j < i; j++){
                arch_attack.add(++temp_x1 + "," + temp_y);
                arch_attack.add(--temp_x2+ "," + temp_y);
            }
            temp_y++;
        }
        arch_attack.remove(x + "," + y);
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
            if(this.getPlayer().getGame().getKonum().contains(s)&& this.getPlayer().getGame().getBasHarf().get(this.getPlayer().getGame().getKonum().indexOf(s)).equals("O") &&
                    !(this.getPlayer().getMainBuilding().getX() == this.getX() &&  this.getPlayer().getMainBuilding().getY() == this.getY())) {

                this.getPlayer().getGame().getKonum().set(this.getPlayer().getGame().getKonum().indexOf(s),x+","+y);}
            else{
                this.getPlayer().getGame().getKonum().add(x+","+y);
                this.getPlayer().getGame().getBasHarf().add("O");
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
        return move_area;
    }

    public void attack(int x,int y) throws AgeOfEmpiresException{
        this.findSwordAttack(this.getX(),this.getY());
        this.findArchAttack(this.getX(),this.getY());
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

        if(arch_attack.contains(x+","+y)){
            if(!(item.getPlayer().getMainBuilding().getX() == x && item.getPlayer().getMainBuilding().getY() == y))
            {
                if (item instanceof Catapult) {
                    ((Catapult) item).findAttackArea(item.getX(), item.getY());
                    if (((Catapult) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(0);
                    }
                    if (sword_attack.contains(x + "," + y))
                        ((Catapult) item).setLifePoints(item.getLifePoints() - this.getSwordPowerToHuman());
                    else ((Catapult) item).setLifePoints(item.getLifePoints() - this.getArchPowerToHuman());
                }
                if (item instanceof Spearman) {
                    ((Spearman) item).findAttackArea(item.getX(), item.getY());

                    if (((Spearman) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Spearman) item).getOtherPower());
                    }
                    if (sword_attack.contains(x + "," + y)) {
                        if (this.getLifePoints() > 0)
                            ((Spearman) item).setLifePoints(item.getLifePoints() - this.getSwordPowerToHuman());
                    } else if (this.getLifePoints() > 0)
                        ((Spearman) item).setLifePoints(item.getLifePoints() - this.getArchPowerToHuman());
                }
                if (item instanceof Archer) {
                    ((Archer) item).findArchAttack(item.getX(), item.getY());
                    ((Archer) item).findSwordAttack(item.getX(), item.getY());

                    if (((Archer) item).getArch_attack().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Archer) item).getArchPowerToHuman());
                    } else if (((Archer) item).getSword_attack().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Archer) item).getSwordPowerToHuman());
                    }
                    if (sword_attack.contains(x + "," + y)) {
                        ((Archer) item).setLifePoints(item.getLifePoints() - this.getSwordPowerToHuman());
                    } else ((Archer) item).setLifePoints(item.getLifePoints() - this.getArchPowerToHuman());
                }
                if (item instanceof Swordman) {
                    ((Swordman) item).findAttackArea(item.getX(), item.getY());
                    if (((Swordman) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Swordman) item).getPower());
                    }
                    if (sword_attack.contains(x + "," + y)) {
                        ((Swordman) item).setLifePoints(item.getLifePoints() - this.getSwordPowerToHuman());
                    } else ((Swordman) item).setLifePoints(item.getLifePoints() - this.getArchPowerToHuman());
                }
                if (item instanceof Cavalry) {
                    ((Cavalry) item).findAttackArea(item.getX(), item.getY());
                    if (((Cavalry) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Cavalry) item).getOtherHumanPower());
                    }
                    if (sword_attack.contains(x + "," + y))
                        ((Cavalry) item).setLifePoints(item.getLifePoints() - this.getSwordPowerToHuman());
                    else ((Cavalry) item).setLifePoints(item.getLifePoints() - this.getArchPowerToHuman());
                }
                if (item instanceof Worker) {
                    ((Worker) item).findAttackArea(item.getX(), item.getY());
                    if (((Worker) item).getAttackArea().contains(this.getX() + "," + this.getY())) {
                        this.setLifePoints(this.getLifePoints() - ((Worker) item).getPower());
                    }
                    if (sword_attack.contains(x + "," + y))
                        ((Worker) item).setLifePoints(item.getLifePoints() - this.getSwordPowerToHuman());
                    else
                         ((Worker) item).setLifePoints(item.getLifePoints() - this.getArchPowerToHuman());
                }
            }if(item instanceof Tower){
                ((Tower) item).findAttackArea(item.getX(), item.getY());
                if (((Tower)item).getAttackArea().contains(this.getX()+","+this.getY()))
                {
                    this.setLifePoints(this.getLifePoints()-2);
                }
                if(sword_attack.contains(x+","+y))
                    ((Tower) item).setLifePoints(item.getLifePoints()-this.getSwordPowerToBuilding());
                else
                    ((Tower) item).setLifePoints(item.getLifePoints()-this.getArchPowerToBuilding());
            }
            else if(item instanceof Building){
                if(sword_attack.contains(x+","+y))
                     ((Building) item).setLifePoints(item.getLifePoints()-this.getSwordPowerToBuilding());
                else
                    ((Building) item).setLifePoints(item.getLifePoints()-this.getArchPowerToBuilding());
            }

        }
        else throw new AgeOfEmpiresException();

        this.getPlayer().getGame().setPreviousPlayer(this.getPlayer().getCode());
        this.getPlayer().checksItems();
        item.getPlayer().checksItems();
        this.getPlayer().getGame().newTour();
    }

    public ArrayList<String> getSword_attack(){
        return sword_attack;
    }
    public ArrayList<String> getArch_attack(){
        return arch_attack;
    }

}
