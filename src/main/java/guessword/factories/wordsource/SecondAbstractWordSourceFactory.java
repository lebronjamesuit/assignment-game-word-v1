package guessword.factories.wordsource;

import java.util.Collections;
import java.util.List;

// This is how we can extend to another source of words
public class SecondAbstractWordSourceFactory extends AbstractWordSource {

    public SecondAbstractWordSourceFactory() {
        listWords.add("adventurous");
        listWords.add("courageous");
        listWords.add("ambitious");
        listWords.add("Charming");
        listWords.add("Compassion");
    }

    public List<String> getListWordInstance() {
        return Collections.unmodifiableList(listWords);
    }
}
