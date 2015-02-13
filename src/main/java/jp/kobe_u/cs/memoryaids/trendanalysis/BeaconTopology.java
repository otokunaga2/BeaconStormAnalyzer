package jp.kobe_u.cs.memoryaids.trendanalysis;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;
import storm.trident.TridentTopology;
import storm.trident.operation.builtin.Count;
import storm.trident.operation.builtin.Debug;
import storm.trident.operation.builtin.FirstN;
import storm.trident.spout.IBatchSpout;
import storm.trident.testing.MemoryMapState;

public class BeaconTopology {

	public static StormTopology buildTopology(IBatchSpout spout) throws IOException{
		final TridentTopology topology = new TridentTopology();
		
		topology.newStream("spout", spout)
		.each( new Fields("tweet"), new HashtagExtractor(), new Fields("hashtag","rid"))
		.groupBy(new Fields("rid"))
		.persistentAggregate(new MemoryMapState.Factory(), new Count(), new Fields("count"))
		.newValuesStream()
		.applyAssembly(new FirstN(10,"count"))
		.each(new Fields("rid","count"), new Debug());
		return topology.build();
	}
	
	
	public static void main(String[] args) throws Exception{
		final Config conf = new Config();
		IBatchSpout spout = (IBatchSpout) new BeaconBatchSpout();
		if(args.length == 0){
			final LocalCluster local = new LocalCluster();
			try{
				local.submitTopology("hastag-count-topology", conf, buildTopology(spout));
			}catch(IOException e){
				throw new RuntimeException(e);
					
			}
			
		
		
		}else{
			conf.setNumWorkers(3);
			
			
			StormSubmitter.submitTopology(args[0], conf, buildTopology(spout));
			
		
		
		}
		
		
		
		
		
	}
}
