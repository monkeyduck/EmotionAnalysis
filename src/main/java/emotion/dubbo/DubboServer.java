package emotion.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * Created by llc on 16/10/30.
 */
public class DubboServer {
    private static Logger logger = LoggerFactory.getLogger(DubboServer.class);

    public static void main(String[] args){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml",
                "classpath:dubbo_emotionanalysis_server.xml"});
        ac.start();
        logger.info("start service");
        try {
            Scanner scan = new Scanner(System.in);
            while(true){
                System.in.read();
                String read = scan.nextLine();
                if(read.trim().toLowerCase().equals("exit")){
                    logger.info("stop service");
                    break;
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(),e);
        }finally {
        }
    }
}
