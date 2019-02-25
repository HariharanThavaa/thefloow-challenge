package com.thefloow.challenge.functionalTest;

import com.thefloow.challenge.domain.Word;
import com.thefloow.challenge.exception.FileReadingException;
import com.thefloow.challenge.helper.Helper;
import com.thefloow.challenge.repo.Repository;
import com.thefloow.challenge.service.DataService;
import com.thefloow.challenge.service.FileSplitterService;
import com.thefloow.challenge.service.WordCounterService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;

import static com.thefloow.challenge.helper.TestConstantsUtil.FIXTURES_FILE_AB_PATH;
import static com.thefloow.challenge.helper.TestConstantsUtil.mBperSplit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DataServiceImplITest {

    @Autowired
    private Repository repository;

    @Autowired
    private DataService classUnderTest;

    @Autowired
    private WordCounterService counter;


    /**
     * Success Test
     * Validating Map has the valid entries
     * @throws FileReadingException
     */
    @Test
    public void givenValidFileAndValidSplitSize_whenSplitFile_thenReturnExpected() throws FileReadingException {
        Word word = repository.findByFileName(FIXTURES_FILE_AB_PATH);
        repository.delete(word);
        classUnderTest.save(FIXTURES_FILE_AB_PATH, counter.countWords(Paths.get(FIXTURES_FILE_AB_PATH)));
        word = repository.findByFileName(FIXTURES_FILE_AB_PATH);
        log.info(word.toString());
        assertEquals(word.fileName, FIXTURES_FILE_AB_PATH);
    }
}
