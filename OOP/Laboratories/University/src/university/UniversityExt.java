package university;

import java.util.logging.Logger;
import static university.Constants.*; 

/**
 * This class is an extended version of the {@Link University} class.
 * 
 *
 */
public class UniversityExt extends University {
	
	private final static Logger logger = Logger.getLogger("University");
	
	
	public UniversityExt(String name) {
		super(name);
		// Example of logging
		logger.info("Creating extended university object");
	}
	
	@Override
	public int enroll(String first, String last ) {
		
		int studentId = super.enroll(first, last);
	
	    logger.info("New student enrolled: " + studentId + ", " +  getStudent(studentId).getFullName());
	
	    return studentId;	
	}
	
	@Override
	public int activate(String title, String teacher){
		
		int courseId = super.activate(title, teacher);
		
		logger.info("New course activated: " + courseId + "," + getCourse(courseId));
		
		return courseId;
	}
	
	@Override
	public void register(int studentID, int courseCode){
		super.register(studentID, courseCode);
		
		logger.info("Student " + studentID + "signed up for course "+ courseCode);
	}
	//a student taking an exam "Student 10001 took an exam in course 12 with grade 27"
	
	
	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentId, int courseID, int grade) {
		
		Student tmp;
		
		tmp = University.getStudent(studentId);
		tmp.setGrades(grade,courseID);
		logger.info("Student " + studentId + " took an exam in course " + courseID + " with grade" + grade);
		
	}

	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		
		Student tmp;
		boolean flag = false;
		int i = 0;
	
		float avg_grade = 0;
		
		tmp = University.getStudent(studentId);
		while( i < MAX_COURSE && flag == false ) {
			
			if(tmp.getGrades(i) != -1) {
				flag = true;
			}
			i++;
		}
		
		if(flag == true) {
			
			avg_grade = tmp.studentAverageS();
			return "Student " + studentId + " : "+ avg_grade;
			
		} else {
			 return "Student " + studentId + " hasn't taken any exams";
		}
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		
		Course tmp;
		boolean flag = false;
		int i = 0;
		
		float avg_grade = 0;
		
		tmp = University.getCourse(courseId);
		
		while( i < MAX_STUDENTS && flag == false ) {
			
			if(tmp.getStudents(i) != null && tmp.getStudents(i).getGrades(courseId - FIRST_COURSE) != -1) {
				flag = true;
			}
			i++;
		}
		
		if(flag == true) {
			
			avg_grade = tmp.courseAverage(courseId);
			
			return "The average for the course " + tmp.getCourseName() + "is: "+ avg_grade;
			
		} else {
			 return "No student has taken the exam in " +  tmp.getCourseName();
		}
	}
	
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10.
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info of the best three students.
	 */
	public String topThreeStudents() {
		
		Student [] tmp;
		boolean flag;
		float [] bestAverage = {-1,-1,-1};
		Student [] best;
		Student tempo;
		String line = "";
		float currentAverage = 0;
		float temporary;
		
		best = new Student[3];
		
		int i, j;
		
		tmp = University.getStudents();
		
		for(j = 0; j < tmp.length; j++) { //student
			
			if(tmp[j] != null) {
				
				currentAverage = 0;
				flag = false;
				i = 0;
				
				while( i < MAX_COURSE && flag == false ) { //course
			
						if(tmp[j].getGrades(i) != -1) {
							flag = true;
						}
						i++;
				}		
				if(flag == true) {
					
					currentAverage = tmp[j].studentAverageS() + tmp[j].addBonus();
					
					//sort descending + arranging
					
					if( currentAverage > bestAverage[2]) {
							
							bestAverage[2] = currentAverage;
							best[2] = tmp[j];
							
							//bubble sorting
							
							for (int c = 0 ; c < 3 - 1; c++)
							  {
							    for (int d = 0 ; d < 3 - c - 1; d++)
							    {
							      if (bestAverage[d] < bestAverage[d+1]) /* For decreasing order use '<' instead of '>' */
							      {
							        temporary       = bestAverage[d];
							        bestAverage[d]   = bestAverage[d+1];
							        bestAverage[d+1] = temporary;
							        
							        tempo = best[d];
							        best[d] = best [d+1];
							        best[d+1] = tempo;
							        
							      }
							    }
							  }			
							}
					
						}	
			}
		}
		
		for(i = 0; i < 3 && best[i] != null; ++i) {
			
			line += best[i].getFullName() + " : "+ bestAverage[i] + "\n";
		}
		return line;
	}
	
	
}
