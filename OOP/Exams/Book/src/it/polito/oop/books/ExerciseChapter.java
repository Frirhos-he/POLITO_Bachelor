package it.polito.oop.books;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ExerciseChapter {

	private String title;
	private Integer numPages;
	private Map<String, Topic> mainTopicQ = new HashMap<>();
	private Map<String, Question> questions = new HashMap<>();
	
    public ExerciseChapter(String title, int numPages) {
		// TODO Auto-generated constructor stub
    	this.title = title;
    	this.numPages = numPages;
    	
	}


	public List<Topic> getTopics() {
		List<Topic> tmp =  List.copyOf(this.mainTopicQ.values());
        return tmp.stream().distinct().sorted(Comparator.comparing(Topic::getKeyword)).collect(Collectors.toList());
	};
	

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
    	this.title = newTitle;
    }

    public int getNumPages() {
        return this.numPages;
    }
    
    public void setNumPages(int newPages) {
    	this.numPages = newPages;
    	
    }
    

	public void addQuestion(Question question) {
		questions.put(question.getQuestion(), question);
		this.mainTopicQ.put(question.getMainTopic().getKeyword(), question.getMainTopic());
	}


	public Map<String, Topic> getMainTopicQ() {
		return mainTopicQ;
	}


	public void setMainTopicQ(Map<String, Topic> mainTopicQ) {
		this.mainTopicQ = mainTopicQ;
	}


	public Map<String, Question> getQuestions() {
		return questions;
	}


	public void setQuestions(Map<String, Question> questions) {
		this.questions = questions;
	}


	public void setNumPages(Integer numPages) {
		this.numPages = numPages;
	}	
}
