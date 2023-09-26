package clinic;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
//15.05 16.03
/**
 * Represents a clinic with patients and doctors.
 * 
 */
public class Clinic {

	private Map<String, Patient> patients;
	private Map<Integer, Doctor> doctors;
	
	public Clinic() {
		
		this.patients =  new HashMap<>();
		this.doctors  =  new HashMap<>();
		
	}


	/**
	 * Add a new clinic patient.
	 * 
	 * @param first first name of the patient
	 * @param last last name of the patient
	 * @param ssn SSN number of the patient
	 */
	public void addPatient(String first, String last, String ssn) {
		// TODO Complete method
		
		Patient element;
		element = new Patient(first, last, ssn);
		
		this.patients.put(ssn, element);
		

	}


	/**
	 * Retrieves a patient information
	 * 
	 * @param ssn SSN of the patient
	 * @return the object representing the patient
	 * @throws NoSuchPatient in case of no patient with matching SSN
	 */
	public String getPatient(String ssn) throws NoSuchPatient {
		// TODO Complete method
		
		if(!patients.containsKey(ssn)) throw new NoSuchPatient();
		else return patients.get(ssn).toString();
		
	}

	/**
	 * Add a new doctor working at the clinic
	 * 
	 * @param first first name of the doctor
	 * @param last last name of the doctor
	 * @param ssn SSN number of the doctor
	 * @param docID unique ID of the doctor
	 * @param specialization doctor's specialization
	 */
	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
		// TODO Complete method
		
