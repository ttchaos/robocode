package Orange.Team2.YimingZhouRobot.movement;

import robocode.*;

public interface IMovementController {

	public void execute();
	
	public void stop();
	
	public void resume();
	
	public void adjustDistance(double distance);
	
	public void adjustTurning(double degree);
	
	public void onHitWall(HitWallEvent e);

	public void onHitRobot(HitRobotEvent e);

}
