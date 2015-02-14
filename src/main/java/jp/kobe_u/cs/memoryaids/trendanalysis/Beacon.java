package jp.kobe_u.cs.memoryaids.trendanalysis;

public class Beacon {

	private String date;
	private String major;
	private String rssi;
	private String uuid;
	private String minor;
	private String proximity;
	private String accuracy;
	private String rid;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getRssi() {
		return rssi;
	}
	public void setRssi(String rssi) {
		this.rssi = rssi;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getMinor() {
		return minor;
	}
	public void setMinor(String minor) {
		this.minor = minor;
	}
	public String getProximity() {
		return proximity;
	}
	public void setProximity(String proximity) {
		this.proximity = proximity;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	public String toString(){
		return null;
	}
//	{"date":"2015-03-05 19:45:36","major":1,"rssi":-90,"uuid":"DFE7A87B-F80B-1801-BF45-001C4D79EA56","minor":1,"proximity":"Far","accuracy":4.412027042116809,"rid":2}
//	DEBUG: [{"date":"2015-03-05 19:45:36","major":1,"rssi":-90,"uuid":"DFE7A87B-F80B-1801-BF45-001C4D79EA56","minor":1,"proximity":"Far","accuracy":4.412027042116809,"rid":2}, 2]

}
