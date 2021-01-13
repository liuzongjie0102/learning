package com.newland.debug;

import java.io.*;

public class NlFinalMain {
    public static void main(String[] args) {
        NlFinalMain nlFinalMain = new NlFinalMain();
        String sourceFile = "D:\\test.xlsx";
        String destFile = "D:\\target.xlsx";
        try {
            nlFinalMain.copyFile(sourceFile, destFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copyFile(String sourceFile, String destFile) throws Exception {
        File source = new File(sourceFile);
        File dest = new File(destFile);
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(source);
            out = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int n = 0;
            while ((n = in.read(buf)) > 0) {
                out.write(buf, 0, n);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
