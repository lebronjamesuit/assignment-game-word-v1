package guessword;

import guessword.factories.input.AutomaticInput;
import guessword.factories.input.InputHandler;

import java.io.IOException;

public class MainClass {

    public static void main(String... args) throws IOException {
        AppStarter appStarter = new AppStarter();
        String word = appStarter.getRandomWordToPlay();
        final int MAX_LIVES = 8;

        // If users would like to input from console then
        //  InputHandler inputHandler  = new HumanInput();

        // Automatic guessing
        InputHandler inputHandler = new AutomaticInput();
        appStarter.startTheGame(word, inputHandler, MAX_LIVES);
    }

}
