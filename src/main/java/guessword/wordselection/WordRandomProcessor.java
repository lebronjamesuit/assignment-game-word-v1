package guessword.wordselection;

import java.util.List;
import java.util.Random;

public class WordRandomProcessor implements WordRandom {

    public String selectRandomWord(List<String> listWords) {
        int size = listWords.size();
        Random random = new Random();
        return listWords.get(random.nextInt(size));
    }
}
