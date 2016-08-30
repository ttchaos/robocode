package Orange.Team2.YimingZhouRobot.movement;

import Orange.Team2.YimingZhouRobot.*;
import robocode.*;

public class SlidingMovementController implements IMovementController {

	private AbstractModularRobot robot;
	private IWallAvoidanceController wallAvoidanceController; 
	private int direction = 1;
	
	public SlidingMovementController(AbstractModularRobot robot) {
		this.robot = robot;
		this.wallAvoidanceController = new ReverseWallAvoidanceController(this.robot);
		this.robot.setAhead(Double.MAX_VALUE);
	}

	@Override
	public void execute() {
		if(this.wallAvoidanceController.execute()){
			this.direction = -1*this.direction;
		};
		
		if(this.robot.getDistanceRemaining() == 0){
			this.robot.setAhead(direction * Double.MAX_VALUE);
		}

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
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adjustDistance(double distance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void adjustTurning(double degree) {
		// TODO Auto-generated method stub
		
	}

}
