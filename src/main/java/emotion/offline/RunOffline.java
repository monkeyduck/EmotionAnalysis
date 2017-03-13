package emotion.offline;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static emotion.offline.MainLabel.*;

/**
 * Created by llc on 17/3/13.
 */
public class RunOffline {
    private static Logger logger = Logger.getLogger(RunOffline.class);

    public static void main(String[] args) {
        // 下载音频
        MainLabel label = new MainLabel();
        String audioPath = "";
        InputStream inputStream = MainLabel.class.getResourceAsStream("/conf/labeled.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line=bufferedReader.readLine())!=null){
                lines.add(line);
                String segs[] = line.split("\t");
                String mem_id = segs[0];
                String record_id = segs[3].split("/")[segs[3].split("/").length-1];
                audioKeyList.add(mem_id+"@"+record_id);
                audioList.add(audioPath);
            }
            label.downloadAudioWavBatch();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        logger.info("Finished downloading audios");
        // 分析音频
        try {
            label.createAudioLabel();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        // 分析文本,运行python脚本,注意需要在服务器上配好python环境,比如sklearn, jieba等
        DateTime dt = new DateTime();
        String suffix = dt.toString("yyyy-MM-dd HH:mm:ss");
        try {
            label.runPython(suffix);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
}
