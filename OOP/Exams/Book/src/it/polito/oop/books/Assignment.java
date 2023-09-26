package it.polito.oop.books;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Assignment {

	private String ID;
	private ExerciseChapter exerciseChapter;
	private Map<Question, List<String>> answers = new HashMap<>();
	private List<Double> results = new ArrayList<>();
	

    public Assignment(String iD, ExerciseChapter exerciseChapter) {
		// TODO Auto-generated constructor stub
    	this.ID = iD;
    	this.exerciseChapter = exerciseChapter;
	}

	public String getID() {
        return ID;
    }

    public ExerciseChapter getChapter() {
        return exerciseChapter;
    }

    public double addResponse(Question q,List<String> answers) {
    	
    	
    	this.answers.put(q, answers);
    	int counter = this.answers.values().size();
    	long wrongs = answers.stream().
    			filter(x-> 
    			!q.getAnswers().entrySet().stream().filter(y-> (y.getValue()==true))
    			.map(c->c.getKey()).collect(Collectors.toList())
    			.contains(x)).count();
    			
    	long corrNoprovided	= q.getAnswers().entrySet().stream().filter(y-> (y.getValue()==true)).
    			filter(x->!answers.contains(x.getKey())).count();
    	
    	double result = (counter - wrongs - corrNoprovided)/counter;
    	results.add(result);
    	 return result;
    	
    	//long corrNoprovided = answers.keySet().stream().map(x->x.getAnswers()).flatMap(x->x.entrySet().stream())
    	//		.filter(x->x.getValue()== true).filter(x-> !answers.contains(x.getKey())).collect(Collectors.counting());
    }
    
    public double totalScore() {
        return results.stream().mapToDouble(x->x).sum();
    }

}
