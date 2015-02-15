package jp.kobe_u.cs.memoryaids.trendanalysis;

import backtype.storm.tuple.Values;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

public class CalcAverage extends BaseFunction {

	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		// TODO Auto-generated method stub
		Number sum= (Number)tuple.getValueByField("sum");
		Number count= (Number)tuple.getValueByField("count");
//		Double dSum = Double.valueOf(sum);
//		Double dNum = Double.valueOf(num);
		
		System.out.println("average---------------"+count);
		if(1 != 0){
			collector.emit(new Values(sum));
		}
		
		
	}

}
