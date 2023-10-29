
import java.io.*;

import java.util.ArrayList;



public class Game  implements  GameInterface,Serializable {
    private  int deadPlayers;
    private int initialPlayerCount;
      private ArrayList<Player>  playerList = new ArrayList<>();
      private ArrayList<String>konum = new ArrayList<>();
    private ArrayList<String>basHarf = new ArrayList<>();
    private ArrayList<Worker> workerList = new ArrayList<>();
    private int previousPlayer;
    private Map map ;
    private  int PlayerCode;
    private int playerCount;

      public  Game(int x)
      {
          this.setPreviousPlayer(-1);
          map = new Map();
          map.setG1(this);
          playerCount = x;
          this.deadPlayers = 0;
          this.initialPlayerCount = x;
          Player player = null;
          Worker worker = null;
          int a = 0;
          if( x >= 2 && x<=4) {
              for (int i = 0; i < x; i++) {
                  player = new Player();
                   a = i;
                  player.setCode(a);
                  playerList.add(player);
                  player.setGame(this);
                  worker = new Worker();
                  worker.setX(player.getMainBuilding().getX());
                  worker.setY(player.getMainBuilding().getY());
                  worker.setPlayer(player);
                  player.getWorkers().add(worker);
              }

          }
          if(x == 2) {
              konum.add("1,1");
              basHarf.add("M");
              konum.add("100,50");
              basHarf.add("M");
              this.getPlayer(0).getMainBuilding().setX(1);
              this.getPlayer(0).getMainBuilding().setY(1);
              this.getPlayer(0).getWorker(0).setX(1);
              this.getPlayer(0).getWorker(0).setY(1);
              this.getPlayer(1).getMainBuilding().setX(100);
              this.getPlayer(1).getMainBuilding().setY(50);
              this.getPlayer(1).getWorker(0).setX(100);
              this.getPlayer(1).getWorker(0).setY(50);
              this.getPlayer(0).getBuildings().add(this.getPlayer(0).getMainBuilding());
              this.getPlayer(1).getBuildings().add(this.getPlayer(1).getMainBuilding());




          }
          if(x == 3)
          {
              konum.add("1,1");
              basHarf.add("M");
              konum.add("100,50");
              basHarf.add("M");
              konum.add("1,50");
              basHarf.add("M");
              this.getPlayer(0).getMainBuilding().setX(1);
              this.getPlayer(0).getMainBuilding().setY(1);
              this.getPlayer(0).getWorker(0).setX(1);
              this.getPlayer(0).getWorker(0).setY(1);

              this.getPlayer(1).getMainBuilding().setX(100);
              this.getPlayer(1).getMainBuilding().setY(50);
              this.getPlayer(1).getWorker(0).setX(100);
              this.getPlayer(1).getWorker(0).setY(50);

              this.getPlayer(2).getMainBuilding().setX(1);
              this.getPlayer(2).getMainBuilding().setY(50);
              this.getPlayer(2).getWorker(0).setX(1);
              this.getPlayer(2).getWorker(0).setY(50);
              this.getPlayer(0).getBuildings().add(this.getPlayer(0).getMainBuilding());

              this.getPlayer(1).getBuildings().add(this.getPlayer(1).getMainBuilding());

              this.getPlayer(2).getBuildings().add(this.getPlayer(2).getMainBuilding());


          }
          if(x == 4)
          {
              konum.add("1,1");
              basHarf.add("M");
              konum.add("100,50");
              basHarf.add("M");
              konum.add("1,50");
              basHarf.add("M");
              konum.add("100,1");
              basHarf.add("M");
              this.getPlayer(0).getMainBuilding().setX(1);
              this.getPlayer(0).getMainBuilding().setY(1);
              this.getPlayer(1).getMainBuilding().setX(100);
              this.getPlayer(1).getMainBuilding().setY(50);
              this.getPlayer(2).getMainBuilding().setX(1);
              this.getPlayer(2).getMainBuilding().setY(50);
              this.getPlayer(3).getMainBuilding().setX(100);
              this.getPlayer(3).getMainBuilding().setY(1);

              this.getPlayer(0).getWorker(0).setX(1);
              this.getPlayer(0).getWorker(0).setY(1);
              this.getPlayer(1).getWorker(0).setX(100);
              this.getPlayer(1).getWorker(0).setY(50);
              this.getPlayer(2).getWorker(0).setX(1);
              this.getPlayer(2).getWorker(0).setY(50);
              this.getPlayer(3).getWorker(0).setX(100);
              this.getPlayer(3).getWorker(0).setY(1);

              this.getPlayer(0).getBuildings().add(this.getPlayer(0).getMainBuilding());
              this.getPlayer(1).getBuildings().add(this.getPlayer(1).getMainBuilding());
              this.getPlayer(2).getBuildings().add(this.getPlayer(2).getMainBuilding());
              this.getPlayer(3).getBuildings().add(this.getPlayer(3).getMainBuilding());



          }

      }

