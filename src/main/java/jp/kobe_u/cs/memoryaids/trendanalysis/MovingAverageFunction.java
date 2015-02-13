package jp.kobe_u.cs.memoryaids.trendanalysis;

import jp.kobe_u.cs.memoryaids.trendanalysis.EWMA.Time;
import backtype.storm.tuple.Values;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

public class MovingAverageFunction extends BaseFunction {
	private EWMA ewma;
	private jp.kobe_u.cs.memoryaids.trendanalysis.EWMA.Time emtRatePer;
	
	public MovingAverageFunction(EWMA ewma, Time emitRatePer){
		this.ewma = ewma; 
		this.emtRatePer = emitRatePer;
		
	}
	
	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		// TODO Auto-generated method stub
		this.ewma.mark(tuple.getLong(0));
		
		collector.emit(new Values(this.ewma.getAverageRatePer(this.emtRatePer)));
	}

}
