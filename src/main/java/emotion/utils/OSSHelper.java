package emotion.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by llc on 16/7/7.
 */
public class OSSHelper {
    public static OSSClient ossClient;
    private static final Logger logger = LoggerFactory.getLogger(OSSHelper.class);
    private static String endpoint;
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String bucketName;

    static{
        endpoint = "oss-cn-beijing-internal.aliyuncs.com";
        accessKeyId = "Kywj58hCJKOSQTSk";
        accessKeySecret = "tnQmCz77jZdxr5OBBqKb4lWx4fRziC";
        bucketName = "record-resource";
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    public OSSClient getOssClient(){
        return ossClient;
    }

    public void download(String fname) throws IOException {
        // 创建OSSClient实例
        String[] segs = fname.split("@");
        String filename = "audio/"+segs[0]+"-"+segs[1];
        String key = segs[0]+"/"+segs[1];
//        File wavfile = new File(filename);
        File wavfile = new File("/home/llc/LogAnalysis/"+filename);
        if (!wavfile.exists()){
//            wavfile.createNewFile();
//            ObjectListing objectListing = ossClient.listObjects(bucketName, key.split("-")[0]);
//            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
//            for (OSSObjectSummary s : sums) {
//                System.out.println("\t" + s.getKey());
//            }
            //prefix
            try{
                logger.info("Start to download wav: "+key);
                ossClient.getObject(new GetObjectRequest(bucketName, key), wavfile);
                logger.info("Finished downloading wav: "+key);
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
        else{
            logger.info(key+" has existed.");
        }

    }

    public static void main(String args[]){
        String externalEndpoint =  "http://oss-cn-beijing.aliyuncs.com";
        OSSClient client = new OSSClient(externalEndpoint, accessKeyId, accessKeySecret);
        String key = "11ff924ae966460ca36c97a44192b2270.56092095/2016-08-06-07-23-19-user.wav";
        String filename = "11ff924ae966460ca36c97a44192b2270.56092095-2016-08-06-07-23-19-user.wav";
        System.out.println(client.doesObjectExist(bucketName,key));
        File wavfile = new File("/Users/linchuan/"+filename);
//        OSSObject object = client.getObject(bucketName, filename);
        client.getObject(new GetObjectRequest(bucketName, key), wavfile);

//        try{
//            BufferedReader reader = new BufferedReader(new InputStreamReader(object.getObjectContent()));
//            while (true) {
//                String line = reader.readLine();
//                if (line == null) break;
//                System.out.println("\n" + line);
//            }
//            reader.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }

//        client.getObject(new GetObjectRequest(bucketName, filename), wavfile);
        System.out.println("Finished downloading wav: "+filename);

    }


}
