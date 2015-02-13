package jp.kobe_u.cs.memoryaids.trendanalysis;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import storm.trident.*;
import storm.trident.operation.TridentCollector;
import storm.trident.spout.IBatchSpout;
import storm.trident.spout.ITridentSpout;


public class BeaconSpout implements IRichSpout{

	/**
	 * 
	 */
	private JedisPool pool;
	private LinkedBlockingQueue<Beacon> beaconQueue;
	private Jedis jedis;
	@Override
	public void ack(Object arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void fail(Object arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void nextTuple() {
		// TODO Auto-generated method stub
		String str = jedis.get("beacon");
		
	}
	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
		// TODO Auto-generated method stub
		 pool = new JedisPool(new JedisPoolConfig(),"192.168.100.106");
		 jedis = pool.getResource();
	}
	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Open this stream
	

	




}
