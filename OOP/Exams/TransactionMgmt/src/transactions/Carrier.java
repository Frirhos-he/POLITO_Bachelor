package transactions;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Carrier {

	private String carrierName;
	Map<String, Region> regions = new TreeMap<>();;
	
	public Carrier(String carrierName, Map<String,Region> regions) {

		this.carrierName = carrierName;
		this.regions = regions;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public Map<String, Region> getRegions() {
		return regions;
	}

	public void setRegions(Map<String, Region> regions) {
		this.regions = regions;
	}


	
	
	

}
