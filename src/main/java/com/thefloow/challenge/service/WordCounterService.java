package com.thefloow.challenge.service;

import com.thefloow.challenge.exception.FileReadingException;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * Given a file/ part of a file
 * find unique word and it's number of occurrence.
 * Return the results in a Hash Map (Key = word, Value= count)
 */
public interface WordCounterService {

    /**
     * given a file, find unique word and it's number of occurrence
     *
     * @param filePath   Path of file to be split.
     * @throws FileReadingException
     */
    public Map< String, Long> countWords(Path filePath) throws FileReadingException;
}
