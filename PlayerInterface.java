
public interface PlayerInterface {
	
	
	int getWood();
	
	int getGold();
	
	int getStone();
	
	int getTowerCount();
	
	int getWorkerCount();
	
	
	int getSoldierCount();
	
	

	void pass() throws AgeOfEmpiresException;
	

	Soldier getSoldier(int index) throws AgeOfEmpiresException;
	

	Worker getWorker(int index) throws AgeOfEmpiresException;
	

	void purchase(Item item) throws AgeOfEmpiresException;
	
	University getUniversity();
	

	Tower getTower(int index) throws AgeOfEmpiresException;

}
