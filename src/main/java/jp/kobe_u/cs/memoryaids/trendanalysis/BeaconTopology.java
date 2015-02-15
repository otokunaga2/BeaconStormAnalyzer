package jp.kobe_u.cs.memoryaids.trendanalysis;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.LocalDRPC;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Time;
import storm.trident.Stream;
import storm.trident.TridentState;
import storm.trident.TridentTopology;
import storm.trident.fluent.GroupedStream;
import storm.trident.operation.builtin.Count;
import storm.trident.operation.builtin.Debug;
import storm.trident.operation.builtin.FilterNull;
import storm.trident.operation.builtin.FirstN;
import storm.trident.operation.builtin.MapGet;
import storm.trident.operation.builtin.Sum;
import storm.trident.spout.IBatchSpout;
import storm.trident.testing.FixedBatchSpout;
import storm.trident.testing.MemoryMapState;
import storm.trident.testing.Split;
import jp.kobe_u.cs.memoryaids.trendanalysis.EWMA;
public class BeaconTopology {

	@SuppressWarnings("unchecked")
	public static StormTopology buildTopology(IBatchSpout spout) throws IOException{
		final TridentTopology topology = new TridentTopology();
		//IBatchspout//
//		Fields jsonFields = new Fields("accuracy","date","rid");
//		


		Stream parsedStream = topology
				.newStream("spout1", spout)
				.parallelismHint(16)
				.each(new Fields("sentence"),new FirstConvertFunction(), new Fields("rid","accuracy"))
				.groupBy(new Fields("rid"))
				.persistentAggregate(new MemoryMapState.Factory(), new Sum(), new Fields("count")).newValuesStream().each(new Fields("count"), new Debug());
//		TridentTopology topology = new TridentTopology();
		
		return topology.build();
		
//		spout.setCycle(true);
//		TridentState parsedStream = topology.newStream("sentence", spout)
//		.each(new Fields("sentence"), new DataPreparation(),new Fields("rid"))
//		.groupBy(new Fields("rid"))
//		.persistentAggregate(new MemoryMapState.Factory(), new Count(), new Fields("count"));
//		topology.newStream().
//		topology.newDRPCStream("words",drpc)
//				.each(new Fields("args"), new Split(), new Fields("word"))
//				.groupBy(new Fields("word"))
//				.stateQuery(parsedStream, new Fields("word"), new MapGet(),new Fields("count"))
//				.each(new Fields("count"), new FilterNull())
//				.aggregate(new Fields("count"), new Sum(), new Fields("sum"));
//		return topology.build();
////		.per
//		Stream averageStream = parsedStream.each(new Fields("tweet"),new DataPreparation(),new Fields("rid","accuracy"));
//		Stream ridStream = secondPraseStream.groupBy(new Fields("rid"))
////		ridStream.each(inputFields, filter)
//		.persistentAggregate(new MemoryMapState.Factory(), new Count(), new Fields("count"))
//		.newValuesStream()
//		.applyAssembly(new FirstN(10, "count"))
//	    .each(new Fields("rid", "count"), new Debug());
//		
		
		
		
		//drop the required fields
//		parsedStream = parsedStream.project(jsonFields);
//		EWMA ewma = new EWMA().sliding(1.0,jp.kobe_u.cs.memoryaids.trendanalysis.EWMA.Time.MINUTES).withAlpha(EWMA.ONE_MINUTE_ALPHA);
//		Stream averageStream = secondPraseStream.each(new Fields("accuracy"),
//				new FirstConvertFunction(), new Fields("rid","accuracy"));
		
		
//		.each(new Fields("rid","accuracy"),tff,new Fields("change","threashold"))
		
		
//		Stream judgeStream = averageStream.groupBy(new Fields("rid")).chainedAgg()
//				.partitionAggregate(new Fields("accuracy"), new Sum(), new Fields("sum"))
//				.partitionAggregate(new Count(),new Fields("count"))averageStream;
//		judgeStream.each(new Fields("count","sum"), new CalcAverage(),new Fields("result"));
//		judgeStream.each(new Fields("sum"), new CalcAverage(), new Fields("hogehoge"));
//		.partitionAggregate(new Fields("accuracy"), new Count(), new Fields("count"))
//		.partitionAggregate(new Fields("accuracy"), new Sum(), new Fields("sum")).chainEnd();
//		stream.each(,new CalcAverage(),);
//		stream.each(inputFields, function, functionFields)
//		stream.each(inputFields, function, functionFields)
							
		//		Stream filteredStream = thresholdStream.each(new Fields("change"), new BooleanFilter());
		
		
//		filteredStream.each(filteredStream.getOutputFields(),new XMPPFunction(), new Fields());
		
		
		
		
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
		conf.setMaxSpoutPending(20);
		IBatchSpout spout = (IBatchSpout) new BeaconBatchSpout();
		if(args.length == 0){
			final LocalCluster local = new LocalCluster();
			LocalDRPC drpc = new LocalDRPC();
			try{
				local.submitTopology("log-analysis-topology", conf, buildTopology(spout));
				for(int i=0;i<100;i++)
				{
					System.out.println("DRPC result"+drpc.execute("words", "cat the dog jumped"));
					Thread.sleep(1000);
				}
			}catch(IOException e){
				throw new RuntimeException(e);
					
			}
			
		
		
		}else{
			conf.setNumWorkers(3);
			
			
//			StormSubmitter.submitTopology(args[0], conf, buildTopology(spout));

		
		}
		
	}
}
