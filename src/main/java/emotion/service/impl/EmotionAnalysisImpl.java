package emotion.service.impl;

import emotion.analyser.*;
import emotion.model.Context;
import emotion.service.IEmotionAnalysis;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by llc on 16/10/28.
 */
@Service("EmotionAnalysis")
public class EmotionAnalysisImpl implements IEmotionAnalysis{
    private static RepeatAnalyser repeatAnalyser = new RepeatAnalyser();
    private static NegativeAnalyser negativeAnalyser = new NegativeAnalyser();
    private static XiaoleAnalyser xiaoleAnalyser = new XiaoleAnalyser();
    private static CommandAnalyser commandAnalyser = new CommandAnalyser();
    private static StructAnalyser structAnalyser = new StructAnalyser();

    public double analyse(List<String> userSentences){
        Context context = new Context(userSentences);
        int score = 0;
        double total = 10.0;
        double ret = 0.0;
        if (context.isValid()) {
            score += repeatAnalyser.analyse(context);
            score += negativeAnalyser.analyse(context);
            score += xiaoleAnalyser.analyse(context);
            score += commandAnalyser.analyse(context);
            score += structAnalyser.analyse(context);
            ret = (double) score / total;
        }
        return ret;
    }

    public String test(String string) {
        String ret = string + "world";
        return ret;
    }
}
