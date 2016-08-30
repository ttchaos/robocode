package Orange.Team2;
import java.awt.Color;

import Orange.Team2.YimingZhouRobot.*;
import Orange.Team2.YimingZhouRobot.strategy.*;
import robocode.*;


// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * YimingZhou - a robot by Yiming Zhou
 */
public class YimingZhou extends AbstractModularRobot
{
	
	@Override
	public void run() {

		setColors(Color.lightGray,Color.lightGray,Color.blue); // body,gun,radar
		
		System.out.println("Number of Other Robots on the field: " + this.getOthers());
		System.out.println("Number of Teammates: " + this.getNumTeammates());
		System.out.println("Number of Enemies: " + (this.getOthers()-this.getNumTeammates()));
		
		int numEnemies = this.getOthers()-this.getNumTeammates();
		this.setStrategy(chooseStrategy(numEnemies));
		
		while(true){
			//choose new strategy if battlefield condition changed
			if(numEnemies!=(this.getOthers()-getNumTeammates())){
				numEnemies = this.getOthers()-getNumTeammates();
				this.setStrategy(chooseStrategy(numEnemies));
			}
			
			//execute strategy
			this.getStrategy().execute();
		}
	}
	
	//Choose strategy based on the number of enemies on the field
	private IStrategyController chooseStrategy(int numEnemies){
		if(numEnemies > 0){ //change to 1 when TrackAndRam is ready
			if(this.getStrategy() instanceof RandomWalkStrategy){
				return this.getStrategy();
			} 
			
			System.out.println("Switching strategy to RandomWalk");
			return new RandomWalkStrategy(this);
		}
		else{
			if(this.getStrategy() instanceof TrackAndRamStrategy){
				return this.getStrategy();
			} 
			
			System.out.println("Switching strategy to TrackAndRam");
			return new TrackAndRamStrategy(this);
		}
	}
	
	private int getNumTeammates(){
		return ((this.getTeammates() == null) ? 0 : this.getTeammates().length);
	}
	

}
					
