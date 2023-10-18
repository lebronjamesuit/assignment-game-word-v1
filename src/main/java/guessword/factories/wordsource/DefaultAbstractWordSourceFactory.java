package guessword.factories.wordsource;

import java.util.Collections;
import java.util.List;

public class DefaultAbstractWordSourceFactory extends AbstractWordSource {

    public DefaultAbstractWordSourceFactory() {
        listWords.add("apple");
        listWords.add("dog");
        listWords.add("cat");
        listWords.add("book");
        listWords.add("mango");
        listWords.add("banana");
        listWords.add("avocado");
        listWords.add("watermelon");

    }

    public List<String> getListWordInstance() {
        return Collections.unmodifiableList(listWords);
    }
}
