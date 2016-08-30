package Orange.Team2.YimingZhouRobot.targeting;

import java.util.HashMap;
import java.util.Map;

import Orange.Team2.YimingZhouRobot.*;
import robocode.*;

//Smart Firing Controller that chooses aiming strategy based on past hit/miss statistics 
public class learningFiringController implements IFiringController {

	private AbstractModularRobot robot;
	private IAimingController[] aimingControllers;
	private Map<String,int[][]> stats; //hit and miss stats for each enemy and aiming strategy
	private Map<Bullet,BulletInfo> bulletMap; //hit and miss stats for each enemy and aiming strategy
	
	public learningFiringController(AbstractModularRobot robot) {
		this.robot = robot;
		this.aimingControllers = new IAimingController[]{new linearAimingController(this.robot), new spotAimingController(this.robot)};
		this.stats = new HashMap<String,int[][]>();
		this.bulletMap = new HashMap<Bullet,BulletInfo>();
	}

	@Override
	public void fire(ScannedRobotEvent e) {
		//add new enemy to stats tracking map
		if(!this.stats.containsKey(e.getName())){
			int[][] initStats = new int[this.aimingControllers.length][2];
			for(int i=0;i<this.aimingControllers.length;i++){
				initStats[i] = new int[]{1,1};
			}
			this.stats.put(e.getName(), initStats);
		}
		
		if(this.robot.getGunHeat()==0){
			int aimingControllerIndex = this.getAimingController(e.getName());
			Bullet bullet = this.aimingControllers[aimingControllerIndex].fire(e);
			if(bullet!=null){
				this.bulletMap.put(bullet, new BulletInfo(e.getName(),aimingControllerIndex));	
			}
		}	
	}
	
	private int getAimingController(String enemyName){
		int[][] enemyStats = this.stats.get(enemyName);
		double[] probs = new double[enemyStats.length];
		double totalProbs = 0;
		for(int i=0;i<enemyStats.length;i++){
			probs[i] = enemyStats[i][0]/(enemyStats[i][0]+enemyStats[i][0]);		
			totalProbs += probs[i];
		}
		
		double random = Math.random() * totalProbs;
		for (int i = 0; i < probs.length; ++i)
		{
		    random -= probs[i];
		    if (random <= 0.0d)
		    {
		    	return i;
		    }
		}
		
		return 0;
	}
	
	class BulletInfo {
		private String enemy;
		private int aimingStrategyIndex;
		
		BulletInfo(String enemy, int aimingStrategyIndex){
			this.enemy = enemy;
			this.aimingStrategyIndex = aimingStrategyIndex;
		}

		public int getAimingStrategyIndex() {
			return aimingStrategyIndex;
		}

		public String getEnemy() {
			return enemy;
		}
	}

}
