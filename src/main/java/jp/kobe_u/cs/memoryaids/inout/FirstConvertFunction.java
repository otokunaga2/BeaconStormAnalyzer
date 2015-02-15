package jp.kobe_u.cs.memoryaids.inout;

import java.util.Map;

import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.google.gson.Gson;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

public class FirstConvertFunction extends BaseFunction {
	private static final int NUM_WINDOW_CHUNKS = 5;
	private static final int DEFAULT_SLIDING_WINDOW_IN_SECONDS = NUM_WINDOW_CHUNKS * 60;
	private static final int DEFAULT_EMIT_FREQUENCY_IN_SECONDS = DEFAULT_SLIDING_WINDOW_IN_SECONDS/NUM_WINDOW_CHUNKS;
	private static final String WINDOW_LENGTH_WARNING_TEMPLATE = 
			"Actual awindow legnth is %d seconds when it should be %d seconds" + "(you can sefely ignore this warinign during the statup phase)";
	
	
	private SlidingWindowCounter<Object> counter;
	private int windwoLengthInSeconds;
	private int emitFrequencyInSeconds;
	private NthLastModifiedTimeTracker lastModifiedTracker;
	
	public FirstConvertFunction(){
		this(DEFAULT_SLIDING_WINDOW_IN_SECONDS,DEFAULT_EMIT_FREQUENCY_IN_SECONDS);
	}
	
	public FirstConvertFunction(int windowLenghtInSeconds, int emitFrequencyInSeconds) {
		
		this.windwoLengthInSeconds = windowLenghtInSeconds;
		this.emitFrequencyInSeconds = emitFrequencyInSeconds;
		
		counter = new SlidingWindowCounter<Object>(deriveNumWindowChuncksFrom(this.windwoLengthInSeconds, this.emitFrequencyInSeconds));
		// TODO Auto-generated constructor stub
		counter = new SlidingWindowCounter<Object>(60);
		
	}
	private int deriveNumWindowChuncksFrom(int windowLengthInSeconds, int windowUpdateFrequnecyInSeconds){
		return windowLengthInSeconds/windowUpdateFrequnecyInSeconds;
	}
	
	private void emitCurrentWindowCounts(TridentCollector collector){
		Map<Object,Long> counts = counter.getCountsThenAdvanceWindow();
		int actualWindwoLegnthInSeconds = lastModifiedTracker.secondsSinceOldestModification();
		lastModifiedTracker.markAsModified();
		if(actualWindwoLegnthInSeconds != windwoLengthInSeconds){
			System.out.println(WINDOW_LENGTH_WARNING_TEMPLATE);
		}
		
	}
	
	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		// TODO Auto-generated method stub
		final String beacon = tuple.getString(0);
		
		Gson gsonSerializer = new Gson();
		
		Beacon convetedBeacon = gsonSerializer.fromJson(beacon,Beacon.class);
		
		String jsonBeacon = gsonSerializer.toJson(beacon);
		System.out.println(convetedBeacon.getAccuracy());
		
		collector.emit(new Values(convetedBeacon.getAccuracy(),convetedBeacon.getRid()));
		
	}
	
	private void countObjAndAck(Tuple tuple){
		Object obj = tuple.getValue(0);
		counter.incrementCount(obj);
		
	}

}
