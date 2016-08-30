package Orange.Team2.YimingZhouRobot.targeting;

public class ScanningState {
	private boolean stopped;
	private double gunTurninng;
	private double radarTurning;

	public boolean isStopped() {
		return stopped;
	}

	public void stop() {
		this.stopped = true;
	}
	
	public void resume() {
		this.stopped = false;
	}

	public double getGunTurninng() {
		return gunTurninng;
	}

	public void setGunTurninng(double gunTurninng) {
		this.gunTurninng = gunTurninng;
	}

	public double getRadarTurning() {
		return radarTurning;
	}

	public void setRadarTurning(double radarTurning) {
		this.radarTurning = radarTurning;
	}
	
}
