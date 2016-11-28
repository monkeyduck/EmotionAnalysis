package emotion.analyser;

import emotion.model.Context;

/**
 * Created by llc on 16/11/16.
 */
public class StructAnalyser extends BaseAnalyser {
    @Override
    public int analyse(Context context) {
        int score = 0;
        score += context.getPauseTimes() >= 2 ? 2 : 0;
        score += context.getNonFirstTimes() >= 2 ? 2 : 0;
        return score;
    }
}
