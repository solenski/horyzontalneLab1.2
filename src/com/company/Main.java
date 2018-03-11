package com.company;

import java.io.*;
import java.util.Random;
import java.util.concurrent.Callable;


public class Main {

    public static Random ranGen = new Random();
    private static File temp;

    public static void BufferedReadTest() throws IOException {
        FileReader fr = new FileReader(temp);
        BufferedReader br = new BufferedReader(fr);

        String sCurrentLine;

        while ((sCurrentLine = br.readLine()) != null) {
        }
    }

    public static void Bu

    public static void main(String[] args) throws IOException {


        temp = File.createTempFile("bigFile", ".bin");
        FileOutputStream fos = new FileOutputStream(temp);
        for (int i = 0 ; i < 100000000; i++)
        {
            fos.write(ranGen.nextInt());
        }



    }
}
