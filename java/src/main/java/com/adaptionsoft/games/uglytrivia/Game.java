package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	public static final int NUMBER_OF_CELLS = 12;
	ArrayList<String> players = new ArrayList();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    
    int currentPlayerIndex = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}

	public void add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());

	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(currentPlayerName() + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayerIndex]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(currentPlayerName() + " is getting out of the penalty box");
				places[currentPlayerIndex] = places[currentPlayerIndex] + roll;
				if (places[currentPlayerIndex] >= NUMBER_OF_CELLS) places[currentPlayerIndex] = places[currentPlayerIndex] - NUMBER_OF_CELLS;
				
				System.out.println(currentPlayerName()
						+ "'s new location is " 
						+ places[currentPlayerIndex]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(currentPlayerName() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayerIndex] = places[currentPlayerIndex] + roll;
			if (places[currentPlayerIndex] >= NUMBER_OF_CELLS) places[currentPlayerIndex] = places[currentPlayerIndex] - NUMBER_OF_CELLS;
			
			System.out.println(currentPlayerName()
					+ "'s new location is " 
					+ places[currentPlayerIndex]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory() == Category.POP)
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == Category.SCIENCE)
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == Category.SPORTS)
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == Category.ROCK)
			System.out.println(rockQuestions.removeFirst());		
	}
	
	
	private Category currentCategory() {
		if (places[currentPlayerIndex] == 0) return Category.POP;
		if (places[currentPlayerIndex] == 4) return Category.POP;
		if (places[currentPlayerIndex] == 8) return Category.POP;
		if (places[currentPlayerIndex] == 1) return Category.SCIENCE;
		if (places[currentPlayerIndex] == 5) return Category.SCIENCE;
		if (places[currentPlayerIndex] == 9) return Category.SCIENCE;
		if (places[currentPlayerIndex] == 2) return Category.SPORTS;
		if (places[currentPlayerIndex] == 6) return Category.SPORTS;
		if (places[currentPlayerIndex] == 10) return Category.SPORTS;
		return Category.ROCK;
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayerIndex]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayerIndex]++;
				System.out.println(currentPlayerName()
						+ " now has "
						+ purses[currentPlayerIndex]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayerIndex++;
				if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
				
				return winner;
			} else {
				currentPlayerIndex++;
				if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayerIndex]++;
			System.out.println(currentPlayerName()
					+ " now has "
					+ purses[currentPlayerIndex]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayerIndex++;
			if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
			
			return winner;
		}
	}

	private String currentPlayerName() {
		return players.get(currentPlayerIndex);
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(currentPlayerName() + " was sent to the penalty box");
		inPenaltyBox[currentPlayerIndex] = true;
		
		currentPlayerIndex++;
		if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayerIndex] == 6);
	}
}
