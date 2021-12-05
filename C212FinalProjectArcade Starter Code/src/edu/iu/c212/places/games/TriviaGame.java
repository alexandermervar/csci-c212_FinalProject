package edu.iu.c212.places.games;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Places;
import edu.iu.c212.utils.ConsoleUtils;
import edu.iu.c212.utils.http.*;

public class TriviaGame extends Game{

    List<TriviaQuestion> questions;
    private int currentQuestionIndex;
    private int score;

    public TriviaGame(String placeName) {
        setPlaceName(placeName);
        setEntryFee(0.0);
        setIsGame(true);
        setPrize(2.0);
    }

    @Override
    public void onEnter(User user) {

        // Retrieve Questions
        try {
            List<TriviaQuestion> questions = HttpUtils.getTriviaQuestions(5);
        } catch (IOException e) {
            System.out.println("Unable to get trivia question due to exception.");
            e.printStackTrace();
        }

        // Shuffle the questions List
        Collections.shuffle(questions);

        System.out.println("Welcome to C212 trivia. You get $2 for every correct answer - there are 5 total questions in this trivia round.");

        for (TriviaQuestion question : questions) {
            System.out.println("You're on question " + (currentQuestionIndex + 1) + ". Ready?");
            // sleep for 1 second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<String> answers = question.getIncorrectAnswers();
            answers.add(question.getCorrectAnswer());

            // Shuffle the answers List
            Collections.shuffle(answers);

            // Fixed bug
            String userGuess = ConsoleUtils.printMenuToConsole(question.getQuestion(), answers, true);

            if (userGuess.equals(question.getCorrectAnswer())) {
                score++;
                System.out.println("You got it right! You got $2.");
                user.setBalance(user.getBalance() + getPrize());
                saveSingularUser(user);
            } else {
                System.out.println("You got it wrong :( The correct answer is " + question.getCorrectAnswer());
            }

            currentQuestionIndex++;
        }

        if (score >= 3) {
            System.out.println("Nice! You got " + score + " questions right.");
        } else {
            System.out.println("Aww, good try. You got " + score + " questions right.");
        }

        getArcade().transitionArcadeState(Places.LOBBY);
    
    }
}
