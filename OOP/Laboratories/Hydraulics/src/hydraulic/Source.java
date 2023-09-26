package hydraulic;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * Lo status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends ElementExt {

	private double flow;
	
	public Source(String name) {
		super(name);
	}

	public void setFlow(double flow){
		this.flow = flow;
		this.maxFlow = flow;
	}

	@Override
	void simulate(double inFlow, SimulationObserver observer, boolean enableMaxFlowChecks) {
		//System.out.println("Source " + getName() + ": flow=" + flow);
		observer.notifyFlow("Source",getName(),SimulationObserver.NO_FLOW, flow);
		if(observer instanceof SimulationObserverExt && enableMaxFlowChecks && inFlow>maxFlow)
			((SimulationObserverExt)observer).notifyFlowError(this.getClass().getName(), getName(), inFlow, maxFlow);
		getOutput().simulate(flow,observer,enableMaxFlowChecks);
	}
	
	@Override
	StringBuffer layout(String pad) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("]Source -> ");
		res.append( getOutput().layout(blanks(res.length())) );
		return res;
	}
	
	@Override
	public void setMaxFlow(double maxFlow) {
		return;
	}

	@Override
	void simulate(double inFlow, SimulationObserver observer) {
		simulate(inFlow, observer,false);
	}
	
}
