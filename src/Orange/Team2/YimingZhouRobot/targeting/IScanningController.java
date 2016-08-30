package Orange.Team2.YimingZhouRobot.targeting;

import robocode.*;

public interface IScanningController {

	public void execute();
	
	public void stop();
	
	public void resume();

	public void onScannedRobot(ScannedRobotEvent e);
	
	public void adjustGun(double degree);
	
	public void adjustRadar(double degree);
	
}
