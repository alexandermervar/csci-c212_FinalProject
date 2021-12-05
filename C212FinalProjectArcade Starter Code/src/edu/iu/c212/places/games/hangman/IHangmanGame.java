package edu.iu.c212.places.games.hangman;

import java.util.List;

public interface IHangmanGame {

    String getBlurredWord(List<Character> guess, String word);

    List<Character> getValidLexicon();
    
}
