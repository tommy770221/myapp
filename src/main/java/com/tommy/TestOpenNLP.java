package com.tommy;

import com.tommy.opennlp.TokenSegFolderFileRecursion;
import org.ansj.lucene3.AnsjAnalysis;
import org.apache.lucene.analysis.Analyzer;
import com.tommy.opennlp.NameEntityFindTester;
import com.tommy.opennlp.NamedEntityMultiFindTrainer;

import java.util.Map;

/**
 * Created by Tommy on 2017/7/4.
 */
public class TestOpenNLP {

    public static void main(String[] args) {

        Analyzer ansjAnalyzer = new AnsjAnalysis();

        String oriFolderPath = ".\\src\\main\\name_find\\train";
        String tagFolderPath = ".\\src\\main\\name_find\\seg";

        TokenSegFolderFileRecursion tokenSegFolderFileRecursion = new TokenSegFolderFileRecursion(
                ansjAnalyzer);
        tokenSegFolderFileRecursion.execTokenSegRecursion(oriFolderPath,
                tagFolderPath);
        String nameWordsPath = ".\\src\\main\\name_find\\name_words";
        String dataPath = ".\\src\\main\\name_find\\seg\\train";
        String modelPath = ".\\src\\main\\name_find\\model\\multi_name_model.bin";

        NamedEntityMultiFindTrainer trainer = new NamedEntityMultiFindTrainer(
                nameWordsPath, dataPath, modelPath);
        boolean succFlag = trainer.execNameFindTrainer();

        System.out.println("success: "+succFlag);
        String testFileDirPath = ".\\src\\main\\name_find\\seg\\test";

        NameEntityFindTester tester = new NameEntityFindTester(modelPath, testFileDirPath);
        Map<String, String> nameProbResMap = tester.execNameFindTester();

        for (Map.Entry<String, String> nameProbRes : nameProbResMap.entrySet()) {
            System.out.println(nameProbRes.getKey() + " -> " + nameProbRes.getValue());
        }
    }
}
