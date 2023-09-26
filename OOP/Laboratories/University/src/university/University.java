package university;
import static university.Constants.*; 
//all static members are accessible without specifying the class name


/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {

	/**
	 * Constructor
	 * @param name name of the university
	 */

	
	private String univ;
	private String rect;
	
	private int studentCounter;
	private int courseCounter;
	
	private  static Course []  course;
	private  static Student [] student; 
	
	private Student tmps;
	private Course tmpc;
	
	
	public University(String name){
		//TODO: to be implemented
		
		this.univ = name;
		this.studentCounter = 0;
		this.courseCounter = 0;
		student = new Student [MAX_STUDENTS];
		course = new Course [MAX_COURSE];
	}
	
	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){
		//TODO: to be implemented
		
		return this.univ;
		//return null;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		//TODO: to be implemented
		this.rect = first + " " + last;
		
	}
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		//TODO: to be implemented
		
		
		return rect;
		//return null;
	}
	
	/**
	 * Enroll a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */

	public int enroll(String first, String last){
		//TODO: to be implemented
		
		
		if(this.studentCounter >= MAX_STUDENTS) {
			System.err.println("OVERSIZE");
			System.exit(-1);
		}
		
	  student[this.studentCounter] = new Student(first, last, this.studentCounter);
		
		
		return student[this.studentCounter++].getStudentId();
	}
	
	/*
	 * Retrieves the information for a given student
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int id){
		//TODO: to be implemented
		
		return student[id - FIRST_STUDENT].toString();
		//return null;
	}
	
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		//TODO: to be implemented
		
		 course[this.courseCounter] = new Course(title , teacher, this.courseCounter);
		 
		return course[this.courseCounter++].getCourseId();
	}
	
	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		//TODO: to be implemented
		
		/*
		 * that accepts the course's code and returns a string
		 * containing code, title, and teacher separated by commas,
		 *  e.g., "10,Object Oriented Programming,James Gosling".
		 */
		return course[code-FIRST_COURSE].toString();
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		//TODO: to be implemented
		
		for(int i = 0; i< MAX_STUDENTS; i++ ) {
			if(student[i].getStudentId() == studentID) {
				tmps = student[i];
				break;
			}
			
		}
		for(int i = 0; i< MAX_COURSE; i++) {
			if(course[i].getCourseId() == courseCode) {
				tmpc = course[i];
				break;
			}
		}
		
		if(tmps == null || tmpc == null) {
			System.err.println("Error student, class not found");
			System.exit(-1);
		}
		tmps.enroll(tmpc);
		tmpc.regs(tmps);
		
	}
	
	public static Student [] getStudents() {
		
		return student;

	}

	public static Student getStudent(int studentID) {
		
		return student[studentID - FIRST_STUDENT];

	}
	public static Course getCourse(int courseId) {
		
		return course[courseId - FIRST_COURSE];
	}

	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		//TODO: to be implemented
		
		for(int i = 0; i< MAX_STUDENTS; i++) {
			if(course[i].getCourseId() == courseCode) {
				tmpc = course[i];
				break;
			}
		}

		return tmpc.list();
	}

	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		//TODO: to be implemented
		for(int i = 0; i< MAX_COURSE; i++) {
			if(student[i].getStudentId() == studentID) {
				tmps = student[i];
				break;
			}
		}
		
		return tmps.list();
	}
}

