package mountainhuts;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.net.ssl.CertPathTrustManagerParameters;

import static java.util.stream.Collectors.*;


/**
 * Class {@code Region} represents the main facade
 * class for the mountains hut system.
 * 
 * It allows defining and retrieving information about
 * municipalities and mountain huts.
 *
 */
public class Region {
	
	private String name;
	List<AltitudeRange> altitudeRanges;
	private Map <String,Municipality> municipality;
	private Map <String, MountainHut> mountainHut ;
	
	/**
	 * Create a region with the given name.
	 * 
	 * @param name
	 *            the name of the region
	 */
	public Region(String name) {

		this.name = name;
		altitudeRanges = new ArrayList<>();
		 municipality = new HashMap<>();
		 mountainHut = new HashMap<>();
		
	}

	/**
	 * Return the name of the region.
	 * 
	 * @return the name of the region
	 */
	public String getName() {
		return name;
	}

	/**
	 * Create the ranges given their textual representation in the format
	 * "[minValue]-[maxValue]".
	 * 
	 * @param ranges
	 *            an array of textual ranges
	 */
	public void setAltitudeRanges(String... ranges) {
		
		for (String range : ranges) {
			altitudeRanges.add(new AltitudeRange(range));
		}
		
		
	}

	/**
	 * Return the textual representation in the format "[minValue]-[maxValue]" of
	 * the range including the given altitude or return the default range "0-INF".
	 * 
	 * @param altitude
	 *            the geographical altitude
	 * @return a string representing the range
	 */
	public String getAltitudeRange(Integer altitude) {
		
		String res = altitudeRanges.stream()
				.filter(x -> (x.getMinValue() <= altitude && x.getMaxValue() >= altitude))
				.map(AltitudeRange::toString)
				.findFirst()
				.orElse("0-INF");
				return res;
	}

	private String getAltitudeRange(MountainHut m) {
		if (m.getAltitude().isPresent()) {
			return getAltitudeRange(m.getAltitude().get());
		} else {
			return getAltitudeRange(m.getMunicipality().getAltitude());
		}
	}

	/**
	 * Create a new municipality if it is not already available or find it.
	 * Duplicates must be detected by comparing the municipality names.
	 * 
	 * @param name
	 *            the municipality name
	 * @param province
	 *            the municipality province
	 * @param altitude
	 *            the municipality altitude
	 * @return the municipality
	 */
	public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {
		
		if(municipality != null && municipality.containsKey(name) == true) {
			return municipality.get(name);
		}
		Municipality element = new Municipality (name, province, altitude);
		municipality.put(name, element);
		
		return element;
	}

	/**
	 * Return all the municipalities available.
	 * 
	 * @return a collection of municipalities
	 */
	public Collection<Municipality> getMunicipalities() {
		return municipality.values();
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 *
	 * @param name
	 *            the mountain hut name
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return the mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, String category, Integer bedsNumber,
			Municipality municipality) {
		
		if(mountainHut != null && mountainHut.containsKey(name) == true) {
			return mountainHut.get(name);
		}
		MountainHut element = new MountainHut (name, category, bedsNumber, municipality);
		mountainHut.put(name, element);
		
		return element;
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 * 
	 * @param name
	 *            the mountain hut name
	 * @param altitude
	 *            the mountain hut altitude
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return a mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, Integer bedsNumber,
			Municipality municipality) {
		
		if(mountainHut != null && mountainHut.containsKey(name) == true) {
			return mountainHut.get(name);
		}
		MountainHut element = new MountainHut (name, altitude, category, bedsNumber, municipality);
		mountainHut.put(name, element);
		
		return element;
	}

	/**
	 * Return all the mountain huts available.
	 * 
	 * @return a collection of mountain huts
	 */
	public Collection<MountainHut> getMountainHuts() {
		return mountainHut.values();
	}

