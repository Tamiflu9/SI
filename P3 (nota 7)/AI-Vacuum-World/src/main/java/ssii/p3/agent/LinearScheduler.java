package ssii.p3.agent;

import aima.core.search.local.Scheduler;

public class LinearScheduler extends Scheduler {
	private int limit; // steps to cool down
	
	public LinearScheduler(int limit) {
		this.limit = limit;
	}
	
	/**
	 * During a SimulatedAnnealingSearch, this method will be called on 
	 * each iteration starting with t = 0 and incrementing +1 on each 
	 * new iteration
	 * */
	@Override
	public double getTemp(int t) {
		if (t < this.limit) {    				
			return limit-t;
		} else {
			return 0.0;
		}
	}  
}
