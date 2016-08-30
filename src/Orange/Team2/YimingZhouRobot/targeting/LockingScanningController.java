package Orange.Team2.YimingZhouRobot.targeting;

import Orange.Team2.YimingZhouRobot.AbstractModularRobot;
import robocode.ScannedRobotEvent;

public class LockingScanningController implements IScanningController {

	private AbstractModularRobot robot;
	private IFiringController firingController;
	
	public LockingScanningController(AbstractModularRobot robot, IFiringController firingController) {
		this.robot = robot;
		this.firingController = firingController;
	}

	@Override
	public void execute() {
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
	public void onScannedRobot(ScannedRobotEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void adjustGun(double degree) {
		// TODO Auto-generated method stub

	}

	@Override
	public void adjustRadar(double degree) {
		// TODO Auto-generated method stub

	}

}
