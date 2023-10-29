import java.io.Serializable;

public class Map implements MapInterface, Serializable {
   private Game g1;
     public void print() {
        System.out.println(status());

    }


     public String status() {
        String s = "";
        for (int j = 1; j < 51; j++) {
            for (int i = 1; i < 101; i++) {
                if (g1.getKonum().contains(i + "," + j))
                    s += g1.getBasHarf().get(g1.getKonum().indexOf(i + "," + j));
                else
                    s += "_";
            }
            s += "\n";
        }
        return s;
    }

    public void setG1(Game g1) {
        this.g1 = g1;
    }

    public Game getG1() {
        return g1;
    }
}
