import java.util.ArrayList;

public class Main {
    public static void mymove(Human human, int x_move, int y_move) throws AgeOfEmpiresException {
        //int player_x = game.getPlayer(player_no).getX();
        //int player_y = game.getPlayer(player_no).getY();

        int human_x = human.getX();
        int human_y = human.getY();

        human.move(human_x + x_move, human_y + y_move);

        //System.out.println(item_x + ", " + item_y);

    }

    public static void main(String[] args) throws AgeOfEmpiresException {
        int x1, x2;
        int y1, y2;
        Game g;

        //**********************************************************************************************************
        // CASE 5 - CATAPULT TOWER ATTACK
        //**********************************************************************************************************
        System.out.println("TEST CASE 5 - CATAPULT TOWER ATTACK");
        try {
            g = new Game(2);
            g.getPlayer(0).purchase(new Catapult());
            mymove(g.getPlayer(1).getWorker(0), -2, -1);
            System.out.println(".......");
            System.out.println(g.getPlayer(1).getWorker(0).getX());
            System.out.println(g.getPlayer(1).getWorker(0).getY());


            int i = 0;
            for (i = 0; i < 98; i++) {
                mymove(g.getPlayer(0).getSoldier(0), 1, 0);
                g.getPlayer(1).pass();
            }

            for (i = 0; i < 40; i++) {
                mymove(g.getPlayer(0).getSoldier(0), 0, 1);
                g.getPlayer(1).pass();
            }

            mymove(g.getPlayer(0).getSoldier(0), 0, 1);
            g.getPlayer(1).getWorker(0).build(new Tower());

            x1 = g.getPlayer(1).getTower(0).getX();
            y1 = g.getPlayer(1).getTower(0).getY();


            System.out.println("P0 Catapult: " + g.getPlayer(0).getSoldier(0).getLifePoints());
            System.out.println("P1 Tower: " + g.getPlayer(1).getTower(0).getLifePoints());
            System.out.println("------");

            g.getPlayer(0).getSoldier(0).attack(x1, y1);
            g.getPlayer(1).pass();

            System.out.println("P0 Catapult: " + g.getPlayer(0).getSoldier(0).getLifePoints());
            System.out.println("P1 Tower: " + g.getPlayer(1).getTower(0).getLifePoints());
            System.out.println("------");


            mymove(g.getPlayer(0).getSoldier(0), 0, 1);

            x2 = g.getPlayer(0).getSoldier(0).getX();
            y2 = g.getPlayer(0).getSoldier(0).getY();

            g.getPlayer(1).getTower(0).attack(x2, y2);

            System.out.println("P0 Catapult: " + g.getPlayer(0).getSoldier(0).getLifePoints());
            System.out.println("P1 Tower is dead");
            System.out.println("------");

            g.getMap().print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}



























