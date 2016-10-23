package emotion.analyser;

import emotion.model.Context;

/**
 * Created by llc on 16/10/21.
 */
public class Analyser {
    private static RepeatAnalyser repeatAnalyser = new RepeatAnalyser();
    private static NegativeAnalyser negativeAnalyser = new NegativeAnalyser();
    private static XiaoleAnalyser xiaoleAnalyser = new XiaoleAnalyser();
    private static CommandAnalyser commandAnalyser = new CommandAnalyser();

    public double analyse(Context context){
        int score = 0;
        double total = 10.0;
        score += repeatAnalyser.analyse(context);
        score += negativeAnalyser.analyse(context);
        score += xiaoleAnalyser.analyse(context);
        score += commandAnalyser.analyse(context);
        double ret = (double) score / total;
        return ret;
    }
}
