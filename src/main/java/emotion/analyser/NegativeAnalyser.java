package emotion.analyser;

import emotion.model.Context;
import emotion.utils.Utils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by llc on 16/10/21.
 */
public class NegativeAnalyser extends BaseAnalyser{
    private static List<String> negativeWords = new ArrayList<String>();

    public NegativeAnalyser() {
        InputStream inputStream = getClass().getResourceAsStream("/conf/negative.txt");
        try{
            negativeWords = Utils.readLines(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int analyse(Context context){
        String cur = context.getUserCurrentSentence();
        boolean curNeg = false;
        boolean prevNeg = false;
        for (String word: negativeWords){
            Pattern p = Pattern.compile(word);
            Matcher m = p.matcher(cur);
            if (m.find()) curNeg = true;
            int prevNum = context.getUserSentences().size();
            for (int i = 0; i < prevNum - 1; ++i){
                m = p.matcher(context.getUserSentences().get(i));
                if (m.find()){
                    prevNeg = true;
                    break;
                }
            }
        }
        return (curNeg ? 2:0) + (prevNeg ? 1:0);
    }
}
