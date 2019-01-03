package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.LinkedList;

import static com.adaptionsoft.games.uglytrivia.Category.*;
import static com.adaptionsoft.games.uglytrivia.Category.ROCK;

public class QuestionsDeck {

    private final HashMap<Category, LinkedList<String>> questionsForCategory;

    public QuestionsDeck() {
        LinkedList<String> popQuestions = new LinkedList<>();
        LinkedList<String> scienceQuestions = new LinkedList<>();
        LinkedList<String> sportsQuestions = new LinkedList<>();
        LinkedList<String> rockQuestions = new LinkedList<>();

        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }

        questionsForCategory = new HashMap<>();
        questionsForCategory.put(POP, popQuestions);
        questionsForCategory.put(SCIENCE, scienceQuestions);
        questionsForCategory.put(SPORTS, sportsQuestions);
        questionsForCategory.put(ROCK, rockQuestions);
    }

    public String pickQuestionFor(int position) {
        LinkedList<String> currentQuestions = questionsForCategory.get(categoryFor(position));
        return currentQuestions.removeFirst();
    }
}
