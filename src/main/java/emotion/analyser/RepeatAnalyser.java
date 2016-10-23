package emotion.analyser;

import emotion.model.Context;

import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import java.io.File;


/**
 * Created by llc on 16/10/21.
 */
public class RepeatAnalyser extends BaseAnalyser{
    static class Threshold{
        static int singleRepeatTimes = 3;
        static int prevRepeatTimes = 2;
    }
    static class Utils{
        static List<String> ignoreWords;

        static {
            URL url = Utils.class.getResource("/conf/ignore.txt");
            System.out.println(url.getFile());
            File file = FileUtils.getFile(url.getPath());
            try{
                ignoreWords = FileUtils.readLines(file);
            }catch (Exception e){
                e.printStackTrace();
            }
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
                if (Utils.ignoreWords.contains(ngram)) continue;
                if (!single){
                    Pattern p = Pattern.compile(ngram);
                    Matcher m = p.matcher(sentence); // 获取 matcher 对象
                    int count = 0;

                    while(m.find()) {
                        count++;
                    }

                    if (count >= Threshold.singleRepeatTimes) {
                        single = true;
                    }
                }
                int times = 0;
                int prevNum = context.getUserSentences().size();
                for (int i = 0; i < prevNum - 1; ++i){
                    if (context.getUserSentences().get(i).contains(ngram)
                            && !context.getXiaoleSentences().get(i).contains(ngram)){
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
        List<String> list = Utils.ignoreWords;
        for(String str: list){
            System.out.println(str);
        }
    }
}
