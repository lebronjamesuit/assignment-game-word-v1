package guessword.factories.wordsource;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWordSource {

    protected final List<String> listWords = new ArrayList<>();

    public abstract List<String> getListWordInstance();


}
