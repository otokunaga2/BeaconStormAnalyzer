package jp.kobe_u.cs.memoryaids.trendanalysis;



import org.json.simple.JSONArray;

import com.google.common.collect.ImmutableBiMap.Builder;
import com.google.gson.Gson;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;
import backtype.storm.tuple.Values;


public class HashtagExtractor extends BaseFunction {
  //this class execute the specify the require value for analyze 
  @Override
  public void execute(TridentTuple tuple, TridentCollector collector) {
    //Get the beaconlog
	//
	final String beacon = (String)tuple.get(0);
	
	
	Gson gsonSerializer = new Gson();
	
	Beacon convetedBeacon = gsonSerializer.fromJson(beacon,Beacon.class);
	System.out.println(convetedBeacon.getAccuracy());
	
	collector.emit(new Values(convetedBeacon.getAccuracy()));
	
//    final Status status = (Status) tuple.get(0);
    //Loop through the hashtags
//    for (HashtagEntity hashtag : status.getHashtagEntities()) {
//      //Emit each hashtag
//      collector.emit(new Values(hashtag.getText()));
//    }
  }
}
