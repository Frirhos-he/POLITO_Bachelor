package it.polito.oop.futsal;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;

//20.20 22.17

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a infrastructure with a set of playgrounds, it allows teams
 * to book, use, and  leave fields.
 *
 */
public class Fields   {
    
	private Integer counter = 1;
	private Map<Integer, Features> pieces = new HashMap<>();
	private String opening;
	private String closure = "00:00";
	
	 private Integer Closurehour = 0;
	 private Integer Closureminute = 0;
	 private Integer Openhour;
	 private Integer Openminute;
	
	private Map<Integer, Associate> associates = new HashMap<>();
	private Map<Integer, List<Booking>> booked = new HashMap<>();
	
	
    public static class Features {
        public final boolean indoor; // otherwise outdoor
        public final boolean heating;
        public final boolean ac;
        
        public Features(boolean i, boolean h, boolean a) {
            this.indoor=i; this.heating=h; this.ac = a;
        }

		public boolean isIndoor() {
			return indoor;
		}

		public boolean isHeating() {
			return heating;
		}

		public boolean isAc() {
			return ac;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (ac ? 1231 : 1237);
			result = prime * result + (heating ? 1231 : 1237);
			result = prime * result + (indoor ? 1231 : 1237);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Features other = (Features) obj;
			if (ac != other.ac)
				return false;
			if (heating != other.heating)
				return false;
			if (indoor != other.indoor)
				return false;
			return true;
		}
        
        
    }
    
    public void defineFields(Features... features) throws FutsalException {
    	
    	for(int i = 0; i<features.length; ++i) {
    		if(features[i].indoor == false && (features[i].ac == true || features[i].heating == true)) {
    			throw new FutsalException();
    		}
    		pieces.put(counter, features[i]);
    		counter++;
    	}
    	
        
    }
    
    public long countFields() {
        return pieces.size();
    }

    public long countIndoor() {
        return pieces.values().stream().filter(x-> (x.indoor == true)).count();
    }
    
    public String getOpeningTime() {
        return opening;
    }
    
    public void setOpeningTime(String time) {
    		
    	this.opening = time;
    	String[] tmp = time.split(":");
    	Openhour = Integer.valueOf(tmp[0]);
    	Openminute = Integer.valueOf(tmp[1]);
    	
    }
    
    public String getClosingTime() {
    	
        return closure;
    }
    
    public void setClosingTime(String time) {
    	this.closure = time;
    	
    	String[] tmp = time.split(":");
    	 Closurehour = Integer.valueOf(tmp[0]);
    	 Closureminute = Integer.valueOf(tmp[1]);
    }

    public int newAssociate(String first, String last, String mobile) {
    	
    	Associate element = new Associate (first, last, mobile);
    	associates.put(element.hashCode(), element);
    	
        return element.hashCode();
    }
    
    public String getFirst(int partyId) throws FutsalException {
    	
    	if(!associates.containsKey(partyId)) throw new FutsalException();
        return associates.get(partyId).getName();
    }
    
    public String getLast(int associate) throws FutsalException {
    	if(!associates.containsKey(associate)) throw new FutsalException();
        return associates.get(associate).getSurname();
    }
    
    public String getPhone(int associate) throws FutsalException {
    	if(!associates.containsKey(associate)) throw new FutsalException();
        return associates.get(associate).getSurname();
    }
    
    public int countAssociates() {
        return associates.size();
    }
    
    public void bookField(int field, int associate, String time) throws FutsalException {
    	
    	
    	String[] tmp = time.split(":");
    	Integer hour = Integer.valueOf(tmp[0]);
    	Integer minute = Integer.valueOf(tmp[1]);
    	Integer count = (hour*60+ minute); 
    	

    	if( (  (count-this.getMinutes())% 60  ) != 0 ) throw new FutsalException();
    	if(!pieces.containsKey(field)) throw new FutsalException();
    	if(!associates.containsKey(associate)) throw new FutsalException();
    	List<Booking> tme = new ArrayList<>();
    	
    	Booking element = new Booking(tme,field, associates.get(associate),hour, minute, time );
    	if(!booked.containsKey(field)) {
    		
    		booked.put(field, tme);
    	}
    	booked.get(field).add(element);    	

    }
    public Integer getMinutes() {
    	return (this.Openhour*60 + this.Openminute);
    }

    public boolean isBooked(int field, String time) {
    	

    	String[] tmp = time.split(":");
    	Integer hour = Integer.valueOf(tmp[0]);
    	Integer minute = Integer.valueOf(tmp[1]);
    	Integer count = hour*60 + minute;
    	
    	if(
    			booked.entrySet().stream().filter(x->x.getKey().equals(field)).map(x->x.getValue()).flatMap(x->x.stream()).
    			anyMatch(x->x.getTime().equals(time))
    			|| 	booked.entrySet().stream().filter(x->x.getKey().equals(field)).map(x->x.getValue()).flatMap(x->x.stream()).
    			anyMatch(x->((x.counter()- count)> 0 && (x.counter()-count)< 60) ) )	
    			 {
    		return true;
    			 }
    	else {
    		return false;
    	}
    }
    

    public int getOccupation(int field) {
        return booked.get(field).size();
    }
    
    
    public List<FieldOption> findOptions(String time, Features required){

    			return  booked.values().stream().flatMap(x->x.stream())
    	    			.filter(x-> ((pieces.get(x.getFild()).ac == required.ac) && (pieces.get(x.getFild()).indoor == required.indoor)
    	    					&& (pieces.get(x.getFild()).heating == required.heating)))
    	    			.sorted(Comparator.comparing(Booking::getField).reversed().thenComparing(Booking::getOccupation))
    	    			.collect(
    	    					
    	    					 Collectors.toList()
    	    					);
    }
    
    public long countServedAssociates() {
        return -1;
    }
    
    public Map<Integer,Long> fieldTurnover() {
        return null;
    }
    
    public double occupation() {
        return -1.0;
    }

//	@Override
//	public int getField() {
//		// TODO Auto-generated method stub
//		
//		return 0;
//	}
//
//	@Override
//	public int getOccupation() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
    
}
