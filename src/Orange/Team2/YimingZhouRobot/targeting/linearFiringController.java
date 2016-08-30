package Orange.Team2.YimingZhouRobot.targeting;

import Orange.Team2.YimingZhouRobot.*;
import robocode.*;

public class linearFiringController implements IFiringController {

	private AbstractModularRobot robot;
	private IAimingController aimingController;
	
	public linearFiringController(AbstractModularRobot robot) {
		this.robot = robot;
		this.aimingController = new linearAimingController(this.robot);
	}

	@Override
	public void fire(ScannedRobotEvent e) {
		if(this.robot.getGunHeat()==0){
			this.aimingController.fire(e);
		}	
	}

}
