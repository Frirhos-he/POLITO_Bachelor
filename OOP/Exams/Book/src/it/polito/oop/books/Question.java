package it.polito.oop.books;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Question {
	
	private String question;
	private Topic topic;
	private Map<String, Boolean> answers = new HashMap<>();

	
	public Question(String question, Topic topic) {
		// TODO Auto-generated constructor stub
		this.question = question;
		this.topic = topic;
	}

	public String getQuestion() {
		return question;
	}
	
	public Topic getMainTopic() {
		return topic;
	}

	public void addAnswer(String answer, boolean correct) {
		
		this.answers.put(answer, correct);
		
	}
	
    @Override
    public String toString() {
        return question + " (" +topic.getKeyword() +")";
    }

	public long numAnswers() {
	    return this.answers.size();
	}

	public Set<String> getCorrectAnswers() {
		return this.answers.entrySet().stream().filter( x->x.getValue()==true).map(x->x.getKey()).collect(Collectors.toSet());
	}

	public Set<String> getIncorrectAnswers() {
		return this.answers.entrySet().stream().filter( x->x.getValue()==false).map(x->x.getKey()).collect(Collectors.toSet());

	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Map<String, Boolean> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<String, Boolean> answers) {
		this.answers = answers;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
