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
		NEAR,FAR,NONE
//		BELOW, ABOVE
	}
	
	private State last = State.NONE;
	private double threshold;
	
	public  ThreasholdFilterFunction(double threshold){
		this.threshold = threshold;
	}
	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		
		String strVal = tuple.getStringByField("accuracy");
		
		Double val = Double.parseDouble(strVal);
		State newState = val < this.threshold ? State.NEAR : State.FAR;
		boolean stateChange = (this.last != newState && newState == State.NEAR);//near daked detect
		
		System.out.println(stateChange+"------------------------------------------------AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		collector.emit(new Values(stateChange, threshold));
		
		this.last = newState;
		
		// TODO Auto-generated metshod stub
		
	}

}
