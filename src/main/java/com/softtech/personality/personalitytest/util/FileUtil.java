package com.softtech.personality.personalitytest.util;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileUtil {
    public static String readFromInputStream(String uri)
            throws IOException {
        FileInputStream fis = new FileInputStream(uri);
        return IOUtils.toString(fis, "UTF-8");

    }
}
