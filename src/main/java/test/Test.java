package test;

import emotion.analyser.Analyser;
import emotion.model.Context;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by llc on 16/10/23.
 */
public class Test {
    public static void main(String[] args){
        Test test = new Test();
        URL url = test.getClass().getResource("/conf/labeled.txt");
        File file = FileUtils.getFile(url.getPath());
        List<String> input = new ArrayList<String>();
        try{
            input = FileUtils.readLines(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        Analyser analyser = new Analyser();
        for (int i = 0; i < input.size(); ++i){
            int start = i > 4 ? i - 4 : 0;
            List<String> userWords = new ArrayList<String>();
            List<String> xiaoleWords = new ArrayList<String>();
            for (int j = start; j <= i; ++j){
                String str = input.get(j);
                xiaoleWords.add(str.split("\\t")[4]);
                userWords.add(str.split("\\t")[5]);
            }
            Context context = new Context(userWords, xiaoleWords);
            if (context.getUserCurrentSentence().equals("睡觉吧"))
                System.out.println("pause");
            double score = analyser.analyse(context);
            System.out.println(context.getXiaoLeCurrentSentence()+"\t\t"+context.getUserCurrentSentence());
            if (score > 0){
                System.out.println(score);
            }
        }
    }
}
