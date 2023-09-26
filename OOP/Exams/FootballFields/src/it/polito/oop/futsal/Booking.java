package it.polito.oop.futsal;

import java.util.List;

import it.polito.oop.futsal.Fields.Features;

public class Booking implements FieldOption{

	private Integer field;
	private Associate associate;
	private Integer hour;
	private Integer minute;
	private String time;
	private List<Booking> booking;

	public Booking(List<Booking> booking, int field, Associate associate, Integer hour, Integer minute, String time) {

		this.field = field;
		this.associate = associate;
		this.hour = hour;
		this.minute = minute;
		this.time = time;
		this.booking = booking;
	}

	public Associate getAssociate() {
		return associate;
	}
	public void setAssociate(Associate associate) {
		this.associate = associate;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public Integer getMinute() {
		return minute;
	}
	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public Integer getFild() {
		return field;
	}
	public void setFild(Integer fild) {
		this.field = fild;
	}
	
	public Integer counter() {
	return hour*60 + minute;
	}

	@Override
	public int getField() {
		// TODO Auto-generated method stub
		return field;
	}

	@Override
	public int getOccupation() {
		// TODO Auto-generated method stub
		return booking.size();
	}
	
	
	

}
