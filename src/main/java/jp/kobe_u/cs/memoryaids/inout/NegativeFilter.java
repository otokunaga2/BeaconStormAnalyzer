package jp.kobe_u.cs.memoryaids.inout;

import storm.trident.operation.BaseFilter;
import storm.trident.tuple.TridentTuple;

public class NegativeFilter extends BaseFilter {
	
	@Override
	public boolean isKeep(TridentTuple tuple) {
		// TODO Auto-generated method stub
		
		
//		for(Beacon b: tuple){
//			if ( Double.parseDouble(b.getAccuracy()) < 0){
//				
//			}
//		}
		return false;
	}

}
