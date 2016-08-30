package Orange.Team2.YimingZhouRobot.targeting;

import Orange.Team2.YimingZhouRobot.*;
import robocode.*;
import robocode.util.Utils;

public class SpinningScanningController implements IScanningController {

	private AbstractModularRobot robot;
	private IFiringController firingController;
	
	
	public SpinningScanningController(AbstractModularRobot robot, IFiringController firingController) {
		this.robot = robot;
		this.firingController = firingController;
		this.robot.setTurnGunRight(Double.POSITIVE_INFINITY);
	}

	@Override
	public void execute() {
		
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		if(!this.robot.isTeammate(e.getName())){
			this.firingController.fire(e);
		}
	}

	@Override
	public void adjustGun(double degree) {

	}

	@Override
	public void stop() {
		this.robot.setTurnGunRight(0);
	}

	@Override
	public void resume() {
		this.robot.setTurnGunRight(Double.POSITIVE_INFINITY);
	}

	@Override
	public void adjustRadar(double degree) {
		// TODO Auto-generated method stub
		
	}

}
