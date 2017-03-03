package emotion.analyser;

import emotion.model.Context;

/**
 * Created by llc on 16/10/23.
 */
public class AudioAnalyser extends BaseAnalyser {

    public int analyse(Context context) {
        return 0;
//        FileInputStream fis;
//        String filename = context.getUserCurrentSentence();
//        int label = 0;
//        try {
//            fis = new FileInputStream(filename);
//            byte[] temp = new byte[fis.available()];
//            fis.read(temp);
//            fis.close();
//            byte[] buffer = Arrays.copyOfRange(temp, 44, temp.length);
//            PitchEstimation.process(buffer);
//            GetLoudnessSeq.process(buffer, buffer.length, PitchEstimation.getPoint());
//            if(PitchEstimation.angryScore >= 1.0 && GetLoudnessSeq.getLoudnessmax() >= 3.0)
//                label = 1;
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return label;

    }
}
