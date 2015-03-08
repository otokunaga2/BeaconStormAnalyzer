package jp.kobe_u.cs.memoryaids.trendanalysis;

import java.util.Map;

import backtype.storm.task.IMetricsContext;
import backtype.storm.tuple.Values;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.state.State;
import storm.trident.state.StateFactory;
import storm.trident.tuple.TridentTuple;

public class PipeFunction extends BaseFunction implements StateFactory {

	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		// TODO Auto-generated method stub
		String rid = tuple.getStringByField("rid");
		String accuracy = tuple.getStringByField("accuracy");
		collector.emit(new Values(rid,accuracy));
	}

	@Override
	public State makeState(Map conf, IMetricsContext metrics,
			int partitionIndex, int numPartitions) {
		// TODO Auto-generated method stub
		return new BeaconDB();
	}

}
