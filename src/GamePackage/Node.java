package GamePackage;

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
		x = 128 + 100 * lane;
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
		y = 800;
	}
	
	public void move (double speed){
		y+= speed;
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