package it.polito.oop.elective;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Manages elective courses enrollment.
 * 
 *
 */
public class ElectiveManager {
	
	private Map<String,Student> students = new HashMap<>();
	private TreeMap<String,Course> coursesR = new TreeMap<>();
	private List<Notifier> notifications = new ArrayList<>();
	
	

    /**
     * Define a new course offer.
     * A course is characterized by a name and a number of available positions.
     * 
     * @param name : the label for the request type
     * @param availablePositions : the number of available positions
     */
    public void addCourse(String name, int availablePositions) {
        
    	Course element = new Course(name, availablePositions);
    	coursesR.put(name, element);
    	
    }
    
    /**
     * Returns a list of all defined courses
     * @return
     */
    public SortedSet<String> getCourses(){
    	
    	
        return coursesR.navigableKeySet();
        }
    
    /**
     * Adds a new student info.
     * 
     * @param id : the id of the student
     * @param gradeAverage : the grade average
     */
    public void loadStudent(String id, 
                                  double gradeAverage){
    	
    	if(!students.containsKey(id)) {
    		
    		Student element = new Student (id, gradeAverage);
    		students.put(id, element);
    	}else {
    		students.get(id).setGradeAverage(gradeAverage);
    	}
        
    }

    /**
     * Lists all the students.
     * 
     * @return : list of students ids.
     */
    public Collection<String> getStudents(){
    	
    
        return students.keySet();
    }
    
    /**
     * Lists all the students with grade average in the interval.
     * 
     * @param inf : lower bound of the interval (inclusive)
     * @param sup : upper bound of the interval (inclusive)
     * @return : list of students ids.
     */
    public Collection<String> getStudents(double inf, double sup){
    	
    	return students.values().stream().filter(x->(x.getGradeAverage()>= inf && x.getGradeAverage()<= sup)).map(x->x.getId()).
    			collect(Collectors.toList());

    }


    /**
     * Adds a new enrollment request of a student for a set of courses.
     * <p>
     * The request accepts a list of course names listed in order of priority.
     * The first in the list is the preferred one, i.e. the student's first choice.
     * 
     * @param id : the id of the student
     * @param selectedCourses : a list of of requested courses, in order of decreasing priority
     * 
     * @return : number of courses the user expressed a preference for
     * 
     * @throws ElectiveException : if the number of selected course is not in [1,3] or the id has not been defined.
     */
    public int requestEnroll(String id, List<String> courses)  throws ElectiveException {
    	
    	if(!students.containsKey(id)) throw new ElectiveException();
    	
    	for(Notifier tmp: this.notifications) {
    		tmp.requestReceived(id);
    	}
    	
    	List<Course> preferences = new LinkedList<>();
    	for(String tmp: courses) {
    		
    		if(!courses.contains(tmp)) throw new ElectiveException();
    		else {
    			preferences.add(coursesR.get(tmp));
    			
    			
    		}
    	}
    	
    	if(preferences.size()<1 || preferences.size()>3) throw new ElectiveException();
    	
    	students.get(id).setPreferences(preferences);
    	
    	 
    	for(int i = 0; i<preferences.size(); ++i ) {
    		
    	preferences.get(i).
    	getPreferences().
    	replace(i+1, preferences.get(i).getPreferences().get(i+1)+1);
    		
    	}
    	
        return preferences.size();
    }
    
    /**
     * Returns the number of students that selected each course.
     * <p>
     * Since each course can be selected as 1st, 2nd, or 3rd choice,
     * the method reports three numbers corresponding to the
     * number of students that selected the course as i-th choice. 
     * <p>
     * In case of a course with no requests at all
     * the method reports three zeros.
     * <p>
     * 
     * @return the map of list of number of requests per course
     */
    public Map<String,List<Long>> numberRequests(){
    	
    	
    	return coursesR.values().stream().collect(
    			Collectors.toMap(Course::getName, x->x.getPreferences().values().stream().collect(Collectors.toList()) ));
   
    }
    
    
    /**
     * Make the definitive class assignments based on the grade averages and preferences.
     * <p>
     * Student with higher grade averages are assigned to first option courses while they fit
     * otherwise they are assigned to second and then third option courses.
     * <p>
     *  
     * @return the number of students that could not be assigned to one of the selected courses.
     */
    public long makeClasses() {
    	
    List<Student> studentsOrd = students.values().stream().
    sorted(Comparator.comparing(Student::getGradeAverage).reversed()).collect(Collectors.toList());
    	for(Student tmp: studentsOrd ) {
    		
    		
    		for(int i = 0; i< tmp.getPreferences().size(); ++i) {
    			if(tmp.getPreferences().get(i).getAvailablePositions() != tmp.getPreferences().get(i).getSub().size()) {
    				tmp.setAssigned(true);
    				tmp.setChoose(i+1);
    				tmp.getPreferences().get(i).getSub().add(tmp);
    				tmp.setAttending(tmp.getPreferences().get(i));
    				
    				for(Notifier tmpN: this.notifications) {
        	    		tmpN.assignedToCourse(tmp.getId(), tmp.getPreferences().get(i).getName());
        	    	}
    				break;
    			}
    			
    		}
    		
    	}
        return students.values().stream().filter(x->x.getAssigned()== false).count();
    }
    
    
    /**
     * Returns the students assigned to each course.
     * 
     * @return the map course name vs. student id list.
     */
    public Map<String,List<String>> getAssignments(){
    	
    	return coursesR.values().stream().collect(Collectors.toMap(
    			Course::getName
    			,
    			Course::getsubString
    			));
    }
    
    
    /**
     * Adds a new notification listener for the announcements
     * issues by this course manager.
     * 
     * @param listener : the new notification listener
     */
    public void addNotifier(Notifier listener) {
    	this.notifications.add(listener);
        
    }
    
    /**
     * Computes the success rate w.r.t. to first 
     * (second, third) choice.
     * 
     * @param choice : the number of choice to consider.
     * @return the success rate (number between 0.0 and 1.0)
     */
    public double successRate(int choice){
    	
    
    	long numerator =students.values().stream().filter(x->x.getAssigned()== true).filter(x->(x.getChoose()) == choice).count();
    	long denominator = students.values().stream().count();
    	double result = (double) numerator/denominator;

        return result;
    }

    
    /**
     * Returns the students not assigned to any course.
     * 
     * @return the student id list.
     */
    public List<String> getNotAssigned(){
        return students.values().stream().filter(x->x.getAssigned()== false).map(x->x.getId()).collect(Collectors.toList());
    }
    
    
}
