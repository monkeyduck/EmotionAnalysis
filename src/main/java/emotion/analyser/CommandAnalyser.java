package emotion.analyser;

import emotion.model.Context;
import emotion.utils.Utils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by llc on 16/10/23.
 */
public class CommandAnalyser extends BaseAnalyser {
    private static List<String> commands = new ArrayList<String>();

    public CommandAnalyser() {
        InputStream inputStream = getClass().getResourceAsStream("/conf/command.txt");
        try{
            commands = Utils.readLines(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int analyse(Context context) {
        int senSize = context.getUserSentences().size();
        if (senSize > 1){
            String cur = context.getUserCurrentSentence();
            String last = context.getUserSentences().get(senSize - 2);
            for (String comd: commands){
                Pattern p = Pattern.compile(comd);
                Matcher m = p.matcher(cur); // 获取 matcher 对象
                if (m.find()){
                    m = p.matcher(last);
                    if (m.find()) return 1;
                }
            }
        }
        return 0;
    }
}
