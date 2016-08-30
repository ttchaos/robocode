package Orange.Team2.YimingZhouRobot;

import Orange.Team2.YimingZhouRobot.strategy.IStrategyController;
import robocode.*;

public abstract class AbstractModularRobot extends TeamRobot {
	private IStrategyController strategy;

	public IStrategyController getStrategy() {
		return strategy;
	}

	public void setStrategy(IStrategyController strategy) {
		this.strategy = strategy;
	}
	
	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		this.strategy.onScannedRobot(e);
	}

	@Override
	public void onHitByBullet(HitByBulletEvent e) {
		this.strategy.onHitByBullet(e);
	}
	
	@Override
	public void onHitWall(HitWallEvent e) {
		this.strategy.onHitWall(e);
	}
	
	@Override
	public void onHitRobot(HitRobotEvent e) {
		this.strategy.onHitRobot(e);
	}
}
