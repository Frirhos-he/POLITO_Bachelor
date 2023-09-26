package university;
import static university.Constants.*; 
//all static members are accessible without specifying the class name


public class Student {
	
	
	private String first;
	private String last;
	
	private int studentId; 
	
	private Course [] courses;
	private String row;
	
	private int [] grades;
	
	public Student(String first, String last, int id) {
		
		this.studentId = FIRST_STUDENT + id;
		this.first = first;
		this.last = last;
		courses = new Course [MAX_COURSE];
		grades = new int[MAX_COURSE];

		for(int i= 0; i < MAX_COURSE; i++) {
			grades[i] = -1;
		}
		
	}
	
	public String list() {
		
		if(courses[0] != null) { // to avoid null value
			row = courses[0].toString() + "\n";
		}
		
		for(int i=1; courses[i] != null; ++i) {
			
			row = row + courses[i].toString() + "\n";
		}
		return row;
	}
	
	
	public float studentAverageS() {
			
			float avg = 0;
			int counter = 0;
			int i;
			
			for(i = 0; i < MAX_COURSE; ++i) {
				
				if(this.getGrades(i)>= 0 && this.getGrades(i) < 31 ) {
					avg += this.getGrades(i);
					counter++;
				}	
			}
			return avg/counter;
			
		}
	
	
	public String getFullName() {
		return this.first + " " + this.last;
	}

	public int getStudentId() {
		return this.studentId;
	}
	

	public int getGrades(int index) {
		
		return grades[index];
	}

	public void setGrades(int grade, int index) {
		this.grades[index-10] = grade;
	}

	public void enroll(Course c){

		for(int i=0; i< courses.length; ++i){
			if( courses[i] == null){
				courses[i] = c;
				break;
			}
		}
	}
	
	public int getNumCourse(){
		int counter = 0;
		for(int i = 0; i< courses.length; ++i) {
			if(courses[i] != null) {
				counter++;
			}
		}
		return counter;
	}
	
	public int getNumExams() {
		int counter = 0;
		for(int i = 0; i< grades.length; ++i) {
			if(grades[i] != -1) {
				counter++;
			}
		}
		return counter;
	}
	
	public String toString() {
		String tag;
		
		tag = this.studentId + " " + this.first + " " + this.last;
				
		return tag;
		}
	
	
	public float addBonus() {
		 float bonus=0;
		 if( getNumExams()!=0 ) // because denominator can't be zero
		 bonus = (float) 10 * (getNumExams() / getNumCourse());
		 return bonus;
		 }
}
