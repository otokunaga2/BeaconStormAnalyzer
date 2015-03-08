package jp.kobe_u.cs.memoryaids.trendanalysis;

import java.util.List;

import storm.trident.state.State;


public class BeaconDB implements State {
	private String accuracy;
	@Override
	public void beginCommit(Long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commit(Long arg0) {
		// TODO Auto-generated method stub
		
	}
	public void setBeaconValuesBulk(List<String> rid, List<String> accuracy){
		
	}
	
	public List<String> bulkGetAccuracy(List<String> rid){
		
		return null;
	}
	
	public void setBeaconValue(String rid,String accuracy){
		
	}
	public String getBeaconValue(String rid){
		return this.accuracy;
	}

}
