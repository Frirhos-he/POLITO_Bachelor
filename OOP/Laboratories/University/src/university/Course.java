package university;
import static university.Constants.*; 
//all static members are accessible without specifying the class name

public class Course {

	
	
	
	private String courseName;
	private String professorName;
	private int    courseId;
	private Student [] students;
	private String lines;
	
	public String list() {
		
		if(students[0] != null) { // to avoid null value
			lines = students[0].toString() + "\n";
		}
		
		for(int i=1; students[i] != null; ++i) {
			
			lines = lines + students[i].toString() + "\n";
		}
		return lines;
	}
	
	
	public int getCourseId() {
		return courseId;
	}


	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public float courseAverage(int courseId) {
		
		int i;
		float avg = 0;
		int counter = 0;
		
		for(i = 0; i < MAX_STUDENTS; ++i) {
			
			
			if(this.getStudents(i) != null && this.getStudents(i).getGrades(courseId - FIRST_COURSE) != -1) {
			
				avg += this.getStudents(i).getGrades(courseId - FIRST_COURSE);
				counter++;
			}	
		}
		
		return avg/counter;
	}
	
	
	public Course(String cname, String pname, int cid) {
		this.courseName = cname;
		this.professorName = pname;
		this.courseId = FIRST_COURSE + cid;
		students = new Student[MAX_STUDENTS];
	}
	
	public void regs(Student s){
		for(int i=0; i< students.length; ++i){
			if( students[i] == null){
				students[i] = s;
				break;
			}
		}
	}

	public void setStudents(Student[] attendee) {
		this.students = attendee;
	}


	public Student getStudents(int index) {
		return students[index];
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String toString() {
		String tag;
		
		tag = this.courseId + " " + this.courseName + " " + this.professorName;
		//or c[this.ccounter + 10].cid + "," + c[this.ccounter + 10].cname + "," + c[this.ccounter + 10].pname
				
		return tag;
		}
	
	
}
