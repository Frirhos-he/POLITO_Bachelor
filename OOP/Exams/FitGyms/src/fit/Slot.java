package fit;

public class Slot {
	private int day;
	private int start;
	
	public Slot(int day, int start) {
		this.day = day;
		this.start = start;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + start;
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
		Slot other = (Slot) obj;
		if (day != other.day)
			return false;
		if (start != other.start)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return Integer.toString(day) + Integer.toString(start);
	}
	

}
