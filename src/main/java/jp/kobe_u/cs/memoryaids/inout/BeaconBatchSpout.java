package jp.kobe_u.cs.memoryaids.inout;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import backtype.storm.task.TopologyContext;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import storm.trident.operation.TridentCollector;
import storm.trident.spout.IBatchSpout;

public class BeaconBatchSpout implements IBatchSpout {

	private JedisPool pool;
	private LinkedBlockingQueue<Beacon> beaconQueue;
	private Jedis jedis;
	
	
	@Override
	public void open(Map arg0, TopologyContext arg1) {
		// TODO Auto-generated method stub
		 pool = new JedisPool(new JedisPoolConfig(),"192.168.100.106");
		 if(jedis == null){
			 jedis = pool.getResource();
		 }
		 
	}
	
	@Override
	public void ack(long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void emitBatch(long arg0, TridentCollector collector) {
		// TODO Auto-generated method stub
		final String beacon = jedis.rpop("beacon");
		if (beacon == null){
			Utils.sleep(50);
		}else{
			collector.emit(new Values(beacon));
		}
	}

	
	//Get configuration
	// future work:kokoni => userniyoru parameter wo yomikomu 

	@Override
	public Map getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	
	@Override
	public Fields getOutputFields() {
		// TODO Auto-generated method stub
		return new Fields("tweet");
	}

}
