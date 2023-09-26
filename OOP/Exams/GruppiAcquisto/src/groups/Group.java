package groups;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Group {

	private String name;
	private Product product;
	private Map<String, Client> clients = new HashMap<>(); 
	private Map<String, Supplier> suppliers = new HashMap<>(); 
	private List<Bid> bid = new ArrayList<>(); 
	private Map<Bid,Integer> votes= new HashMap<>();

	
	public Group(String name, Product product) {

		this.name = name;
		this.product = product;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Map<String, Client> getClients() {
		return clients;
	}
	public void setClients(Map<String, Client> clients) {
		this.clients = clients;
	}
	public Map<String, Supplier> getSuppliers() {
		return suppliers;
	}
	public void setSuppliers(Map<String, Supplier> suppliers) {
		this.suppliers = suppliers;
	}
	public List<Bid> getBid() {
		return bid;
	}
	public void setBid(List<Bid> bid) {
		this.bid = bid;
	}

	public String Bidlect() {
		
		String tmp="";
		
		List<Bid> tmpl = bid.stream().sorted(Comparator.
				comparing(Bid::getPrice).thenComparing(x->x.getSupplier().getName())).collect(Collectors.toList());
		
		
		for(Bid element: tmpl) {
			
			tmp+= element.getSupplier().getName() + ":"+ element.getPrice()+  ",";
		}
		
		tmp.substring(0, tmp.length()-1);
		return tmp;
	}
	public Map<Bid,Integer> getVotes() {
		return votes;
	}
	public void setVotes(Map<Bid,Integer> votes) {
		this.votes = votes;
	}
	

	public String finales() {
		
		Map<String,Long>  element = this.getVotes().entrySet().stream().
		collect(Collectors.groupingBy(
				x->x.getKey().getSupplier().getName(),
			()->new TreeMap<String,Long> (Comparator.naturalOrder()),
				Collectors.counting()
					)
				);
		String result = "";
		for(String tmp: element.keySet()) {
			
			result+= tmp +":"+ element.get(tmp);
		}
		return result;
		
	}
	

}
