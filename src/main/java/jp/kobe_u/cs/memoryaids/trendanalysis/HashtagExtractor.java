package jp.kobe_u.cs.memoryaids.trendanalysis;

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
	System.out.println(beacon.toString());
	collector.emit(new Values(beacon));
	
//    final Status status = (Status) tuple.get(0);
    //Loop through the hashtags
//    for (HashtagEntity hashtag : status.getHashtagEntities()) {
//      //Emit each hashtag
//      collector.emit(new Values(hashtag.getText()));
//    }
  }
}
