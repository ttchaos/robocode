package Orange.Team2.YimingZhouRobot.targeting;

import Orange.Team2.YimingZhouRobot.*;
import robocode.*;
import robocode.util.Utils;

public class OscillaingScanningController implements IScanningController {

	private static final int ARC = 180;
	private AbstractModularRobot robot;
	private IFiringController firingController;
	private ScanningState state;
	private int direction = 0;
	
	
	public OscillaingScanningController(AbstractModularRobot robot, IFiringController firingController) {
		this.robot = robot;
		this.firingController = firingController;
		this.state = new ScanningState();
		this.robot.setTurnGunRight(direction*(ARC%2));
	}

	@Override
	public void execute() {
		if(this.direction == 0){
			this.direction = (Utils.getRandom().nextInt(2) > 0) ? 1 : -1;
			this.robot.turnGunRight(this.direction*ARC/2);
		}
		if(this.robot.getGunTurnRemaining() == 0){
			this.direction = -1*this.direction;
			this.robot.setTurnGunRight(this.direction*ARC);
		}
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		if(!this.robot.isTeammate(e.getName())){
			this.firingController.fire(e);
		}
	}

	@Override
	public void adjustGun(double degree) {
		if(this.state.isStopped()){
			this.state.setGunTurninng(this.state.getGunTurninng()+degree);
		}
		else{
			this.robot.turnGunRight(this.robot.getGunTurnRemaining()+degree);
		}
	}

	@Override
	public void stop() {
		if(this.state.isStopped()) return;
		this.state.stop();
		this.state.setGunTurninng(this.robot.getGunTurnRemaining());
		this.state.setRadarTurning(this.robot.getRadarTurnRemaining());
		this.robot.setTurnGunRight(0);
		this.robot.setTurnRadarRight(0);
	}

	@Override
	public void resume() {
		if(!this.state.isStopped()) return;
		this.state.resume();
		this.robot.setTurnGunRight(this.state.getGunTurninng());
		this.robot.setTurnRadarRight(this.state.getRadarTurning());
	}

	@Override
	public void adjustRadar(double degree) {
		// TODO Auto-generated method stub
		
	}

}
