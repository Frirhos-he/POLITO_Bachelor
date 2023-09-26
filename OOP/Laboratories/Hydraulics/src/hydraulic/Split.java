package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends ElementExt {

	/**
	 * Constructor
	 * @param name
	 */
	public Split(String name) {
		super(name,2);
	}

	
	protected Split(String name, int numOutput) {
		super(name,numOutput);
	}

	/**
	 * returns the downstream elements
	 * @return array containing the two downstream elements
	 */
//    public Element[] getOutputs(){
//    		//TODO: complete
//        return null;
//    }
//
//	public void connect(Element elem, int noutput){
//		//TODO: complete
//	}

	@Override
	void simulate(double inFlow, SimulationObserver observer, boolean enableMaxFlowChecks) {
		//System.out.println("Source " + getName() + ": flow=" + flow);
		double outFlow = inFlow/2;
//		System.out.println("Source " + getName() + ": flow=" + inFlow);
//		System.out.println(" : out flow=" + outFlow);

		observer.notifyFlow("Split", getName(), inFlow, outFlow, outFlow);
		if(observer instanceof SimulationObserverExt && enableMaxFlowChecks && inFlow>maxFlow)
			((SimulationObserverExt)observer).notifyFlowError(this.getClass().getName(), getName(), inFlow, maxFlow);
		for(Element e : getOutputs()){
			e.simulate(outFlow,observer,enableMaxFlowChecks);
		}
	}
	
	@Override
	void simulate(double inFlow, SimulationObserver observer) {
		simulate(inFlow, observer,false);
	}
	
	@Override
	StringBuffer layout(String pad) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("]Split +-> ");
		
		
		String subPad = pad + blanks(res.length()-4) ;

		res.append( getOutputs()[0].layout(subPad+"|   ") );
		res.append("\n");
		
		res.append(subPad).append("|\n");
		
		res.append(subPad + "+-> ");
		res.append( getOutputs()[1].layout( subPad + "    ") );
		return res;
	}

}
