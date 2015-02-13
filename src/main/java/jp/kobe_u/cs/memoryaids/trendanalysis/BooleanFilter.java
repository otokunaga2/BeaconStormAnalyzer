package jp.kobe_u.cs.memoryaids.trendanalysis;

import storm.trident.operation.BaseFilter;
import storm.trident.tuple.TridentTuple;

public class BooleanFilter extends BaseFilter {
	//this filter keeps the true/false
	@Override
	public boolean isKeep(TridentTuple tuple) {
		// TODO Auto-generated method stub
		return tuple.getBoolean(0);
	}

}
