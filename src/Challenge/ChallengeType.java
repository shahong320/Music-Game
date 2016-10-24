package Challenge;

public enum ChallengeType {

	TotalScore(0), Combo(1);
	
	private int value;
	
	private ChallengeType (int value){
		this.value = value;
	}
	
	public int getValue (){
		return value;
	}
}
