
public interface GameInterface {
	
	Player getPlayer(int x);
	
	Map getMap();
	
	void save_text(String filename);

	
	void save_binary(String filename);

}
