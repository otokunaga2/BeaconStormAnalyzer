package jp.kobe_u.cs.memoryaids.trendanalysis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONValue;

import com.google.gson.Gson;

import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

public class DataPreparation extends BaseFunction {
	private Fields fields;
	
	public DataPreparation(){
		
	}
	public DataPreparation(Fields fields){
		this.fields = fields;
	}
	
	
	
	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		// TODO Auto-generated method stub
		final String beacon = tuple.getString(0);
		
		Gson gsonSerializer = new Gson();
		
		Beacon convetedBeacon = gsonSerializer.fromJson(beacon,Beacon.class);
		
		String jsonBeacon = gsonSerializer.toJson(beacon);
		
		Values value = new Values();
//		Map<String,Long> tmpMap = (Map<String, Long>)JSONValue.parse(jsonBeacon);
//		Map<String,Long> inputMap = null;
		/*for(Map.Entry<String, Long> tmp: tmpMap.entrySet()){
			if(tmp.getKey() == "accuracy"){
				inputMap.put(tmp.getKey(), tmp.getValue());
			}
		}*/
//		HashMap<String,Object> tmpMap = new HashMap<String, Object>();
//		
//		SimpleDateFormat sdf = new SimpleDateFormat();
//		Date date = new Date();
//		try {
//			date = sdf.parse(convetedBeacon.getDate());
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		//		tmpMap.put(  convetedBeacon.getAccuracy());
//		Long tmpLong = Long.parseLong();
//		Double tmpNumber = Double.valueOf();
		Double tmpDouble = Double.valueOf(convetedBeacon.getAccuracy());
		if(convetedBeacon.getAccuracy().equals("-1")){
			// do nothing
			collector.emit(new Values(0));
		}else{
//			collector.emit(new Values(convetedBeacon.getRid(),tmpDouble));
			collector.emit(new Values(convetedBeacon.getRid()));
		}
		
		
		
		
		
	}

}
