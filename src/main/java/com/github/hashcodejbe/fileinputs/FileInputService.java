package com.github.hashcodejbe.fileinputs;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Service
public class FileInputService {

    public InputReader getFileReader(int year, String stage, String fileName) throws FileNotFoundException {
        return new InputReader(getFileStreamFromResource(year, stage, fileName));
    }

    public List<String> getFiles(int year, String stage) {
        String[] dirs = new java.io.File(getDir(year, stage)).list();
        return Arrays.asList(dirs);
    }

    private InputStream getFileStreamFromResource(int year, String stage, String fileName) throws FileNotFoundException {
        //return getClass().getResourceAsStream(getFilePath(year, stage, fileLetter));
        return new FileInputStream(getFilePath(year, stage, fileName));
    }

    private String getDir(int year, String stage) {
        String[] parts = new String[] {
                ".", "src", "main", "resources", "static",
                "inputs", String.valueOf(year), stage
        };
        return String.join(File.separator, parts);
    }

    private String getFilePath(int year, String stage, String fileName) {
        //String[] parts = new String[] {"inputs", String.valueOf(year), stage, fileLetter + ".txt"};
        String[] parts = new String[] {
                ".", "src", "main", "resources", "static",
                "inputs", String.valueOf(year), stage, fileName
        };
        return String.join(File.separator, parts);
    }
}
