package mountainhuts;

import java.util.Optional;

/**
 * Represents a mountain hut.
 * 
 * It is linked to a {@link Municipality}
 *
 */
public class MountainHut {

	private String name;
	private String category;
	private Integer bedsNumber;
	private Municipality municipality;
	private Optional<Integer> altitude;
	
	public MountainHut(String name, String category, Integer bedsNumber, Municipality municipality) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.category = category;
		this.bedsNumber = bedsNumber;
		this.municipality = municipality;
		this.altitude = Optional.ofNullable(null);
		
	}

	public MountainHut(String name2, Integer altitude, String category2, Integer bedsNumber2,
			Municipality municipality2) {
		// TODO Auto-generated constructor stub
		this.name = name2;
		this.category = category2;
		this.bedsNumber = bedsNumber2;
		this.municipality = municipality2;
		this.altitude = Optional.ofNullable(altitude);
	}

	/**
	 * Unique name of the mountain hut
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Altitude of the mountain hut.
	 * May be absent, in this case an empty {@link java.util.Optional} is returned.
	 * 
	 * @return optional containing the altitude
	 */
	public Optional<Integer> getAltitude() {
				
				
				return altitude;
			

	}

	/**
	 * Category of the hut
	 * 
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Number of beds places available in the mountain hut
	 * @return number of beds
	 */
	public Integer getBedsNumber() {
		return bedsNumber;
	}

	/**
	 * Municipality where the hut is located
	 *  
	 * @return municipality
	 */
	public Municipality getMunicipality() {
		return municipality;
	}

}
