package Orange.Team2.YimingZhouRobot.strategy;

import Orange.Team2.YimingZhouRobot.*;
import Orange.Team2.YimingZhouRobot.movement.IMovementController;
import Orange.Team2.YimingZhouRobot.movement.RandomWalkMovementController;
import Orange.Team2.YimingZhouRobot.targeting.IScanningController;
import Orange.Team2.YimingZhouRobot.targeting.LockingScanningController;
import Orange.Team2.YimingZhouRobot.targeting.SpinningScanningController;
import Orange.Team2.YimingZhouRobot.targeting.learningFiringController;
import robocode.*;

public class TrackAndRamStrategy implements IStrategyController {

	AbstractModularRobot robot;
	IMovementController movementController;
	IScanningController scanningController;
	
	public TrackAndRamStrategy(AbstractModularRobot robot) {
		this.robot = robot;
		this.movementController = new RandomWalkMovementController(this.robot);
		this.scanningController = new LockingScanningController(this.robot, new learningFiringController(this.robot));
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHitByBullet(HitByBulletEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHitWall(HitWallEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHitRobot(HitRobotEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IMovementController getMovementController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IScanningController getScanningController() {
		// TODO Auto-generated method stub
		return null;
	}

}
