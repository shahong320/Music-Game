package GamePackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Challenge.Challenge;
import Challenge.ChallengeType;
import Challenge.Difficulty;

public class UserInform implements Serializable{
	private ArrayList<Challenge> trophys;
	private HashMap<String, Integer> scores;
	
	public UserInform (){
		scores = new HashMap<String, Integer>();
		trophys = new ArrayList<Challenge> ();
		initializeTrophyList ();
	}
	
	public List<Challenge> gettrophyList (){
		return trophys;
	}
	
	public HashMap<String, Integer> getScores(){
		return scores;
	}
	
	public boolean addToMap (String songName, int score){
		if (scores.put(songName, score) != null)
			return true;
		else
			return false;
	}
	
	public void initializeTrophyList (){
		trophys.add(new Challenge(0, 20, "Score more than 20 score in a game.", ChallengeType.TotalScore,  Difficulty.EASY));
		trophys.add(new Challenge(0, 100, "Score more than 100 score in a game.", ChallengeType.TotalScore,  Difficulty.NORMAL));
		trophys.add(new Challenge(0, 200, "Score more than 200 score in a game.", ChallengeType.TotalScore,  Difficulty.HARD));
		trophys.add(new Challenge(0, 10, "Get more than 10 combo in a game.", ChallengeType.Combo,  Difficulty.EASY));
		trophys.add(new Challenge(0, 30, "Get more than 30 score in a game.", ChallengeType.Combo,  Difficulty.NORMAL));
	}
}