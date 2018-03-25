package com.company;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
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

    public static void NioReadTest() throws IOException {
        Files.readAllBytes(temp.toPath());
    }

    public static void MemoryMapped() throws IOException {
        FileChannel fc = new RandomAccessFile(temp, "r").getChannel();

        MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

        for (int i = 0; i < buffer.limit(); i++)
        {
            buffer.get();
        }
    }


    public static void main(String[] args) throws Exception {


        temp = File.createTempFile("bigFile", ".bin");
        FileOutputStream fos = new FileOutputStream(temp);
        for (int i = 0 ; i < 100000; i++)
        {
            fos.write(ranGen.nextInt());
        }

        System.out.println("Buffered read");

        System.out.println(new Benchmark().TestMean(() -> {
            BufferedReadTest();
            return null;
        },10).time + "ns");

        System.out.println("NIO ");

        System.out.println(new Benchmark().TestMean(() -> {
            NioReadTest();
            return null;
        },10).time + "ns");

        System.out.println("Memory mapped");
        System.out.println(new Benchmark().TestMean(() -> {
            MemoryMapped();
            return null;
        }, 10).time + "ns");


    }
}
