package Orange.Team2.YimingZhouRobot.targeting;

import Orange.Team2.YimingZhouRobot.*;
import robocode.*;
import robocode.util.Utils;

public class spotAimingController implements IAimingController {

	private AbstractModularRobot robot;
	
	public spotAimingController(AbstractModularRobot robot) {
		this.robot = robot;
	}

	@Override
	public Bullet fire(ScannedRobotEvent e) {
		double bulletPower = Math.min(Rules.MAX_BULLET_POWER, Math.max(Rules.MIN_BULLET_POWER, 200/e.getDistance()));
	    Bullet bullet = this.robot.setFireBullet(bulletPower);
	    return bullet;
	}

}
