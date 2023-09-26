package clinic;

import java.util.ArrayList;
import java.util.List;

public class Doctor {

	

	private String first;
	private String last;
	private String ssn;
	private int docID;
	private String specialization;
	private List<Patient> patients;
	
	public Doctor(String first, String last, String ssn, int docID, String specialization) {
		// TODO Auto-generated constructor stub
		this.first = first;
		this.last = last;
		this.ssn = ssn;
		this.docID = docID;
		this.specialization = specialization;
		this.patients = new ArrayList<>();
	}


	@Override
	public String toString() {
		return last + " " + first + " (" + ssn + ") " + "[" + docID + "]: " + specialization;   
	}


	public List<Patient> getPatients() {
		return patients;
	}

	public int getPatientsSize() {
		return patients.size();
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}


	public int getDocID() {
		return docID;
	}
	
	public String patientNum (){
		return String.format("%3d", getPatientsSize())+" " + this.docID +" "+ this.last+" " + this.first;
	}


	public String getFirst() {
		return first;
	}


	public String getLast() {
		return last;
	}


	public String getSpecialization() {
		return specialization;
	}



}
