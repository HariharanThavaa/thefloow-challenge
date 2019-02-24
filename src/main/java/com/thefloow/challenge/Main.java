package com.thefloow.challenge;

import com.thefloow.challenge.domain.Word;
import com.thefloow.challenge.exception.FileReadingException;
import com.thefloow.challenge.repo.Repository;
import com.thefloow.challenge.service.DataService;
import com.thefloow.challenge.service.FileSplitterService;
import com.thefloow.challenge.service.WordCounterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.*;

@Slf4j
@Component
public class Main implements CommandLineRunner {

    private final FileSplitterService fileSplitterService;
    private final WordCounterService counterService;
    private final DataService dataService;
    private final Repository repository;
    private long value;

    @Autowired
    Main(FileSplitterService fileSplitterService,
         WordCounterService counterService,
         DataService dataService,
         Repository repository){
        this.fileSplitterService = fileSplitterService;
        this.counterService = counterService;
        this.dataService = dataService;
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(String.valueOf(repository.count()));
        repository.deleteAll();
        log.info(String.valueOf(repository.count()));

        long lStartTime = System.currentTimeMillis();
        String largeFile = "/Users/thavaahariharan/work/Floow/workspace/challenge/src/test/resources/fixtures/enwiki-latest-abstract.xml";
        fileSplitterService.splitFile(largeFile).stream()
                .forEach(path -> dataService.save(largeFile, count(path)));

        Word word = repository.findByFileName(largeFile);
        log.info(word.toString());

        long lEndTime = System.currentTimeMillis();
        long output = lEndTime - lStartTime;
        log.info("Latency/Time Delay : " + output);
    }

    private Map<String,Long> count(Path path){
        try{
            log.info(path.getFileName().toString());
            return counterService.countWords(path);
        }catch (FileReadingException ex){
            log.info("Error while word cont");
        }
        return new HashMap<String, Long>();
    }
}
