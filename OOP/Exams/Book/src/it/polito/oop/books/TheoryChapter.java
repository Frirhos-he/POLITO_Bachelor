package it.polito.oop.books;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class TheoryChapter {
	
	private String title;
	private Integer numPages;
	private String text;
	private Map<String, Topic> topics = new HashMap<>();


    public TheoryChapter(String title, int numPages, String text) {
		// TODO Auto-generated constructor stub
    	this.title = title;
    	this.numPages = numPages;
    	this.text = text;
    	
	}

	public String getText() {
		return text;
	}

    public void setText(String newText) {
    	this.text = newText;
    }


	public List<Topic> getTopics() {
		List<Topic> tmp =  List.copyOf(this.topics.values());
        return tmp.stream().distinct().sorted(Comparator.comparing(Topic::getKeyword)).collect(Collectors.toList());
	}

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
    
    public void addTopic(Topic topic) {
    	
    	this.topics.put(topic.getKeyword(), topic);
    	
    	for(Topic tmp: topic.getSubTopics()) {
    		this.topics.put(tmp.getKeyword(), tmp);
    	}
    }
    
}
