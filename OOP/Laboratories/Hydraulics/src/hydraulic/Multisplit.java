package hydraulic;

/**
 * Represents a multisplit element, an extension of the Split that allows many outputs
 * 
 * During the simulation each downstream element will
 * receive a stream that is determined by the proportions.
 */

public class Multisplit extends Split {

	private double[] proportions;
	private int numOutput;
	
	/**
	 * Constructor
	 * @param name
	 */
	public Multisplit(String name, int numOutput) {
		super(name, numOutput);
		this.numOutput = numOutput;
	}
    
//	/**
//	 * returns the downstream elements
//	 * @return array containing the two downstream element
//	 */
//    public Element[] getOutputs(){
//    		//TODO: complete
//        return null;
//    }

//    /**
//     * connect one of the outputs of this split to a
//     * downstream component.
//     * 
//     * @param elem  the element to be connected downstream
//     * @param noutput the output number to be used to connect the element
//     */
//	public void connect(Element elem, int noutput){
//		//TODO: complete
//	}
	
	/**
	 * Define the proportion of the output flows w.r.t. the input flow.
	 * 
	 * The sum of the proportions should be 1.0 and 
	 * the number of proportions should be equals to the number of outputs.
	 * Otherwise a check would detect an error.
	 * 
	 * @param proportions the proportions of flow for each output
	 */
	public void setProportions(double... proportions) {
		this.proportions = proportions;
	}
	
	@Override
	void simulate(double inFlow, SimulationObserver observer, boolean enableMaxFlowChecks) {
		double[] outFlow = new double[numOutput];
		for(int i=0; i<outFlow.length; ++i) {
			outFlow[i] = inFlow * proportions[i];
		}
		observer.notifyFlow("MultiSplit", getName(), inFlow, outFlow);
		if(enableMaxFlowChecks && observer instanceof SimulationObserverExt && inFlow>maxFlow)
			((SimulationObserverExt)observer).notifyFlowError(this.getClass().getName(), getName(), inFlow, maxFlow);
		for(int i=0; i<outFlow.length; ++i) {
		    Element e = getOutputs()[i];
			e.simulate(outFlow[i],observer,enableMaxFlowChecks);
		}
	}

	@Override
	StringBuffer layout(String pad) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("]Split +-> ");
		
		String subPad = pad + blanks(res.length()-4) ;
		
		for(int i=0; i<this.numOutput; ++i) {
			if(i>0) {
				res.append("\n");
				res.append(subPad).append("|\n");
				res.append(subPad + "+-> ");
			}
			res.append( getOutputs()[i].layout(subPad+"|   ") );
		}
		return res;
	}

}
