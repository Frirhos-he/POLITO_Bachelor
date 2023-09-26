package ticketing;

import java.util.HashMap;
import java.util.Map;

public class Component {

	
	private String name;

	
	private Map<String, Component> childs = new HashMap<>();

	
	public Component(String name) {

		this.name = name;
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Component> getChilds(){
	
		return childs;
	}
	public void setChilds(Map<String, Component> childs) {
		this.childs = childs;
	}

	public void addChilds(Component element) throws TicketException  {
		// TODO Auto-generated method stub
		if(childs.containsKey(element.getName())) throw new TicketException();
		this.childs.put(element.getName(), element);

	}

}
