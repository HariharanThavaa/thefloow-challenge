package com.thefloow.challenge.helper;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

public class TestConstantsUtil {

    public static final int mBperSplit=1;

    public static final String FIXTURES_LARGE_FILE = "/fixtures/enwiki-latest-abstract.xml";
    public static final String FIXTURES_LARGE_FILE_AB_PATH = getAbsolutePath(FIXTURES_LARGE_FILE);
    public static final String FIXTURES_PART_FILE = "/fixtures/0a0b050b-5781-4592-90d2-2aa61121c4e5.splitPart";
    public static final String FIXTURES_PART_FILE_AB_PATH = getAbsolutePath(FIXTURES_PART_FILE);

    private static String getAbsolutePath(String resourcePath) {
        try {
            return ResourceUtils.getFile(TestConstantsUtil.class.getResource(resourcePath)).getAbsolutePath();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
