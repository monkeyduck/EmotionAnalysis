package emotion.service;

import java.util.List;

/**
 * Created by llc on 16/10/28.
 */
public interface IEmotionAnalysis {
    double analyse(List<String> sentences);
}
