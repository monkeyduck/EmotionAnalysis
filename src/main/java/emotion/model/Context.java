package emotion.model;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by llc on 16/10/21.
 */
public class Context {
    private List<String> userSentences;
    private List<String> xiaoleSentences;
    private int pauseTimes;
    private int nonFirstTimes;

    public Context(List<String> input) {
        //sendType: text,sensor,effect,pause,nonFirstStart
        this.userSentences = new ArrayList<>();
        this.xiaoleSentences = new ArrayList<>();
        pauseTimes = 0;
        nonFirstTimes = 0;
        for (String sentence : input){
            JSONObject json = JSONObject.fromObject(sentence);
            String sendType = json.getString("sendType");
            String direction = json.getString("direction");
            if (sendType.equals("text")){
                if (direction.equals("usr_spk_text"))
                    this.userSentences.add(json.getString("content"));
                else if (direction.equals("to_usr_text"))
                    this.xiaoleSentences.add(json.getString("content"));
            } else if (sendType.equals("pause")){
                pauseTimes++;
            } else if (sendType.equals("nonFirstStart")){
                nonFirstTimes++;
            }
        }
    }

    public List<String> getUserSentences() {
        return userSentences;
    }

    public List<String> getXiaoleSentences() {
        return xiaoleSentences;
    }

    public String getUserCurrentSentence(){
        if (userSentences.size() == 0) return "";
        else return userSentences.get(userSentences.size() - 1);
    }

    public String getXiaoLeCurrentSentence(){
        if (xiaoleSentences.size() == 0) return "";
        else return xiaoleSentences.get(xiaoleSentences.size() - 1);
    }

    public int getPauseTimes() {
        return pauseTimes;
    }

    public int getNonFirstTimes() {
        return nonFirstTimes;
    }

    public boolean isValid() {
        return userSentences.size() > 0 && xiaoleSentences.size() > 0;
    }
}