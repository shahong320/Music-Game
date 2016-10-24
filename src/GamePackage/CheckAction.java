package GamePackage;

import java.util.ArrayList;

import org.newdawn.slick.Input;

public class CheckAction {
	private boolean isOk, isGreat, isMiss, isBad;
	private int[] Keys;
	private Input input;
	private int checkLane;
	private Score score;
	private ArrayList<Node> chars;
	private long showIcon = 0;
	
	public CheckAction (ArrayList<Node> chars, Input input, Score score){
		this.chars = chars;
		this.input = input;
		this.score = score;
		instantiateLanes();
	}
	
	public void instantiateLanes (){
		Keys = new int[4];
		Keys[0] = Input.KEY_A;	
		Keys[1] = Input.KEY_S;	
		Keys[2] = Input.KEY_D;	
		Keys[3] = Input.KEY_F;	
	}

	public void resetState (){
		isOk = false;
		isBad = false;
		isGreat = false;
		isMiss = false;
	}
	
	public void checkUserInput() {
		for (Node node : chars) {
			if (checkLane == node.getLane() && !node.isMissed()) {
				if (node.getY() > 520 && node.getY() < 560) {
					node.stop();
					resetState();
					score.great();
					score.combo();
					isGreat = true;
					showIcon = System.currentTimeMillis();
					break;
				} else if (node.getY() > 500 && node.getY() < 580) {
					node.stop();
					resetState();
					score.nice();
					score.combo();
					showIcon = System.currentTimeMillis();
					isOk = true;
					break;
				} else if (node.getY() > 460 && node.getY() < 620) {
					node.stop();
					resetState();
					score.bad();
					score.resetCombo();
					showIcon = System.currentTimeMillis();
					isBad = true;
					break;
				}
			}
		}
	}
	
	public long getIconShowTime (){
		return showIcon;
	}
	
	public void resetIconTime (){
		showIcon = 0;
	}

	public void checkNode (){
		for (Node node:chars){
			if (node.getY() <= 0.5){
				break;
			}
			if (node.getY() > 700 && !node.isStop() && !node.isMissed()){
				node.markMissed();
				node.stop();
				resetState ();
				score.miss();
				score.resetCombo();
				isMiss = true;
				showIcon = System.currentTimeMillis();
			}
		}
	}

	public void getKey (){
		checkLane = -1;
		for(int i = 0; i < Keys.length; i++){
			if (input.isKeyPressed(Keys[i])){
				checkLane = i;
			}
		}
	}
	
	public boolean isOk (){
		return isOk;
	}
	
	public boolean isGreat (){
		return isGreat;
	}
	
	public boolean isBad (){
		return isBad;
	}
	
	public boolean isMiss (){
		return isMiss;
	}
}
