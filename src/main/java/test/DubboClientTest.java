package test;

import emotion.service.IEmotionAnalysis;
import org.apache.commons.io.FileUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by llc on 16/10/28.
 */
public class DubboClientTest {
    private static List<String> input = new ArrayList<String>();
    static {
        URL url = DubboClientTest.class.getResource("/conf/labeled.txt");
        File file = FileUtils.getFile(url.getPath());
        input = new ArrayList<String>();
        try{
            input = FileUtils.readLines(file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
                new String[]{"classpath:spring.xml", "dubbo_userservice_client_alpha.xml"});
        ac.start();
        IEmotionAnalysis analyser = (IEmotionAnalysis) ac.getBean("EmotionAnalysis");

        for (int i = 0; i < input.size(); ++i) {
            int start = i > 4 ? i - 4 : 0;
            List<String> userWords = new ArrayList<String>();
            List<String> xiaoleWords = new ArrayList<String>();
            for (int j = start; j <= i; ++j) {
                String str = input.get(j);
                xiaoleWords.add(str.split("\\t")[4]);
                userWords.add(str.split("\\t")[5]);
            }
            System.out.println(analyser.analyse(userWords));
        }

    }

}
