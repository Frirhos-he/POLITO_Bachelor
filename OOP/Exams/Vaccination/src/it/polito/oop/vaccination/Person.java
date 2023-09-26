package it.polito.oop.vaccination;

public class Person {

	private String ssn;
	private String first;
	private String last;
	private Integer y;
	public Hub hub;//
	public Integer day = -1;
	public Person(String ssn, String first, String last, Integer y) {

		this.ssn = ssn;
		this.first = first;
		this.last = last;
		this.y = y;
	}


	public String getSsn() {
		return ssn;
	}


	public void setSsn(String ssn) {
		this.ssn = ssn;
	}


	public String getFirst() {
		return first;
	}


	public void setFirst(String first) {
		this.first = first;
	}


	public String getLast() {
		return last;
	}


	public void setLast(String last) {
		this.last = last;
	}


	public Integer getY() {
		return y;
	}


	public void setY(Integer y) {
		this.y = y;
	}
	
	public String print() {
		
		return this.ssn+","+this.last +"," + this.first;
	}
	
	

}
