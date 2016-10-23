package emotion.model;

import java.util.List;

/**
 * Created by llc on 16/10/21.
 */
public class Context {
    private List<String> userSentences;
    private List<String> xiaoleSentences;

    public Context(List<String> userSentences, List<String> xiaoleSentences) {
        this.userSentences = userSentences;
        this.xiaoleSentences = xiaoleSentences;
    }

    public List<String> getUserSentences() {
        return userSentences;
    }

    public void setUserSentences(List<String> userSentences) {
        this.userSentences = userSentences;
    }

    public List<String> getXiaoleSentences() {
        return xiaoleSentences;
    }

    public void setXiaoleSentences(List<String> xiaoleSentences) {
        this.xiaoleSentences = xiaoleSentences;
    }

    public String getUserCurrentSentence(){
        if (userSentences.size() == 0) return "";
        else return userSentences.get(userSentences.size() - 1);
    }

    public String getXiaoLeCurrentSentence(){
        if (xiaoleSentences.size() == 0) return "";
        else return xiaoleSentences.get(xiaoleSentences.size() - 1);
    }
}