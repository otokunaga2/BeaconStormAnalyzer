package com.microsoft.example;

import java.util.Map;

import jp.kobe_u.cs.memoryaids.trendanalysis.Beacon;

import com.google.gson.Gson;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class SplitBeaconBolt extends BaseRichBolt {
	 private OutputCollector collector;
	@Override
	public void execute(Tuple tuple) {
		// TODO Auto-generated method stub
		final String beaconValue = tuple.getString(0);
		Gson gsonSerializer = new Gson();
		
		Beacon convetedBeacon = gsonSerializer.fromJson(beaconValue,Beacon.class);
		
		collector.emit(new Values(convetedBeacon.getAccuracy(),convetedBeacon.getRid()));
	}

	@Override
	public void prepare(Map arg0, TopologyContext context, OutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("accuracy","rid"));
	}

}
