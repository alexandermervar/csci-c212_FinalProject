package edu.iu.c212.utils.http;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TriviaResponse{

	@SerializedName("response_code")
	private int responseCode;

	@SerializedName("results")
	private List<TriviaQuestion> triviaQuestions;

	public int getResponseCode(){
		return responseCode;
	}

	public List<TriviaQuestion> getTriviaQuestions(){
		return triviaQuestions;
	}
}