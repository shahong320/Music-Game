package MusicGame;

import java.io.IOException;

public class Score {
	
	private int great;
	private int nice;
	private int bad;
	private int miss;
	private int score;
	private int combo;
	
	public Score() {
		great = 0;
		nice = 0;
		bad = 0;
		miss = 0;
		score = 0;
	}
	
	public void reset() {
		great = 0;
		nice = 0;
		bad = 0;
		miss = 0;
		score = 0;
		combo = 0;
	}
	
	public void great() {
		great ++;
		combo ++;
		score += 4;
	}
	
	public void nice() {
		combo ++;
		nice ++;
		score += 2;

	}
	
	public void bad() {
		bad ++;
	}
	
	public void miss() {
		miss ++;
		score -= 2;
		combo = 0;
	}
	
	public boolean record() throws IOException{
		FileOut record = new FileOut("Score.saved.chart");
		return record.scoreFileout(this);
	}

	public String toString(){
		return "Great: " + great + "\n   OK: " + nice + "\n Miss: " + miss + "\n  Bad: " + bad + "\nTotal score: " + score;
	}
	
	public int getGreat() {
		return great;
	}

	public int getNice() {
		return nice;
	}

	public int getBad() {
		return bad;
	}

	public int getMiss() {
		return miss;
	}

	public int getScore() {
		return score;
	}

	public int getCombo() {
		return combo;
	}
	
	public void setGreat(int great) {
		this.great = great;
	}

	public void setNice(int nice) {
		this.nice = nice;
	}

	public void setBad(int bad) {
		this.bad = bad;
	}

	public void setMiss(int miss) {
		this.miss = miss;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setCombo(int combo) {
		this.combo = combo;
	}



	
	

}
