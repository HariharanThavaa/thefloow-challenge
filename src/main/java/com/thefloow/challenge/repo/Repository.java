package com.thefloow.challenge.repo;

import com.thefloow.challenge.domain.Word;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository extends MongoRepository<Word, String> {

    public Word findByFileName(String fileName);
}