	/**
	 * Factory methods that creates a new region by loadomg its data from a file.
	 * 
	 * The file must be a CSV file and it must contain the following fields:
	 * <ul>
	 * <li>{@code "Province"},
	 * <li>{@code "Municipality"},
	 * <li>{@code "MunicipalityAltitude"},
	 * <li>{@code "Name"},
	 * <li>{@code "Altitude"},
	 * <li>{@code "Category"},
	 * <li>{@code "BedsNumber"}
	 * </ul>
	 * 
	 * The fields are separated by a semicolon (';'). The field {@code "Altitude"}
	 * may be empty.
	 * 
	 * @param name
	 *            the name of the region
	 * @param file
	 *            the path of the file
	 */
	public static Region fromFile(String name, String file) {
		Region r = new Region(name);
		List<String> lines = readData(file);
		
		String[] headers = lines.remove(0).split(";");
		Map<String, Integer> h2i = new HashMap<>();
		for (int i=0; i<headers.length; i++) {
			h2i.put(headers[i], i);
		}
		
		lines.forEach(l -> {
			String[] rows = l.split(";");
			
			String provinceName = rows[h2i.get("Province")];
			String municipalityName = rows[h2i.get("Municipality")];
			Integer municipalityAltitude = Integer.parseInt(rows[h2i.get("MunicipalityAltitude")]);
			
			Municipality municipality = r.createOrGetMunicipality(municipalityName,
													provinceName, municipalityAltitude);
			
			String mh_name = rows[h2i.get("Name")];
			String altitude = rows[h2i.get("Altitude")];
			String category = rows[h2i.get("Category")];
			Integer bedsNumber = Integer.parseInt(rows[h2i.get("BedsNumber")]);
			
			
			if (altitude.equals("")) {
				r.createOrGetMountainHut(mh_name, category, bedsNumber, municipality);
			} else {
				r.createOrGetMountainHut(mh_name, Integer.parseInt(altitude), category, bedsNumber, municipality);
			}		
		});
		
		return r;
	}

	/**
	 * Internal class that can be used to read the lines of
	 * a text file into a list of strings.
	 * 
	 * When reading a CSV file remember that the first line
	 * contains the headers, while the real data is contained
	 * in the following lines.
	 * 
	 * @param file the file name
	 * @return a list containing the lines of the file
	 */
	@SuppressWarnings("unused")
	private static List<String> readData(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Count the number of municipalities with at least a mountain hut per each
	 * province.
	 * 
	 * @return a map with the province as key and the number of municipalities as
	 *         value
	 */
	public Map<String, Long> countMunicipalitiesPerProvince() {

		return municipality.values().stream().
				collect(Collectors.groupingBy(x -> x.getProvince(),
												Collectors.counting()));
										
	}

	/**
	 * Count the number of mountain huts per each municipality within each province.
	 * 
	 * @return a map with the province as key and, as value, a map with the
	 *         municipality as key and the number of mountain huts as value
	 */
	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
		
		
		return mountainHut.values().stream().collect(
								Collectors.groupingBy(x -> x.getMunicipality().getProvince(), //classifier
													  Collectors.groupingBy(x -> x.getMunicipality().getName(),Collectors.counting()) //downstream
														)); 
	}

	/**
	 * Count the number of mountain huts per altitude range. If the altitude of the
	 * mountain hut is not available, use the altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the number of mountain huts
	 *         as value
	 */
	public Map<String, Long> countMountainHutsPerAltitudeRange() {
				
				Map<String, Long> res = mountainHut.values().stream()
				.collect(Collectors.groupingBy(x ->  getAltitudeRange(x), 
						Collectors.counting()));
				altitudeRanges.stream()
				.map(x -> x.toString())
				.forEach(r -> res.putIfAbsent(r, 0L)); //to add ranges not present
		return res;
	}

	/**
	 * Compute the total number of beds available in the mountain huts per each
	 * province.
	 * 
	 * @return a map with the province as key and the total number of beds as value
	 */
	public Map<String, Integer> totalBedsNumberPerProvince() {
		
		 return mountainHut.values().stream().collect( Collectors.groupingBy(
				
														x -> x.getMunicipality().getProvince(),
														Collectors.summingInt( x-> x.getBedsNumber())
														)
					);
	
	}

	/**
	 * Compute the maximum number of beds available in a single mountain hut per
	 * altitude range. If the altitude of the mountain hut is not available, use the
	 * altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the maximum number of beds
	 *         as value
	 */
	public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		
		Map<String, Optional<Integer>> res = mountainHut.values().stream()
				.collect(Collectors.groupingBy(x -> getAltitudeRange(x),
						Collectors.mapping(MountainHut::getBedsNumber,
								Collectors.maxBy(Comparator.naturalOrder()))));
		altitudeRanges.stream()
		.map(x -> x.toString())
		.forEach(r -> res.putIfAbsent(r, Optional.of(0))); //to add ranges not present
		
				
		return res;
	}

	/**
	 * Compute the municipality names per number of mountain huts in a municipality.
	 * The lists of municipality names must be in alphabetical order.
	 * 
	 * @return a map with the number of mountain huts in a municipality as key and a
	 *         list of municipality names as value
	 */
	public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
		
		 Map <String, Long> res = mountainHut.values().stream()
				 .collect(Collectors.groupingBy( 
										x -> x.getMunicipality().getName(),
										() -> new TreeMap<String, Long> (Comparator.naturalOrder()),
										counting())
						 );
		 
		 Map<Long, List<String>> tmp =  res.entrySet().stream().collect(
				 
				 		Collectors.groupingBy(
				 						x-> x.getValue(),
				 						Collectors.mapping(
				 										x-> x.getKey(),
				 										Collectors.toList()
				 										)
				 						
				 						)
				 				);
		
		return tmp;
	}

}
