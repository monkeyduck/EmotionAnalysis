package emotion.analyser;

import emotion.model.Context;
import emotion.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by llc on 16/10/21.
 */
public class RepeatAnalyser extends BaseAnalyser{
    private static List<String> ignoreWords;
    private static int singleRepeatTimes = 3;

    static {
        InputStream inputStream = RepeatAnalyser.class.getResourceAsStream("/conf/ignore.txt");
        try {
            ignoreWords = Utils.readLines(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int calRepeatScores(Context context){
        String sentence = context.getUserCurrentSentence();
        if (sentence == null || "".equals(sentence)) return 0;

        sentence = sentence.trim();
        int maxNgramLen = 5;

        boolean single = false;
        int score = 0;

        for (int k = maxNgramLen; k >= 2; --k) {
            for (int start = 0;  start  + k <= sentence.length(); ++start) {
                String ngram = sentence.substring(start, start + k);
                ngram = ngram.replaceAll("\\+", "");
                ngram = ngram.replaceAll("\\?", "");
                ngram = ngram.replaceAll("\\.", "");
                if (ignoreWords.contains(ngram)) continue;
                if (!single){
                    Pattern p = Pattern.compile(ngram);
                    Matcher m = p.matcher(sentence); // 获取 matcher 对象
                    int count = 0;

                    while(m.find()) {
                        count++;
                    }

                    if (count >= singleRepeatTimes) {
                        single = true;
                    }
                }
                int times = 0;
                int prevNum = context.getUserSentences().size();
                for (int i = 0; i < prevNum - 1; ++i){
                    if (context.getUserSentences().get(i).contains(ngram)) {
                        times++;
                    }
                }
                score = Math.max(score, times);
            }
        }
        return score + (single ? 1 : 0);
    }

    public int analyse(Context context){
        int score = 0;
        score += calRepeatScores(context);
        return score;
    }
    public static void main(String agrs[]){
        List<String> list = ignoreWords;
        for(String str: list){
            System.out.println(str);
        }
    }
}
