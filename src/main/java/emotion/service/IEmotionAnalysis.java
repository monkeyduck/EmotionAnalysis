package emotion.service;

import emotion.model.Context;

import java.util.List;

/**
 * Created by llc on 16/10/28.
 */
public interface IEmotionAnalysis {
    double analyse(List<String> sentences);
    String test(String string);
}
