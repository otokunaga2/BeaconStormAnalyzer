package jp.kobe_u.cs.memoryaids.trendanalysis;

import backtype.storm.tuple.Values;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;



public class ThreasholdFilterFunction extends BaseFunction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static enum State{
		BELOW, ABOVE
	}
	private State last = State.BELOW;
	private double threshold;
	
	public  ThreasholdFilterFunction(double threshold){
		this.threshold = threshold;
	}
	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		double val = tuple.getDouble(0);
		State newState = val < this.threshold ? State.BELOW : State.ABOVE;
		boolean stateChange = this.last != newState;
		collector.emit(new Values(stateChange, threshold));
		
		this.last = newState;
		
		// TODO Auto-generated metshod stub
		
	}

}
