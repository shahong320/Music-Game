package GamePackage;

public enum GameState {
	Start(1), Selection(2), Game(3);
	
	private int value;
	
	private GameState (int value){
		this.value = value;
	}
	
	public int getValue (){
		return value;
	}
}
