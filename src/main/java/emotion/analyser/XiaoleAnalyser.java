package emotion.analyser;

import emotion.model.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by llc on 16/10/23.
 */
public class XiaoleAnalyser extends BaseAnalyser{

    private List<String> shakeWords;

    public XiaoleAnalyser(){
        shakeWords = new ArrayList<String>();
        shakeWords.add("我快被摇晕了");
        shakeWords.add("救命啊!晃得我好晕啊");
        shakeWords.add("倒着不舒服,我都看不到你的脸了");
    }

    public boolean shakeAnalysis(Context context){
        String cur = context.getXiaoLeCurrentSentence();
        return shakeWords.contains(cur);
    }

    public boolean wrongTipAnalysis(Context context){
        String cur = context.getXiaoLeCurrentSentence();
        return cur.contains("wrong_tip");
    }

    public boolean repeatAnalysis(Context context){
        String cur = context.getXiaoLeCurrentSentence();
        int prevNum = context.getXiaoleSentences().size();
        int start = prevNum > 3 ? prevNum - 3 : 0;
        for (int i = start; i < prevNum - 1; ++i){
            if (cur.equals(context.getXiaoleSentences().get(i)))
                return true;
        }
        return false;
    }

    public int analyse(Context context){
        int score = 0;
        score += shakeAnalysis(context) ? 1 : 0;
        score += wrongTipAnalysis(context) ? 1 : 0;
        score += repeatAnalysis(context) ? 1 : 0;
        return score;
    }
}
