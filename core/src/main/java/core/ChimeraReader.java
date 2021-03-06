package core;

import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Iterator;

/**
 * Created by gleb on 08.10.15.
 */
public class ChimeraReader implements Iterator<String> {

    private BufferedReader stream;
    private String file;
    private String data;
    private Integer from;
    private Integer to;

    public ChimeraReader(InputData inputData) {
        this.file = inputData.getFileName();
        this.from = inputData.getFrom();
        this.to = inputData.getTo();
        initStream();
    }

    private void initStream() {
        try {
            FileInputStream compressed = new FileInputStream(file);
            XZCompressorInputStream xzIn = new XZCompressorInputStream(compressed, true);
            this.stream = new BufferedReader(new InputStreamReader(new BufferedInputStream(xzIn)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        try {
            data = stream.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data != null;
    }

    @Override
    public String next() {
        return data;
    }
}
