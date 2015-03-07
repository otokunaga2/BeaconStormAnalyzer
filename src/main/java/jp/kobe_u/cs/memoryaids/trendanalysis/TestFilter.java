package jp.kobe_u.cs.memoryaids.trendanalysis;

import java.util.Map;

import storm.trident.operation.Filter;
import storm.trident.operation.TridentOperationContext;
import storm.trident.tuple.TridentTuple;

public class TestFilter implements Filter {

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void prepare(Map arg0, TridentOperationContext arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isKeep(TridentTuple arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
