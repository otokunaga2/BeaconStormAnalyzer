package jp.kobe_u.cs.memoryaids.trendanalysis;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Time;
import storm.trident.Stream;
import storm.trident.TridentTopology;
import storm.trident.operation.builtin.Count;
import storm.trident.operation.builtin.Debug;
import storm.trident.operation.builtin.FirstN;
import storm.trident.spout.IBatchSpout;
import storm.trident.testing.MemoryMapState;
import jp.kobe_u.cs.memoryaids.trendanalysis.EWMA;
public class BeaconTopology {

	public static StormTopology buildTopology(IBatchSpout spout) throws IOException{
		final TridentTopology topology = new TridentTopology();
		//IBatchspout//
//		Fields jsonFields = new Fields("accuracy","date","rid");
//		
		Stream parsedStream = topology.newStream("tweet", spout);
//		
		Stream secondPraseStream = parsedStream.each(new Fields("tweet"),new JsonProjectFunction(),new Fields("accuracy"));
//		Stream ridStream = secondPraseStream.groupBy(new Fields("rid"))
////		ridStream.each(inputFields, filter)
//		.persistentAggregate(new MemoryMapState.Factory(), new Count(), new Fields("count"))
//		.newValuesStream()
//		.applyAssembly(new FirstN(10, "count"))
//	    .each(new Fields("rid", "count"), new Debug());
//		
		
		
		
		//drop the required fields
//		parsedStream = parsedStream.project(jsonFields);
		EWMA ewma = new EWMA().sliding(1.0,jp.kobe_u.cs.memoryaids.trendanalysis.EWMA.Time.MINUTES).withAlpha(EWMA.ONE_MINUTE_ALPHA);
		Stream averageStream = secondPraseStream.each(new Fields("accuracy"),
				new MovingAverageFunction(ewma, jp.kobe_u.cs.memoryaids.trendanalysis.EWMA.Time.MINUTES), new Fields("average"));
		ThreasholdFilterFunction tff = new ThreasholdFilterFunction(1.5D);
		Stream thresholdStream = averageStream.each(new Fields("average"),tff,new Fields("change","threashold"));
		Stream filteredStream = thresholdStream.each(new Fields("change"), new BooleanFilter());
		
		
//		filteredStream.each(filteredStream.getOutputFields(),new XMPPFunction(), new Fields());
		
		
		
		return topology.build();
		//motomoto
//		topology.newStream("spout", spout)
//		.each( new Fields("tweet"), new HashtagExtractor(), new Fields("hashtag","rid"))
//		.groupBy(new Fields("rid"))
//		.persistentAggregate(new MemoryMapState.Factory(), new Count(), new Fields("count"))
//		.newValuesStream()
////		.applyAssembly(new FirstN(10,"count"))
//		.each(new Fields("rid","count"), new Debug());
//		return topology.build();
	}
	
	
	public static void main(String[] args) throws Exception{
		final Config conf = new Config();
		IBatchSpout spout = (IBatchSpout) new BeaconBatchSpout();
		if(args.length == 0){
			final LocalCluster local = new LocalCluster();
			try{
				local.submitTopology("log-analysis-topology", conf, buildTopology(spout));
			}catch(IOException e){
				throw new RuntimeException(e);
					
			}
			
		
		
		}else{
			conf.setNumWorkers(3);
			
			
			StormSubmitter.submitTopology(args[0], conf, buildTopology(spout));
			
		
		
		}
		
		
		
		
		
	}
}
