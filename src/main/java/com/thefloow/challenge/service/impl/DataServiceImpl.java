package com.thefloow.challenge.service.impl;

import com.thefloow.challenge.domain.Word;
import com.thefloow.challenge.helper.Helper;
import com.thefloow.challenge.repo.Repository;
import com.thefloow.challenge.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataServiceImpl implements DataService {

    private final int numberOfBestDataToConsider;
    private final Repository repository;
    private final Helper helper;

    @Autowired
    public DataServiceImpl(
            @Value("${number.of.best.data}") int numberOfBestDataToConsider,
            Repository repository,
            Helper helper){
        this.numberOfBestDataToConsider = numberOfBestDataToConsider;
        this.repository = repository;
        this.helper = helper;
    }



    @Override
    public void save(String fileName, Map<String, Long> wordCountMap) {
        //Get First Best Values
        Word word = repository.findByFileName(fileName);
        if(word == null){
            word = new Word(fileName, new HashMap<>(), new HashMap<>());
        }
        word.setFileName(fileName);
        word.setMostCommonWords(helper.getWordCountMap(word.mostCommonWords,
                wordCountMap, numberOfBestDataToConsider, Helper.Query.MOST_COMMON_WORDS));
        word.setLeastCommonWords(helper.getWordCountMap(word.leastCommonWords,
                wordCountMap, numberOfBestDataToConsider, Helper.Query.LEAST_COMMON_WORDS));
        repository.save(word);
    }
}
