package Orange.Team2.YimingZhouRobot.strategy;

import Orange.Team2.YimingZhouRobot.movement.*;
import Orange.Team2.YimingZhouRobot.targeting.*;
import Orange.Team2.YimingZhouRobot.*;
import robocode.*;

public class RandomWalkStrategy implements IStrategyController {
	
	AbstractModularRobot robot;
	IMovementController movementController;
	IScanningController scanningController;
	
	public RandomWalkStrategy(AbstractModularRobot robot) {
		this.robot = robot;
		this.movementController = new RandomWalkMovementController(this.robot);
		this.scanningController = new SpinningScanningController(this.robot, new linearFiringController(this.robot));
	}

	@Override
	public void execute() {
		this.scanningController.execute();
		this.movementController.execute();
		this.robot.execute();
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		this.scanningController.onScannedRobot(e);
		
	}

	@Override
	public void onHitByBullet(HitByBulletEvent e) {
		// do nothing for now
	}

	@Override
	public void onHitWall(HitWallEvent e) {
		this.movementController.onHitWall(e);
	}

	@Override
	public void onHitRobot(HitRobotEvent e) {
		this.movementController.onHitRobot(e);
		
	}

	@Override
	public IMovementController getMovementController() {
		return this.movementController;
	}

	@Override
	public IScanningController getScanningController() {
		return this.scanningController;
	}

}
