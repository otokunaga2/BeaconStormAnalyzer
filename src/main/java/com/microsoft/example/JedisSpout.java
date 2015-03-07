package com.microsoft.example;

import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class JedisSpout extends BaseRichSpout {
	
	SpoutOutputCollector _collector;
	private String host;
	private int port;
	private String pattern;
	LinkedBlockingQueue<String> queue;
	JedisPool pool;
	
	
	public JedisSpout(String host, int port, String pattern){
		this.host = host;
		this.port = port;
		this.pattern = pattern;
		
	}
	@Override
	public void nextTuple() {
		// TODO Auto-generated method stub
		Jedis jedis = pool.getResource();
		queue.add(jedis.rpop("beacon"));
		String ret = queue.poll();
		if(ret == null){
			Utils.sleep(50);
		}else{
			_collector.emit(new Values(ret));
		}
	}

	@Override
	public void open(Map map, TopologyContext context, SpoutOutputCollector collector) {
		// TODO Auto-generated method stub
		_collector = collector;
		queue = new LinkedBlockingQueue<String>(1000);
		pool = new JedisPool(new JedisPoolConfig(),host,port);
	}
	public void close(){
		pool.destroy();
	}
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declare) {
		// TODO Auto-generated method stub
		declare.declare(new Fields("word"));
	}

}
