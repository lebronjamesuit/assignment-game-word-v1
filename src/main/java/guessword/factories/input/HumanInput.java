package guessword.factories.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class HumanInput implements InputHandler {

    // User Input from the command line
    @Override
    public char getUserInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return (char) br.read();
    }
}
