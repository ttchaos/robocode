package Orange.Team2.YimingZhouRobot.strategy;

import Orange.Team2.YimingZhouRobot.movement.IMovementController;
import Orange.Team2.YimingZhouRobot.targeting.IScanningController;
import robocode.*;

public interface IStrategyController {

	public void execute();
	
	public IMovementController getMovementController();
	
	public IScanningController getScanningController();

	public void onScannedRobot(ScannedRobotEvent e);

	public void onHitByBullet(HitByBulletEvent e);

	public void onHitWall(HitWallEvent e);

	public void onHitRobot(HitRobotEvent e);

}
