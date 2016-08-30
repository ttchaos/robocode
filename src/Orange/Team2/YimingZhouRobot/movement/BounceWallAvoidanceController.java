package Orange.Team2.YimingZhouRobot.movement;

import Orange.Team2.YimingZhouRobot.*;
import robocode.*;
import robocode.util.Utils;

public class BounceWallAvoidanceController implements IWallAvoidanceController {

	private AbstractModularRobot robot;
	
	public BounceWallAvoidanceController(AbstractModularRobot robot) {
		this.robot = robot;
	}

	@Override
	public boolean execute() {
		double safeDistance = 1.2*Math.max(Rules.MAX_VELOCITY, robot.getWidth());
		double targetHeading = robot.getHeading();

		for(int i=0; i<2; i++){
			if((targetHeading>=0) && (targetHeading<90)){
				//top wall
				if(safeDistance>robot.getBattleFieldHeight()-robot.getY()){
					System.out.println("Hitting top wall");
					targetHeading = (360+180-targetHeading)%360;
					continue;
				}
				//right wall
				if(safeDistance>robot.getBattleFieldWidth()-robot.getX()){
					System.out.println("Hitting right wall");
					targetHeading = 360 - targetHeading;
					continue;
				}
			};
			if((targetHeading>=90) && (targetHeading<180)){
				//bottom wall
				if(safeDistance>robot.getY()){
					System.out.println("Hitting bottom wall");
					targetHeading = (360+180-targetHeading)%360;
					continue;
				}
				//right wall
				if(safeDistance>robot.getBattleFieldWidth()-robot.getX()){
					System.out.println("Hitting right wall");
					targetHeading = 360 - targetHeading;
					continue;
				}
			};
			if((targetHeading>=180) && (targetHeading<270)){
				//bottom wall
				if(safeDistance>robot.getY()){
					System.out.println("Hitting bottom wall");
					targetHeading = (360+180-targetHeading)%360;
					continue;
				}
				//left wall
				if(safeDistance>robot.getX()){
					System.out.println("Hitting left wall");
					targetHeading = 360 - targetHeading;
					continue;
				}
			};
			if((targetHeading>=270) && (targetHeading<360)){
				//top wall
				if(safeDistance>robot.getBattleFieldHeight()-robot.getY()){
					System.out.println("Hitting top wall");
					targetHeading = (360+180-targetHeading)%360;
					continue;
				}
				//left wall
				if(safeDistance>robot.getX()){
					System.out.println("Hitting left wall");
					targetHeading = 360 - targetHeading;
					continue;
				}
			};
		};
		
		if(targetHeading!=robot.getHeading()){
			double turnDegree = Utils.normalRelativeAngleDegrees(targetHeading-this.robot.getHeading());
			System.out.println("Turing "+turnDegree);
			this.robot.getStrategy().getMovementController().stop();
			this.robot.turnRight(turnDegree);
			this.robot.getStrategy().getMovementController().resume();
			return true;
		}
		
		return false;
	
	}

}
