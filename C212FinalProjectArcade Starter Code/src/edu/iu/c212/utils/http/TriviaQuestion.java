package edu.iu.c212.utils.http;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TriviaQuestion {

	@SerializedName("difficulty")
	private String difficulty;

	@SerializedName("question")
	private String question;

	@SerializedName("correct_answer")
	private String correctAnswer;

	@SerializedName("incorrect_answers")
	private List<String> incorrectAnswers;

	@SerializedName("category")
	private String category;

	@SerializedName("type")
	private String type;

	public String getDifficulty(){
		return difficulty;
	}

	public String getQuestion(){
		return question;
	}

	public String getCorrectAnswer(){
		return correctAnswer;
	}

	public List<String> getIncorrectAnswers(){
		return incorrectAnswers;
	}

	public String getCategory(){
		return category;
	}

	public String getType(){
		return type;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public void setIncorrectAnswers(List<String> incorrectAnswers) {
		this.incorrectAnswers = incorrectAnswers;
	}
}