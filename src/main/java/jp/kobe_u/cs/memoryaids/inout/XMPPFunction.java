package jp.kobe_u.cs.memoryaids.inout;



import java.util.Map;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.operation.TridentOperationContext;
import storm.trident.tuple.TridentTuple;

public class XMPPFunction extends BaseFunction {

	@Override
	public void prepare(Map conf, TridentOperationContext context){
		System.out.println("XMPP function is prepared prepare preapare prepare");
	}
	@Override
	public void execute(TridentTuple arg0, TridentCollector arg1) {
		// TODO Auto-generated method stub
		System.out.println("XMPP function is executed -----------------------------");
	}

}
