package guessword.factories.input;

import java.util.Random;

// This is how we might extend to another AutomaticInput
public class AutomaticInput implements InputHandler {
    @Override
    public char getUserInput() {
        Random random = new Random();
        return (char) (random.nextInt(26) + 'a');
    }
}
