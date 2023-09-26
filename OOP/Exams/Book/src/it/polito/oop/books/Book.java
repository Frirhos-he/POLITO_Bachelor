package it.polito.oop.books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Book {
	
	private Map<String, Topic> topics = new HashMap<>();
	private Map<String, Question> questions = new HashMap<>();
	private Map<String, TheoryChapter > theoryChapters = new HashMap<>();
	private Map<String, ExerciseChapter > exerciseChapters = new HashMap<>();
	private Map<String, Assignment> assigments = new HashMap<>();

    /**
	 * Creates a new topic, if it does not exist yet, or returns a reference to the
	 * corresponding topic.
	 * 
	 * @param keyword the unique keyword of the topic
	 * @return the {@link Topic} associated to the keyword
	 * @throws BookException
	 */
	public Topic getTopic(String keyword) throws BookException {
		if(keyword.isEmpty() == true || keyword.isBlank()  == true) throw new BookException();
		
		Topic element;
		
		if(!topics.containsKey(keyword)) {
			 element = new Topic (keyword);
			topics.put(keyword, element);
		} else {
			 element = topics.get(keyword);
		}
		
	    return element;
	}

	public Question createQuestion(String question, Topic mainTopic) {
		
		Question element = new Question (question, topics.get(mainTopic.getKeyword()));
		questions.put(question, element);
        return element;
	}

	public TheoryChapter createTheoryChapter(String title, int numPages, String text) {
		
		TheoryChapter element = new TheoryChapter (title, numPages, text);
		this.theoryChapters.put(title, element);
		
        return element;
	}

	public ExerciseChapter createExerciseChapter(String title, int numPages) {
		
		ExerciseChapter element = new ExerciseChapter (title, numPages);
		this.exerciseChapters.put(title, element);
		
        return element;
	}

	public List<Topic> getAllTopics() {
		List<Topic> listq = theoryChapters.values().stream().map(TheoryChapter::getTopics).flatMap(x->x.stream())
					.distinct().collect(Collectors.toList());
		List<Topic> lista = this.exerciseChapters.values().stream().map(ExerciseChapter::getTopics).flatMap(x->x.stream())
				.distinct().collect(Collectors.toList());
		List<Topic> liste = new ArrayList<>();
		liste.addAll(listq);
		liste.addAll(lista);
		
        return liste.stream().distinct().collect(Collectors.toList());
	}

	public boolean checkTopics() {
		return this.exerciseChapters.values().stream().map(x->x.getTopics()).flatMap(x->x.stream()).map(x->x.getKeyword())
		.allMatch
			( y-> 
			this.theoryChapters.values().stream().map(x->x.getTopics()).flatMap(x->x.stream())
			.map(x->x.getKeyword()).collect(Collectors.toList()).contains(y));
	}

	public Assignment newAssignment(String ID, ExerciseChapter chapter) {
		
		Assignment element = new Assignment(ID, this.exerciseChapters.get(chapter.getTitle()));
		this.assigments.put(ID, element);
        return element;
	}
	
    /**
     * builds a map having as key the number of answers and 
     * as values the list of questions having that number of answers.
     * @return
     */
    public Map<Long,List<Question>> questionOptions(){
         return exerciseChapters.values().stream()
        		.flatMap(c ->  c.getQuestions().values().stream())
				.collect(Collectors.groupingBy(q -> q.numAnswers(), Collectors.toList()));
    }
}
