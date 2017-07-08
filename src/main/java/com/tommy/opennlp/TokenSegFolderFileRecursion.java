package com.tommy.opennlp;

import org.apache.lucene.analysis.Analyzer;

import java.io.File;

/**
 * Created by Tommy on 2017/7/4.
 */
public class TokenSegFolderFileRecursion {

    private static boolean flagFirst = true;

    private Analyzer a;

    public TokenSegFolderFileRecursion(Analyzer a) {
        super();
        this.a = a;
    }

    /**
     * 在目标文件夹中建立一个与源文件夹名相同的新文件夹
     *
     * @param oriFolderPath
     * @param tagFolderPath
     */
    public void execTokenSegRecursion(String oriFolderPath, String tagFolderPath) {

        File oriFolder = new File(oriFolderPath);
        if (!oriFolder.exists()) {
            System.err.println("源文件夹不存在！");
            return;
        }
        File[] oriFolderFiles = oriFolder.listFiles();
        File tagFilesFolder;
        if (flagFirst) {
            tagFilesFolder = new File(tagFolderPath + File.separator
                    + oriFolder.getName());
            if (oriFolder.equals(tagFilesFolder)) {
                System.err.println("源文件夹和目标文件夹不可重复！");
                return;
            }
            if (!tagFilesFolder.exists()) {
                tagFilesFolder.mkdirs();
            }
            flagFirst = false;
        } else {
            tagFilesFolder = new File(tagFolderPath);
        }

        for (File oriF : oriFolderFiles) {
            if (oriF.isDirectory()) {
                File newFolder = new File(tagFilesFolder.getAbsolutePath()
                        + File.separator + oriF.getName());
                if (!newFolder.exists()) {
                    newFolder.mkdirs();
                }
                execTokenSegRecursion(oriF.getAbsolutePath(),
                        newFolder.getAbsolutePath());
            } else {
                String tagFilePath = tagFilesFolder.getAbsolutePath()
                        + File.separator + oriF.getName();
                Boolean runFlag = TokenSegUtil.tokenFileSeg(oriF, a,
                        tagFilePath);

                System.out.println("建立分词文件：" + tagFilePath + runFlag);
            }
        }
    }

    public Analyzer getA() {
        return a;
    }

    public void setA(Analyzer a) {
        this.a = a;
    }

}
