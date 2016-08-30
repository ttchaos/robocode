package Orange.Team2.YimingZhouRobot.targeting;

import Orange.Team2.YimingZhouRobot.*;
import robocode.*;
import robocode.util.Utils;

public class linearAimingController implements IAimingController {

	private AbstractModularRobot robot;
	
	public linearAimingController(AbstractModularRobot robot) {
		this.robot = robot;
	}

	@Override
	public Bullet fire(ScannedRobotEvent e) {
		double bulletPower = Math.min(Rules.MAX_BULLET_POWER, Math.max(Rules.MIN_BULLET_POWER, 200/e.getDistance()));
	    double headOnBearing = this.robot.getHeadingRadians() + e.getBearingRadians();
	    double linearBearing = headOnBearing + Math.asin(e.getVelocity() / Rules.getBulletSpeed(bulletPower) * Math.sin(e.getHeadingRadians() - headOnBearing));
	    double compensateBearing = Math.toDegrees(Utils.normalRelativeAngle(linearBearing - this.robot.getGunHeadingRadians()));
	    this.robot.getStrategy().getMovementController().stop();
	    this.robot.getStrategy().getScanningController().stop();
	    this.robot.getStrategy().getScanningController().adjustGun(-1*compensateBearing);
	    this.robot.setTurnGunRight(compensateBearing);
	    this.robot.waitFor(new GunTurnCompleteCondition(this.robot));
	    Bullet bullet = this.robot.setFireBullet(bulletPower);
	    this.robot.getStrategy().getMovementController().resume();
	    this.robot.getStrategy().getScanningController().resume();
	    return bullet;
	}

}
