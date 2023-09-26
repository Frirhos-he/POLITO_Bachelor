package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends ElementExt {

	private boolean open;
	
	public Tap(String name) {
		super(name);
	}
	
	public void setOpen(boolean open){
		this.open = open;
	}
	
	@Override
	void simulate(double inFlow, SimulationObserver observer, boolean enableMaxFlowChecks) {
		//System.out.println("Source " + getName() + ": flow=" + flow);
		double outFlow = (open?inFlow:0);
//		System.out.println("Tap " + getName() + ": in flow=" + inFlow);
//		System.out.println(" out flow=" + outFlow);

		observer.notifyFlow("Tap",getName(),inFlow, outFlow);
		if(observer instanceof SimulationObserverExt && enableMaxFlowChecks && inFlow>maxFlow)
			((SimulationObserverExt)observer).notifyFlowError(this.getClass().getName(), getName(), inFlow, maxFlow);
		getOutput().simulate(outFlow,observer,enableMaxFlowChecks);
	}
	
	@Override
	StringBuffer layout(String pad) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("]Tap -> ");
		res.append( getOutput().layout( pad + blanks(res.length()) ) );
		return res;
	}

	@Override
	void simulate(double inFlow, SimulationObserver observer) {
		simulate(inFlow, observer,false);
	}
}
