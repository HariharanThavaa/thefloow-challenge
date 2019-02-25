package com.thefloow.challenge.service.impl;

import com.thefloow.challenge.exception.FileReadingException;
import com.thefloow.challenge.service.FileSplitterService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.thefloow.challenge.helper.TestConstantsUtil.mBperSplit;
import static com.thefloow.challenge.helper.TestConstantsUtil.FIXTURES_FILE_AB_PATH;
import static org.junit.Assert.assertTrue;

@Slf4j
public class FileSplitterServiceImplUTest {

    private static final String SOME_NON_EXISTING_PATH = "/Some/non-existing/path";
    static final String ERROR_LOADING_LARGE_FILE = "Error loading large file from path [%s]";

    private FileSplitterService classUnderTest = new FileSplitterServiceImpl(mBperSplit);

    /**
     * Failure Test
     * Validating the given file path
     * @throws FileReadingException
     */
    @Test(expected = FileReadingException.class)
    public void givenFilePathNotExists_whenSplitFile_thenThrowFileReadingException() throws FileReadingException {
        classUnderTest.splitFile(SOME_NON_EXISTING_PATH);
    }

    /**
     * Success Test
     * Validating number part files created in temp file
     * @throws FileReadingException
     */
    @Test
    public void givenValidFileAndValidSplitSize_whenSplitFile_thenReturnExpected() throws FileReadingException {
        try{
            final long sourceSize = Files.size(Paths.get(FIXTURES_FILE_AB_PATH));
            final long bytesPerSplit = 1024L * 1024L * mBperSplit;
            classUnderTest.splitFile(FIXTURES_FILE_AB_PATH);
            File directory=new File("/tmp/");
            int fileCount=directory.list().length;
            log.info("number of files :: " + fileCount);
            log.info("It should be greater than :: " + sourceSize/bytesPerSplit);
            assertTrue(fileCount >sourceSize/bytesPerSplit);
        }catch (IOException e){
            throw new FileReadingException(String.format(ERROR_LOADING_LARGE_FILE, FIXTURES_FILE_AB_PATH), e);
        }
    }
}
