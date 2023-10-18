package guessword.factories.input;

import java.util.Random;

// This is how we might extend to another HardInput
public class HardInput implements InputHandler {

    @Override
    public char getUserInput() {
        // Hard code to guess apple
        char[] ch = {'a', 'p', 'l', 'e'};
        Random random = new Random();
        random.nextInt(4);
        return ch[random.nextInt(4)];
    }

}
