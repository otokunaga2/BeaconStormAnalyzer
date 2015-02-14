package jp.kobe_u.cs.memoryaids.inout;

import backtype.storm.tuple.Values;

import com.google.gson.Gson;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

public class FirstConvertFunction extends BaseFunction {

	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		// TODO Auto-generated method stub
		final String beacon = tuple.getString(0);
		
		Gson gsonSerializer = new Gson();
		
		Beacon convetedBeacon = gsonSerializer.fromJson(beacon,Beacon.class);
		System.out.println(convetedBeacon.getDate());
		String jsonBeacon = gsonSerializer.toJson(beacon);
		

		
		collector.emit(new Values(convetedBeacon.getRid()));
		
	}

}
