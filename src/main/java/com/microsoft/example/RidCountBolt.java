package com.microsoft.example;

import java.util.HashMap;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class RidCountBolt extends BaseRichBolt {
	private OutputCollector collector;
	private HashMap<String, Long> counts = null;
	@Override
	public void execute(Tuple tuple) {
		// TODO Auto-generated method stub
		String rid = tuple.getStringByField("rid");
		String accuracy = tuple.getStringByField("accuracy");
		Long count = this.counts.get(rid);
		if(count == null){
			count = 0L;
		}
		if(accuracy.equals("-1")){
			count++;
		}
		this.counts.put(rid, count);
		this.collector.emit(new Values(rid,count));
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
		this.counts = new HashMap<String, Long>();
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub

	}

}
