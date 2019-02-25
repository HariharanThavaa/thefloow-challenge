package com.thefloow.challenge.service.impl;

import com.thefloow.challenge.exception.FileReadingException;
import com.thefloow.challenge.service.WordCounterService;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WordCounterServiceImpl implements WordCounterService {

    static final String ERROR_LOADING_PART_FILE = "Error loading part file from path [%s]";

    @Override
    public Map<String, Long> countWords(Path filePath) throws FileReadingException {
        try{
            return Files.lines(filePath)
                    .parallel()
                    .flatMap(line -> Stream.of(line.split("\\s+")))
                    .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim())
                    .map(word -> word.replaceAll("-", ""))
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.groupingBy(
                            Function.identity(), Collectors.counting()));

        }catch (Exception e){
            throw new FileReadingException(String.format(ERROR_LOADING_PART_FILE, filePath.getFileName()), e);
        }
    }
}