		Doctor element;
		Patient elementp;
		element = new Doctor(first, last, ssn, docID, specialization);
		elementp = new Patient(first,last, ssn);
		this.doctors.put(docID, element);
		this.patients.put(ssn, elementp);
	}

	/**
	 * Retrieves information about a doctor
	 * 
	 * @param docID ID of the doctor
	 * @return object with information about the doctor
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public String getDoctor(int docID) throws NoSuchDoctor {
		// TODO Complete method
		
		if(!doctors.containsKey(docID)) throw new NoSuchDoctor();
		else return doctors.get(docID).toString();
	}
	
	/**
	 * Assign a given doctor to a patient
	 * 
	 * @param ssn SSN of the patient
	 * @param docID ID of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
		// TODO Complete method

		if(!doctors.containsKey(docID)) throw new NoSuchDoctor();
		if(!patients.containsKey(ssn)) throw new NoSuchPatient();
		
		Patient patient;
		Doctor doctor;
		patient = patients.get(ssn);
		doctor =  doctors.get(docID);
		
		doctor.getPatients().add(patient);
		patient.setDoctor(doctor);
	}
	
	/**
	 * Retrieves the id of the doctor assigned to a given patient.
	 * 
	 * @param ssn SSN of the patient
	 * @return id of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor has been assigned to the patient
	 */
	public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
		// TODO Complete method
		if(!patients.containsKey(ssn)) throw new NoSuchPatient();
		if(patients.get(ssn).getDoctor() == null) throw new NoSuchDoctor();
		
		return patients.get(ssn).getDoctor().getDocID();
	}
	
	/**
	 * Retrieves the patients assigned to a doctor
	 * 
	 * @param id ID of the doctor
	 * @return collection of patient SSNs
	 * @throws NoSuchDoctor in case the {@code id} does not match any doctor 
	 */
	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {
		// TODO Complete method
		if(!doctors.containsKey(id)) throw new NoSuchDoctor();
		
		
		return doctors.get(id).getPatients().stream().map( x->x.getSsn()).collect(Collectors.toList());
	}


	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and specialization.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method should be able
	 * to ignore the row and skip to the next one.<br>

	 * 
	 * @param readed linked to the file to be read
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader) throws IOException {
		// TODO Complete method
		String line;
		String[] contents;
		int counter =0;
		
		try {
		BufferedReader tmp = new BufferedReader(reader);
			while( (line = tmp.readLine())!= null) {
				
				contents = line.split("\\s*;\\s*");
				
				if(contents[0].equals("P")) {
					
					if(contents.length != 4) continue;
					
					counter++;

					
					addPatient(contents[1],contents[2],contents[3]);
					
				} else if(contents[0].equals("M")) {
					
					if(contents.length != 6) continue;
					 counter++;
					addDoctor(contents[2],contents[3],contents[4],Integer.parseInt(contents[1]),contents[5].trim());
				}
				
			}
		} catch(IOException tmp ) {
			
		}
		

		return counter;		
	}



	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and speciality.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method calls the
	 * {@link ErrorListener#offending} method passing the line itself,
	 * ignores the row, and skip to the next one.<br>

	 * 
	 * @param reader reader linked to the file to be read
	 * @param listener listener used for wrong line notifications
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader, ErrorListener listener) throws IOException {
		String line;
		String[] contents;
		int counter =0 ;
		
		try {
			BufferedReader tmp = new BufferedReader(reader);
				while( (line = tmp.readLine())!= null) {
					
					contents = line.split("\\s*;\\s*");
					
					if(contents[0].equals("P")) {
						if(contents.length != 4) continue;
						 counter++;
						addPatient(contents[1],contents[2],contents[3]);
					} else
					if(contents[0].equals("M")) {
						if(contents.length != 6) continue;
						 counter++;
						addDoctor(contents[2],contents[3],contents[4],Integer.parseInt(contents[1]),contents[5].trim());
						
					} else {
						listener.offending(line);
						}
						
						
					}
			
		}
			 catch(IOException tmp ) {}
		
		return counter;		
	}

		
	/**
	 * Retrieves the collection of doctors that have no patient at all.
	 * The doctors are returned sorted in alphabetical order
	 * 
	 * @return the collection of doctors' ids
	 */
	public Collection<Integer> idleDoctors(){
		// TODO Complete method
		return doctors.entrySet().stream().map(x-> x.getValue()).filter( x->x.getPatients().isEmpty())
				.sorted(Comparator.comparing( Doctor::getLast).thenComparing(Doctor::getFirst)).
				map(x->x.getDocID()).
				collect(
				Collectors.toList()
				);
	}

	/**
	 * Retrieves the collection of doctors having a number of patients larger than the average.
	 * 
	 * @return  the collection of doctors' ids
	 */
	public Collection<Integer> busyDoctors(){
			
		return doctors.entrySet().stream().map( x-> x.getValue()).filter( x-> (x.getPatients().size()> computeAvg()) ).
				map(x->x.getDocID()).collect(Collectors.toList());
	}
	
	
	public float computeAvg() {
		int counter = doctors.size();

		float avg = doctors.entrySet().stream().map(x->x.getValue().getPatients().size()).reduce(0, Integer::sum)/counter ;
		return avg;
	}

	/**
	 * Retrieves the information about doctors and relative number of assigned patients.
	 * <p>
	 * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
	 * represent the number of patients (printed on three characters).
	 * <p>
	 * The list is sorted by decreasing number of patients.
	 * 
	 * @return the collection of strings with information about doctors and patients count
	 */
	public Collection<String> doctorsByNumPatients(){
		
		
		
			return	patients.values().stream()
						.filter(p->p.getDoctor()!=null)
						.collect(Collectors.groupingBy(Patient::getDoctor,counting()))
						.entrySet().stream()
//						.sorted(comparing(Map.Entry::getValue).reversed()) // the compiler cannot infer the type
						.sorted(Comparator.comparing(e -> e.getValue(),reverseOrder())) // while here it can
						.map( e -> String.format("%3d", e.getValue()) + " : "
								+ e.getKey().getDocID() +  " " + e.getKey().getLast() + " " + e.getKey().getFirst()
						)
						.collect(Collectors.toList())
						;
	}
	
	/**
	 * Retrieves the number of patients per (their doctor's)  speciality
	 * <p>
	 * The information is a collections of strings structured as {@code ### - SPECIALITY}
	 * where {@code SPECIALITY} is the name of the speciality and 
	 * {@code ###} is the number of patients cured by doctors with such speciality (printed on three characters).
	 * <p>
	 * The elements are sorted first by decreasing count and then by alphabetic speciality.
	 * 
	 * @return the collection of strings with speciality and patient count information.
	 */
	public Collection<String> countPatientsPerSpecialization(){
		
		
		return patients.values().stream().filter(x->(x.getDoctor() != null)).
				map(x ->x.getDoctor()).collect(
						Collectors.groupingBy(
								x-> x.getSpecialization(),
								HashMap::new,
								Collectors.counting()
								)).entrySet().stream().
						
						sorted(Comparator.comparing(Map.Entry::getValue,Comparator.reverseOrder()))
						.map(e -> String.format("%3d", e.getValue()) + " - " + e.getKey() )
						.collect(Collectors.toList());
		
		
		
	}
	
}
