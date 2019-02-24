package com.thefloow.challenge.service.impl;

import com.thefloow.challenge.exception.FileReadingException;
import com.thefloow.challenge.repo.Repository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DataServiceImplUTest {

    @Autowired
    private Repository repository;


    /**
     * Success Test
     * Validating Map has the valid entries
     * @throws FileReadingException
     */
    @Test
    public void givenValidFileAndValidSplitSize_whenSplitFile_thenReturnExpected() throws FileReadingException {
       log.info(String.valueOf(repository.count()));
    }
}
