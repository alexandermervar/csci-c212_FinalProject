package edu.iu.c212.places.games;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;
import edu.iu.c212.utils.http.*;

public class TriviaGame extends Game{

    List<TriviaQuestion> questions;
    private int currentQuestionIndex;
    private int score;

    @Override
    public void onEnter(User user) {
        setPrize(2.0);
        setEntryFee(0.0);

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


            int userGuess = Integer.parseInt(ConsoleUtils.printMenuToConsole(question.getQuestion(), answers, true));

            if (answers.get(userGuess - 1).equals(question.getCorrectAnswer())) {
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

        return;
    
    }
}
