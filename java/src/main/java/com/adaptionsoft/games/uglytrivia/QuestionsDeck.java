package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.LinkedList;

import static com.adaptionsoft.games.uglytrivia.Category.values;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.IntStream.range;

public class QuestionsDeck {

    private static final int QUESTIONS_PER_CATEGORY = 50;
    private final HashMap<Category, LinkedList<String>> questionsForCategory;

    public QuestionsDeck() {
        questionsForCategory = new HashMap<>();
        for (Category category : values()) {
            questionsForCategory.put(category, makeQuestionsFor(category));
        }
    }

    public String pickQuestionFor(Category category) {
        LinkedList<String> currentQuestions = questionsForCategory.get(category);
        return currentQuestions.removeFirst();
    }

    private LinkedList<String> makeQuestionsFor(Category category) {
        return range(0, QUESTIONS_PER_CATEGORY).boxed()
                .map(questionNumber -> category + " Question " + questionNumber)
                .collect(toCollection(LinkedList::new));
    }
}
