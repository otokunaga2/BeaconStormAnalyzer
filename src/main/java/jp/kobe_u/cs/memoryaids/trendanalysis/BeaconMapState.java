package jp.kobe_u.cs.memoryaids.trendanalysis;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import backtype.storm.task.IMetricsContext;
import storm.trident.state.State;
import storm.trident.state.StateFactory;
import storm.trident.state.map.IBackingMap;
import storm.trident.testing.MemoryMapState;

public class BeaconMapState implements IBackingMap<Beacon> {

	@Override
	public List<Beacon> multiGet(List<List<Object>> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void multiPut(List<List<Object>> arg0, List<Beacon> arg1) {
		// TODO Auto-generated method stub
		
	}
	public static class Factory implements StateFactory{
		String _id;
		public Factory(){
			_id = UUID.randomUUID().toString();
		}

		@Override
		public State makeState(Map arg0, IMetricsContext arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
