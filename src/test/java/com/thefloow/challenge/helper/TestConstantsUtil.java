package com.thefloow.challenge.helper;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

public class TestConstantsUtil {

    public static final int mBperSplit=1;

    public static final String FIXTURES_LARGE_FILE = "/fixtures/enwiki-latest-abstract.xml";
    public static final String FIXTURES_LARGE_FILE_AB_PATH = getAbsolutePath(FIXTURES_LARGE_FILE);

    private static String getAbsolutePath(String resourcePath) {
        try {
            return ResourceUtils.getFile(TestConstantsUtil.class.getResource(resourcePath)).getAbsolutePath();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
