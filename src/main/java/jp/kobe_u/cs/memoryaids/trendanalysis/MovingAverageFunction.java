package jp.kobe_u.cs.memoryaids.trendanalysis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.kobe_u.cs.memoryaids.trendanalysis.EWMA.Time;
import backtype.storm.tuple.Values;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

public class MovingAverageFunction extends BaseFunction {
	private EWMA ewma;
	private jp.kobe_u.cs.memoryaids.trendanalysis.EWMA.Time emtRatePer;
	
	public MovingAverageFunction(EWMA ewma, Time emitRatePer){
		this.ewma = ewma; 
		this.emtRatePer = emitRatePer;
		
	}
	
	@Override
	public void execute(TridentTuple tuple, TridentCollector collector) {
		// TODO Auto-generated method stub
		final String strDate = tuple.getString(0);
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		try {
			date = sf.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			date = DateFormat.getInstance().parse(strDate);
		
		this.ewma.mark(date.getTime());
		
		collector.emit(new Values(this.ewma.getAverageRatePer(this.emtRatePer)));
	}

}
