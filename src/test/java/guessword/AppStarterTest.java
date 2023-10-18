package guessword;

import guessword.factories.input.HardInput;
import guessword.factories.input.InputHandler;
import guessword.factories.input.AutomaticInput;
import guessword.factories.wordsource.DefaultAbstractWordSourceFactory;
import guessword.factories.wordsource.AbstractWordSource;
import guessword.wordselection.WordRandom;
import guessword.wordselection.WordRandomProcessor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author James
 * @Since 10.2023
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppStarterTest {

    AbstractWordSource abstractWordSource;
    WordRandom wordRandom;
    List<String> wordList;
    AppStarter app;
    InputHandler inputHandler;

    @BeforeAll
    public void setup() {
        abstractWordSource = new DefaultAbstractWordSourceFactory();
        wordList = abstractWordSource.getListWordInstance();
        wordRandom = new WordRandomProcessor();
        app = new AppStarter();
    }

    @Test
    public void shouldReturnTrue_WhenRandomWord_InTheSourceList() {
        // When
        String word1 = wordRandom.selectRandomWord(wordList);
        String word2 = wordRandom.selectRandomWord(wordList);
        String word3 = wordRandom.selectRandomWord(wordList);
        String word4 = wordRandom.selectRandomWord(wordList);

        // Then
        assertTrue(wordList.contains(word1));
        assertTrue(wordList.contains(word2));
        assertTrue(wordList.contains(word3));
        assertTrue(wordList.contains(word4));

    }

    @Test
    public void shouldReturnTrue_WhenCallGetRandomWordToPlayInSourceOfList() {
        // Random function
        String word1 = app.getRandomWordToPlay();
        String word2 = app.getRandomWordToPlay();

        // When
        String word3 = wordRandom.selectRandomWord(wordList);
        String word4 = wordRandom.selectRandomWord(wordList);

        // Then
        assertTrue(wordList.contains(word1));
        assertTrue(wordList.contains(word2));
        assertTrue(wordList.contains(word3));
        assertTrue(wordList.contains(word4));
    }


    @Test
    public void shouldReturnAsteriskCharArray_WhenGivenRandomWord() throws IOException {
        String theWord = "appleppppppppp";

        // When
        char[] ch = app.makeUpAsteriskByWord(theWord);

        // Then
        assertTrue(ch.length == theWord.length());
        for (char c : ch) {
            assertEquals('*', c);
        }
    }

    @Test
    public void shouldReturnSize2_WhenGuessCorrectChar() {
        String word = "apple";

        // When
        ArrayList<Integer> indexesFound = app.createArrayOfFoundCharIndexes(word, 'p');

        // Then
        assertEquals(2, indexesFound.size());
        assertEquals(1, indexesFound.get(0));
        assertEquals(2, indexesFound.get(1));
    }

    @Test
    public void shouldReturnSize0_WhenGuessInCorrectChar() {
        // Given
        String word = "apple";

        // When
        ArrayList<Integer> indexesFound = app.createArrayOfFoundCharIndexes(word, 'x');

        // Then
        assertEquals(0, indexesFound.size());

    }

    @Test
    public void shouldRevealedRealChars_WhenGuessCorrect() {
        // Given displayCharacters ***** for apple
        String word = "apple";
        char guessedChar = 'p';
        ArrayList<Integer> indexesFound = app.createArrayOfFoundCharIndexes(word, guessedChar);
        char[] displayCharacters = app.makeUpAsteriskByWord(word);

        // When
        app.updateDisplayCharacters(indexesFound, displayCharacters, guessedChar);

        // Then *pp***
        assertEquals('*', displayCharacters[0]);
        assertEquals('p', displayCharacters[1]);
        assertEquals('p', displayCharacters[2]);
        assertEquals('*', displayCharacters[3]);
        assertEquals('*', displayCharacters[4]);
    }

    @Test
    public void shouldRevealedNothing_WhenGuessWrong() {
        // Given displayCharacters ***** for apple
        String word = "apple";
        char guessedChar = 'x';
        ArrayList<Integer> indexesFound = app.createArrayOfFoundCharIndexes(word, guessedChar);
        char[] displayCharacters = app.makeUpAsteriskByWord(word);

        // When
        app.updateDisplayCharacters(indexesFound, displayCharacters, guessedChar);

        // Then *pp***
        assertEquals('*', displayCharacters[0]);
        assertEquals('*', displayCharacters[1]);
        assertEquals('*', displayCharacters[2]);
        assertEquals('*', displayCharacters[3]);
        assertEquals('*', displayCharacters[4]);
    }

    @Test
    public void shouldReturnFalse_WhenGivenTooLongWord_ToGuess() throws IOException {
        // Max lives are 8, and the word too long for guessing 8 time.
        String theWord = "thewordtoolongforguessing";
        final int MAX_LIVES = 5;
        // Dependency Injection
        inputHandler = new AutomaticInput();
        assertFalse(app.startTheGame(theWord, inputHandler, MAX_LIVES));
    }

    @Test
    public void shouldReturnTrue_WhenGivenGuessing_100_Times() throws IOException {
        // Given Dependency Injection AutomaticInput
        String theWord = "apple";
        final int MAX_LIVES = 100;
        inputHandler = new AutomaticInput();

        // When and Then
        assertTrue(app.startTheGame(theWord, inputHandler, MAX_LIVES));
    }

    @Test
    public void shouldReturnTrue_WhenGuessingApple_HardInput() throws IOException {
        // Given  Dependency Injection AutomaticInput
        String theWord = "apple";
        final int MAX_LIVES = 30;

        inputHandler = new HardInput();

        // When and Then
        assertTrue(app.startTheGame(theWord, inputHandler, MAX_LIVES));
    }

}