      public Game(String filename, boolean binary){
          if(binary){
              try {

                  FileInputStream fileInputStream = new FileInputStream(filename);
                  ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                  Game loadedGame = (Game) objectInputStream.readObject();
                  objectInputStream.close();
                  fileInputStream.close();
                  this.konum = loadedGame.konum;
                  this.basHarf = loadedGame.basHarf;
                  this.previousPlayer = loadedGame.previousPlayer;
                  this.playerList = loadedGame.playerList;
                  this.map = loadedGame.map;
                  this.playerCount = loadedGame.playerCount;
                  this.PlayerCode = loadedGame.PlayerCode;
                  for(int i = 0; i < playerList.size(); i++){
                      this.playerList.get(i).setSoldiers(loadedGame.playerList.get(i).getSoldiers());
                      this.playerList.get(i).setWorkers(loadedGame.playerList.get(i).getWorkers());
                      this.playerList.get(i).setBuildings(loadedGame.playerList.get(i).getBuildings());
                      this.playerList.get(i).setGold(loadedGame.playerList.get(i).getGold());
                      this.playerList.get(i).setStone(loadedGame.playerList.get(i).getStone());
                      this.playerList.get(i).setWood(loadedGame.playerList.get(i).getWood());
                      this.playerList.get(i).setGame(this);
                      this.playerList.get(i).setUniversity(loadedGame.playerList.get(i).getUniversity());
                      this.playerList.get(i).setMainBuilding(loadedGame.playerList.get(i).getMainBuilding());
                      this.playerList.get(i).setTowers(loadedGame.playerList.get(i).getTowers());
                  }

              } catch (IOException | ClassNotFoundException e) {
                  e.printStackTrace();
              }
          }
          else {

          }

      }
      public void setCode(int playerCode)
      {
          this.PlayerCode = playerCode;
      }
    public int getCode()
    {
        return PlayerCode;
    }
      public ArrayList<String>getKonum()
      {
          return this.konum;
      }
      public ArrayList<String>getBasHarf()
      {
          return this.basHarf;
      }
      public Game()
      {

      }
    @Override
    public Player getPlayer(int x) {


        if (x <playerList.size()) {

             /*if (playerList.size() < x) {

                for (int i = playerList.size() + 1; i <= x; i++) {
                    Player player = new Player();
                    playerList.add(player);
                }
            } */
            return playerList.get(x );
        } else {
            // Geçersiz oyuncu numarası durumunda null döndür
            return null;
        }
    }

    @Override
    public Map getMap() {



        return map;
    }

    @Override
    public void save_text(String filename) {

    }

    @Override
    public void save_binary(String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newTour(){
          Player remove_p = null;
          for(Player p: playerList){
              if (p.getMainBuilding() == null){
                  deadPlayers++;
                  playerCount--;
                  remove_p = p;
                  break;
              }
          }
          if(remove_p != null){
              this.playerList.remove(remove_p);
              for(int i = 0;i<playerList.size();i++)
              {
                  playerList.get(i).setCode(i);
              }
              if(previousPlayer == playerCount || remove_p.getCode()<previousPlayer)
                  previousPlayer--;


          }
          if(deadPlayers == initialPlayerCount-1){
              System.out.println("Oyun bitti");
              return;
          }
          if(previousPlayer == playerCount-1 ) {
              for (Player p : playerList) {
                  p.setGold(p.getGold() + 2);
                  p.setWood(p.getWood() + 10);
                  p.setStone(p.getStone() + 5);
              }
              previousPlayer = -1;
          }
    }

    public void setPreviousPlayer(int previousPlayer) {
        this.previousPlayer = previousPlayer;
    }

    public int getPreviousPlayer() {
        return previousPlayer;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}
