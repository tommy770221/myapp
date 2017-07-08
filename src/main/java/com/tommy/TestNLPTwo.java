package com.tommy;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Tommy on 2017/7/5.
 */
public class TestNLPTwo {

    static String onlpModelPath = "zh-ner-drugs.bin";
    // training data set
    static String trainingDataFilePath = "D:/NLPTools/Datasets/drugsDetails.txt";

    public static void main(String[] args) throws IOException {
        Charset charset = Charset.forName("UTF-8");
        ObjectStream<String> lineStream = new PlainTextByLineStream(
                new FileInputStream(trainingDataFilePath), charset);
        ObjectStream<NameSample> sampleStream = new NameSampleDataStream(
                lineStream);
        TokenNameFinderModel model = null;
        HashMap<String, Object> mp = new HashMap<String, Object>();
        try {
            model = NameFinderME.train("zh", "drugs", sampleStream, Collections.<String,Object>                                                                                                                emptyMap(),100,4);
        } finally {
            sampleStream.close();
        }
        BufferedOutputStream modelOut = null;
        try {
            modelOut = new BufferedOutputStream(new FileOutputStream(onlpModelPath));
            model.serialize(modelOut);
        } finally {
            if (modelOut != null)
                modelOut.close();
        }
    }
}

