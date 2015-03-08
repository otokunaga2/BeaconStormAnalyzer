package jp.kobe_u.cs.memoryaids.trendanalysis;

import java.util.ArrayList;
import java.util.List;

import backtype.storm.tuple.Values;
import storm.trident.operation.TridentCollector;
import storm.trident.state.BaseQueryFunction;
import storm.trident.tuple.TridentTuple;

public class QueryBeacon extends BaseQueryFunction<BeaconDB, String> {

	@Override
	public List<String> batchRetrieve(BeaconDB state, List<TridentTuple> inputs) {
		// TODO Auto-generated method stub
		List<String> ret = new ArrayList<String>();
		for(TridentTuple input: inputs){
			ret.add(state.getBeaconValue(input.getStringByField("accuracy")));
		}
		return state.bulkGetAccuracy(ret);
	}

	@Override
	public void execute(TridentTuple arg0, String accuracy, TridentCollector collector) {
		// TODO Auto-generated method stub
		collector.emit(new Values(accuracy));
	}

}
