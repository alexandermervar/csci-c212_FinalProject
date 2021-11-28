package edu.iu.c212.utils.http;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.StringReader;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class HttpUtils {
    private static final Gson gson = new Gson();

    public static String getRandomHangmanWord() throws IOException {
        try {
            return gson.fromJson(Jsoup.connect("https://random-word-api.herokuapp.com/word?number=1").ignoreContentType(true).get().text(), String[].class)[0].toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to get hangman word due to exception.");
            throw e;
        }
    }

    public static List<TriviaQuestion> getTriviaQuestions(int numberOfQuestionsToRetrieve) throws IOException {
        try {
            String json = Jsoup.connect("https://opentdb.com/api.php?difficulty=easy&amount=" + numberOfQuestionsToRetrieve + "&type=multiple&encode=base64").ignoreContentType(true).get().text();
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(true);

            TriviaResponse response = gson.fromJson(reader, TriviaResponse.class);
            if (response.getResponseCode() != 0)
                throw new IllegalStateException("Trivia response code was not 0! It was " + response.getResponseCode());
            else {
                response.getTriviaQuestions().forEach(question -> {
                    question.setQuestion(new String(Base64.getDecoder().decode(question.getQuestion())));
                    question.setCorrectAnswer(new String(Base64.getDecoder().decode(question.getCorrectAnswer())));
                    question.setIncorrectAnswers(question.getIncorrectAnswers().stream().map(incorrectAnswer -> new String(Base64.getDecoder().decode(incorrectAnswer))).collect(Collectors.toList()));
                });
                return response.getTriviaQuestions();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to get hangman word due to exception.");
            throw e;
        }
    }
}
