package Orange.Team2.YimingZhouRobot.movement;

import Orange.Team2.YimingZhouRobot.*;
import robocode.*;
import robocode.util.Utils;

public class ReverseWallAvoidanceController implements IWallAvoidanceController {

	private AbstractModularRobot robot;

	public ReverseWallAvoidanceController(AbstractModularRobot robot) {
		this.robot = robot;
	}

	@Override
	public boolean execute() {
		double safeDistance = 1.2 * Math.max(Rules.MAX_VELOCITY, robot.getWidth());
		boolean nearWall = false;
		double normalHeading = getNormalHeading();

		// top wall
		if ((safeDistance > robot.getBattleFieldHeight() - robot.getY()) && (Math.abs(Utils.normalRelativeAngleDegrees(normalHeading)) < 90)) {
			System.out.println("Hitting top wall");

			nearWall = true;
		}
		// right wall
		if ((safeDistance > robot.getBattleFieldWidth() - robot.getX()) && (normalHeading > 0 && normalHeading < 180 )) {
			System.out.println("Hitting right wall");
			nearWall = true;
		}
		// bottom wall
		if ((safeDistance > robot.getY()) && (Math.abs(Utils.normalRelativeAngleDegrees(normalHeading)) > 90)) {
			System.out.println("Hitting bottom wall");
			nearWall = true;
		}
		// left wall
		if ((safeDistance > robot.getX())  && (normalHeading > 180 && normalHeading < 360 )) {
			System.out.println("Hitting left wall");
			nearWall = true;
		}

		if(nearWall){
			System.out.println("Reversing" + -1*this.robot.getDistanceRemaining());
			this.robot.setAhead(-1*this.robot.getDistanceRemaining());
		}
		
		return nearWall;

	}
	
	private double getNormalHeading(){
		int adjustDirection = (this.robot.getDistanceRemaining()>0) ? 0 : 180;
		return Utils.normalAbsoluteAngleDegrees(adjustDirection+this.robot.getHeading());
	}

}
