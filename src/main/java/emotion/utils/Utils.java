package emotion.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by llc on 16/10/30.
 */
public class Utils {
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    public static List<String> readLines(InputStream inputStream) throws IOException{
        List<String> input = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            input.add(line);
        }
        return input;
    }
}
