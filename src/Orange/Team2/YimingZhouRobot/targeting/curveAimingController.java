package Orange.Team2.YimingZhouRobot.targeting;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

import Orange.Team2.YimingZhouRobot.*;
import robocode.*;
import robocode.util.Utils;

public class curveAimingController implements IAimingController {

	private AbstractModularRobot robot;
	private Map<String,EnemyInfo> lastSeenEnemyInfo;
	
	public curveAimingController(AbstractModularRobot robot) {
		this.robot = robot;
		lastSeenEnemyInfo = new HashMap<String,EnemyInfo>();
	}

	@Override
	public Bullet fire(ScannedRobotEvent e) {
		if(!this.lastSeenEnemyInfo.containsKey(e.getName())){
			this.lastSeenEnemyInfo.put(e.getName(), new EnemyInfo(this.robot.getTime(),e.getHeadingRadians()));
			return null;
		}
		
		long timeElapsed = this.robot.getTime() - this.lastSeenEnemyInfo.get(e.getName()).getTime();
		System.out.println("Time elapsed " + timeElapsed);
		if(timeElapsed > 30){
			lastSeenEnemyInfo.get(e.getName()).setHeading(e.getBearingRadians());
			lastSeenEnemyInfo.get(e.getName()).setTime(this.robot.getTime());
			return null;
		}
		
		double bulletPower = Math.min(Rules.MAX_BULLET_POWER, Math.max(Rules.MIN_BULLET_POWER, 200/e.getDistance()));
		double absoluteBearing = robot.getHeadingRadians() + e.getBearingRadians();
		double enemyX = robot.getX() + e.getDistance() * Math.sin(absoluteBearing);
		double enemyY = robot.getY() + e.getDistance() * Math.cos(absoluteBearing);
		double enemyHeading = e.getHeadingRadians();
		double enemyHeadingChange = (enemyHeading - lastSeenEnemyInfo.get(e.getName()).getHeading())/timeElapsed;
		double enemyVelocity = e.getVelocity();
		lastSeenEnemyInfo.get(e.getName()).setHeading(e.getBearingRadians());
		lastSeenEnemyInfo.get(e.getName()).setTime(this.robot.getTime());
		 
		double deltaTime = 0;
		double battleFieldHeight = robot.getBattleFieldHeight(), 
		       battleFieldWidth = robot.getBattleFieldWidth();
		double predictedX = enemyX, predictedY = enemyY;
		double halfRobotWidth = this.robot.getWidth()/2;
		while((++deltaTime) * Rules.getBulletSpeed(bulletPower) < 
		      Point2D.Double.distance(this.robot.getX(), this.robot.getY(), predictedX, predictedY)){		
			predictedX += Math.sin(enemyHeading) * enemyVelocity;
			predictedY += Math.cos(enemyHeading) * enemyVelocity;
			enemyHeading += enemyHeadingChange;
			if(	predictedX < halfRobotWidth
				|| predictedY < halfRobotWidth
				|| predictedX > battleFieldWidth - halfRobotWidth
				|| predictedY > battleFieldHeight - halfRobotWidth){
		 
				predictedX = Math.min(Math.max(halfRobotWidth, predictedX), 
				    battleFieldWidth - halfRobotWidth);	
				predictedY = Math.min(Math.max(halfRobotWidth, predictedY), 
				    battleFieldHeight - halfRobotWidth);
				break;
			}
		}
		double theta = Utils.normalAbsoluteAngle(Math.atan2(
		    predictedX - robot.getX(), predictedY - robot.getY()));
		 
		double compensateBearing = Utils.normalRelativeAngle(theta - robot.getGunHeadingRadians());
		
	    this.robot.getStrategy().getMovementController().stop();
	    this.robot.getStrategy().getScanningController().stop();		
	    this.robot.getStrategy().getScanningController().adjustGun(-1*Math.toDegrees(compensateBearing));
		this.robot.setTurnGunRightRadians(compensateBearing);
	    this.robot.waitFor(new GunTurnCompleteCondition(this.robot));
	    Bullet bullet = this.robot.setFireBullet(bulletPower);
	    this.robot.getStrategy().getMovementController().resume();
	    this.robot.getStrategy().getScanningController().resume();
	    return bullet;
	}
	
	class EnemyInfo{
		private long time;
		private double heading;
		
		EnemyInfo(long time, double heading){
			this.time = time;
			this.heading = heading;
		}

		public long getTime() {
			return time;
		}

		public void setTime(long time) {
			this.time = time;
		}

		public double getHeading() {
			return heading;
		}

		public void setHeading(double heading) {
			this.heading = heading;
		}
		
		
	}

}
