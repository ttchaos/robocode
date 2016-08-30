package Orange.Team2.YimingZhouRobot.strategy;

import Orange.Team2.YimingZhouRobot.AbstractModularRobot;
import Orange.Team2.YimingZhouRobot.movement.IMovementController;
import Orange.Team2.YimingZhouRobot.movement.SlidingMovementController;
import Orange.Team2.YimingZhouRobot.targeting.IScanningController;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

public class DuelStrategy implements IStrategyController {

	IMovementController movementController;
	private AbstractModularRobot robot;
	
	public DuelStrategy(AbstractModularRobot robot) {
		this.robot = robot;
		this.movementController = new SlidingMovementController(this.robot);
	}

	@Override
	public void execute() {
		this.movementController.execute();
		this.robot.execute();
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
