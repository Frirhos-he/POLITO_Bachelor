package transactions;
import java.util.*;
import java.util.stream.Collectors;

//import static java.util.stream.Collectors.*;
//import static java.util.Comparator.*;

public class TransactionManager {
	
	Map<String,Region> regions = new TreeMap<>();
	Map<String,Request> requests = new TreeMap<>();
	Map<String,Carrier> carriers = new TreeMap<>();
	Map<String,Place> places = new TreeMap<>();
	Map<String,Offer> offers = new TreeMap<>();
	Map<String,Transaction> transactions = new TreeMap<>();
	
//R1

	public List<String> addRegion(String regionName, String... placeNames) { 
		
		List<String> placesS = new ArrayList<>();

		placesS = Arrays.asList(placeNames);
		placesS = placesS.stream().distinct().filter(x-> !places.containsKey(x)).collect(Collectors.toList());
		Collections.sort(placesS);
		
		Map<String,Place> place = new TreeMap<>();
		
		for(String tmp: placesS) {
			place.put(tmp, new Place(tmp));
		}
		
		
		Region element = new Region(regionName, place);
		for(String tmp: placesS) {
			place.get(tmp).setRegions(element);
		}
		
		regions.put(regionName, element);
		places.putAll(place);
		
		
		return placesS;
	}
	
	public List<String> addCarrier(String carrierName, String... regionNames) { 
		
		List<String> regionsS = new ArrayList<>();

		regionsS = Arrays.asList(regionNames);
		regionsS = regionsS.stream().distinct().collect(Collectors.toList());
		Collections.sort(regionsS);
		
		Map<String,Region> regionsR = new TreeMap<>();
		
		for(String tmp: regionsS) {
			if(regions.containsKey(tmp)) {
					regionsR.put(tmp, regions.get(tmp));
			}
		}
		
		
		Carrier element = new Carrier(carrierName, regionsR);
		carriers.put(carrierName, element);
		
		for(Region tmp: regionsR.values()) {
			tmp.getCarriers().put(carrierName,element);
		}
	
		
		return regionsR.keySet().stream().sorted().collect(Collectors.toList());
	}
	
	public List<String> getCarriersForRegion(String regionName) { 
		return regions.get(regionName).getCarriers().keySet().stream().sorted().collect(Collectors.toList());
	}
	
//R2
	public void addRequest(String requestId, String placeName, String productId) 
			throws TMException {
		
		if(requests.containsKey(requestId)) throw new TMException();
		if(!places.containsKey(placeName)) throw new TMException();
		
		Request element = new Request(requestId, places.get(placeName), productId);
		requests.put(requestId, element);
		places.get(placeName).getRequests().put(requestId, element);
		
	}
	
	public void addOffer(String offerId, String placeName, String productId) 
			throws TMException {
		
		if(offers.containsKey(offerId)) throw new TMException();
		if(!places.containsKey(placeName)) throw new TMException();
		
		Offer element = new Offer(offerId, places.get(placeName), productId);
		offers.put(offerId, element);
		places.get(placeName).getOffers().put(offerId, element);
		
		
	}
	

//R3
	public void addTransaction(String transactionId, String carrierName, String requestId, String offerId) 
			throws TMException {
		
		Region reqPlace = requests.get(requestId).getPlace().getRegions();
		Region offPlace = offers.get(offerId).getPlace().getRegions();
		
		if(requests.get(requestId).getTransactionId() != null) throw new TMException();
		else if(offers.get(offerId).getTransactionId() != null) throw new TMException();
		else if(!offers.get(offerId).getProductId().equals(requests.get(requestId).getProductId())) throw new TMException();
		
		
		else if(!carriers.get(carrierName).getRegions().containsKey(reqPlace.getRegionName()))throw new TMException();
		else if(!carriers.get(carrierName).getRegions().containsKey(offPlace.getRegionName()))throw new TMException();
		else {
		
		Transaction element = new Transaction( transactionId,carriers.get(carrierName), requests.get(requestId),offers.get(offerId)
				,offers.get(offerId).getProductId());
		requests.get(requestId).setTransactionId(element);
		offers.get(offerId).setTransactionId(element);
		
		transactions.put(transactionId, element);
		requests.get(requestId).getPlace().getrTransaction().put(transactionId, element);
		offers.get(offerId).getPlace().getoTransaction().put(transactionId, element);
		}
	}
	
	public boolean evaluateTransaction(String transactionId, int score) {
		if(score < 1 || score > 10 ) return false;
		else {
			transactions.get(transactionId).setScore(score);
			return true;
		}
	}
	
//R4
	public SortedMap<Long, List<String>> deliveryRegionsPerNT() {
		
		
			return transactions.values().stream().map(x->x.getRequest().getPlace().getRegions()).
			collect(Collectors.groupingBy(
					x->x.getRegionName()
					, ()-> new TreeMap<String,Long>(Comparator.naturalOrder())
					,Collectors.counting())).entrySet().stream().
			
			collect(Collectors.groupingBy(
					
					x->x.getValue(),
					()-> new TreeMap<>(Comparator.reverseOrder()),
					Collectors.mapping(x->x.getKey(), Collectors.toList())
					));
			
	}
	
	public SortedMap<String, Integer> scorePerCarrier(int minimumScore) {
		 return transactions.values().stream().filter(x->x.getScore()>=minimumScore).collect(
				Collectors.groupingBy(
						
						x->x.getCarrier().getCarrierName(),
						()-> new TreeMap<String,Integer>(Comparator.naturalOrder()),
						 Collectors.summingInt(x->x.getScore())
						));

	}
	
	public SortedMap<String, Long> nTPerProduct() {
		
		return transactions.values().stream().collect(
				Collectors.groupingBy(
						
						x->x.getProductId(),
						()-> new TreeMap<String,Long>(Comparator.naturalOrder()),
						 Collectors.counting()
						));
		
	}
	
	
}

