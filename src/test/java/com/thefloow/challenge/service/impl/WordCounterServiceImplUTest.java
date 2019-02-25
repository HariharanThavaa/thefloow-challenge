package com.thefloow.challenge.service.impl;

import com.thefloow.challenge.exception.FileReadingException;
import com.thefloow.challenge.service.WordCounterService;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Map;

import static com.thefloow.challenge.helper.TestConstantsUtil.FIXTURES_FILE_AB_PATH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WordCounterServiceImplUTest {

    private static final String SOME_NON_EXISTING_PATH = "/Some/non-existing/path";

    private WordCounterService classUnderTest = new WordCounterServiceImpl();

    /**
     * Failure Test
     * Validating the given file path
     * @throws FileReadingException
     */
    @Test(expected = FileReadingException.class)
    public void givenFilePathNotExists_whenSplitFile_thenThrowFileReadingException() throws FileReadingException {
        classUnderTest.countWords(Paths.get(SOME_NON_EXISTING_PATH));
    }

    /**
     * Success Test
     * Validating Map has the valid entries
     * @throws FileReadingException
     */
    @Test
    public void givenValidFileAndValidSplitSize_whenSplitFile_thenReturnExpected() throws FileReadingException {
            Map<String, Long> map = classUnderTest.countWords(Paths.get(FIXTURES_FILE_AB_PATH));
            assertTrue(!map.isEmpty());
            assertEquals(map.size(), 253);
    }

}
