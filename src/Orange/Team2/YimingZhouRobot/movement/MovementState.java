package Orange.Team2.YimingZhouRobot.movement;

public class MovementState {
	private double distance = 0;
	private double turning = 0;
	
	MovementState(double distance, double turning){
		this.distance = distance;
		this.turning = turning;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double heading) {
		this.distance = heading;
	}
	
	public double getTurning() {
		return turning;
	}
	
	public void setTurning(double turning) {
		this.turning = turning;
	}
	
}
