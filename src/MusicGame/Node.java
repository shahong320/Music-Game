package MusicGame;

public class Node {
	private int lane;
	private float targetBeat;
	private float y;
	private float x;
	private boolean missed = false;
	private boolean stop = false;
	
	public Node(int lane, float targetBeat) {
		this.lane = lane;
		this.targetBeat = targetBeat;
		x = 208 + 100 * lane;
		y = 0;
	}

	public float getTargetBeat() {
		return targetBeat;
	}

	public int getLane() {
		return lane;
	}
	
	public String toString() {
		return "(Lane: " + lane + ", Beat: " + targetBeat + ")";
	}
	
	public void setY(double y){
		this.y = (float) y;
	}
	
	public void stop (){
		stop = true;
		y = 1100;
	}
	
	public void move (){
		y+= 0.4;
	}
	
	public boolean isStop (){
		return stop;
	}
	
	public float getY(){
		return y;
	}
	
	public void markMissed (){
		missed = true;
	}

	public boolean isMissed (){
		return missed;
	}

	public float getX(){
		return x;
	}
}