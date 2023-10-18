package guessword.factories.input;

import java.io.IOException;

public interface InputHandler {
    char getUserInput() throws IOException;
}
