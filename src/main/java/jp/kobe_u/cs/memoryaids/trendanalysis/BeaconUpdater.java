package jp.kobe_u.cs.memoryaids.trendanalysis;

import java.util.ArrayList;
import java.util.List;

import backtype.storm.tuple.Values;
import storm.trident.operation.TridentCollector;
import storm.trident.state.BaseStateUpdater;
import storm.trident.tuple.TridentTuple;

public class BeaconUpdater extends BaseStateUpdater<BeaconDB> {
	
	@Override
	public void updateState(BeaconDB state, List<TridentTuple> tuples,
			TridentCollector collector) {
		List<String> ids = new ArrayList<String>();
		List<String> accuracyList = new ArrayList<String>();
		for(TridentTuple t: tuples){
			ids.add(t.getStringByField("rid"));
			accuracyList.add(t.getStringByField("accuracy"));
		}
		state.setBeaconValuesBulk(ids, accuracyList);
		collector.emit(new Values("hoge"));
	}

}
