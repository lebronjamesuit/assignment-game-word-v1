package guessword;

import guessword.factories.input.InputHandler;
import guessword.factories.wordsource.AbstractWordSource;
import guessword.factories.wordsource.DefaultAbstractWordSourceFactory;
import guessword.wordselection.WordRandom;
import guessword.wordselection.WordRandomProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class AppStarter {

    private static final char ASTERISK = '*';

    public boolean startTheGame(String word, InputHandler inputHandler, int max_lives) throws IOException {
        int wordLength = word.length();
        char[] displayCharacters = makeUpAsteriskByWord(word);
        int lives = max_lives;
        int correctGuessTimes = 0;
        System.out.println("Guess the word:");
        printCharacters(displayCharacters);

        while (lives > 0 && correctGuessTimes < wordLength) {
            System.out.println(" --- Type a char that you want to guess the word ---");
            char guessedChar = inputHandler.getUserInput();
            ArrayList<Integer> indexesFound = createArrayOfFoundCharIndexes(word, guessedChar);
            if (!indexesFound.isEmpty()) {
                updateDisplayCharacters(indexesFound, displayCharacters, guessedChar);
                printCharacters(displayCharacters);
                correctGuessTimes += indexesFound.size();
            } else {
                lives--;
                System.out.println("Incorrect " + (max_lives - lives) + " life lost. " + lives + " remaining. The current word is");
                printCharacters(displayCharacters);
            }
        }

        if (wordLength == correctGuessTimes) {
            System.out.println(" You won the game");
            return true;
        } else {
            return false;
        }
    }

    public String getRandomWordToPlay() {
        AbstractWordSource abstractWordSource = new DefaultAbstractWordSourceFactory();
        WordRandom wordRandom = new WordRandomProcessor();
        return wordRandom.selectRandomWord(abstractWordSource.getListWordInstance());
    }

    public ArrayList<Integer> createArrayOfFoundCharIndexes(String word, char guessedChar) {
        ArrayList<Integer> indexesFound = new ArrayList<>();
        for (int pos = word.indexOf(guessedChar); pos > -1;
             pos = word.indexOf(guessedChar, pos + 1)) {
            indexesFound.add(pos);
        }
        return indexesFound;
    }

    public char[] makeUpAsteriskByWord(String word) {
        char[] displayCharacters = new char[word.length()];
        for (int i = 0; i < displayCharacters.length; i++) {
            displayCharacters[i] = ASTERISK;
        }
        return displayCharacters;
    }

    public void updateDisplayCharacters(ArrayList<Integer> indexesFound, char[] displayCharacters, char guessedChar) {
        for (int i : indexesFound) {
            displayCharacters[i] = guessedChar;
        }
    }

    private void printCharacters(char[] characters) {
        Stream.of(characters).forEach(System.out::println);
    }

}
