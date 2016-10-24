package Challenge;

import java.io.Serializable;

import GamePackage.Score;
/**
 * Structure of Challenge 
 */
public class Challenge implements Serializable{
	
	private int ID;
	private String description;
	private ChallengeType type;
	private int limit;
	private Difficulty difficulty;
	private boolean isComplete;
	
	public Challenge(int ID, int limit, String description, ChallengeType type, Difficulty difficulty){
			this.setDescription(description);
			this.setDifficulty(difficulty);
			this.type = type;
			this.ID = ID;
			this.limit = limit;
			setComplete(false);
	}
	
	/**
	 * check a Score could achieve this Challenge return boolean as result
	 * @param score
	 * @return boolean
	 */
	public boolean isComplete(Score score){
		if(this.type==ChallengeType.TotalScore){			
			return limit < score.getScore();
		}
		else if(this.type==ChallengeType.Combo){
			return limit < score.getBestCombo();
		}
		
		return false ;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Difficulty getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}


	public boolean isComplete() {
		return isComplete;
	}


	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	public int getLimit (){
		return limit;
	}
}
