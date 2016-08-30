package Orange.Team2.YimingZhouRobot.movement;

import java.util.Stack;

import Orange.Team2.YimingZhouRobot.*;
import robocode.*;
import robocode.util.Utils;

public class RandomWalkMovementController implements IMovementController {
	
	private AbstractModularRobot robot;
	private IWallAvoidanceController wallAvoidanceController; 
	private Stack<MovementState> states;

	public RandomWalkMovementController(AbstractModularRobot robot) {
		this.robot = robot;
		this.wallAvoidanceController = new BounceWallAvoidanceController(this.robot);
		this.states = new Stack<MovementState>();
		robot.setAhead(Double.POSITIVE_INFINITY);
	}

	@Override
	public void execute() {
		double turnRemaining = robot.getTurnRemaining();
		if(wallAvoidanceController.execute()){			
				robot.setTurnRight(-1*Math.signum(turnRemaining)*(45+Utils.getRandom().nextInt(45)));
		}
		if(robot.getTurnRemaining() == 0){
			//randomly turning left/right 45 to 90 degree
			int direction = (Utils.getRandom().nextInt(2) > 0) ? 1 : -1;
			int turnDegree = 46 + Utils.getRandom().nextInt(45);
			robot.setTurnRight(direction*turnDegree);
		}
	}

	@Override
	public void onHitRobot(HitRobotEvent e) {
		this.robot.stop();
		this.robot.back(100); 
		this.robot.resume();
		this.robot.setAhead(Double.POSITIVE_INFINITY);
		
	}

	@Override
	public void onHitWall(HitWallEvent e) {
		//this should never happen
	}

	@Override
	public void stop() {
		this.states.push(new MovementState(this.robot.getDistanceRemaining(),this.robot.getTurnRemaining()));
		this.robot.setAhead(0);
		this.robot.setTurnRight(0);
	}

	@Override
	public void resume() {
		MovementState state = this.states.pop();
		this.robot.setAhead(state.getDistance());
		this.robot.setTurnRight(state.getTurning());
	}

	@Override
	public void adjustDistance(double distance) {
		if(!this.states.empty()){
			MovementState state = this.states.peek();
			state.setDistance(state.getDistance()+distance);
		}
		else{
			this.robot.ahead(this.robot.getDistanceRemaining()+distance);
		}
		
	}

	@Override
	public void adjustTurning(double degree) {
		if(!this.states.empty()){
			MovementState state = this.states.peek();
			state.setTurning(state.getTurning()+degree);
		}
		else{
			this.robot.setTurnRight(this.robot.getTurnRemaining()+degree);
		}
	}
	
}
