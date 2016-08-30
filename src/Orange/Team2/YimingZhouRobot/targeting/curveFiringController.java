package Orange.Team2.YimingZhouRobot.targeting;

import Orange.Team2.YimingZhouRobot.*;
import robocode.*;

public class curveFiringController implements IFiringController {

	private AbstractModularRobot robot;
	private IAimingController aimingController;
	
	public curveFiringController(AbstractModularRobot robot) {
		this.robot = robot;
		this.aimingController = new curveAimingController(this.robot);
	}

	@Override
	public void fire(ScannedRobotEvent e) {
		if(this.robot.getGunHeat()==0){
			this.aimingController.fire(e);
		}
	}

}
