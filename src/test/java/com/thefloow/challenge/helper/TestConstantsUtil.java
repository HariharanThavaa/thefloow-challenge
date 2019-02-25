package com.thefloow.challenge.helper;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

public class TestConstantsUtil {


    public static final int mBperSplit = 1;
    private static final String FIXTURES_FILE_PATH = "/fixtures/Sample";
    public static final String FIXTURES_FILE_AB_PATH = getAbsolutePath(FIXTURES_FILE_PATH);

    public static final String SOURCE_PARAM = "–source";


    public static String addLineBreak(String text) {
        return text + "\n";
    }

    private static String getAbsolutePath(String resourcePath) {
        try {
            return ResourceUtils.getFile(TestConstantsUtil.class.getResource(resourcePath)).getAbsolutePath();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
