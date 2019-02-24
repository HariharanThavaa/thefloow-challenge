package com.thefloow.challenge.domain;

import com.thefloow.challenge.helper.Helper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Getter
@Setter
public class Word {

    @Id
    public String id;

    public String fileName;
    public Map< String, Long> mostCommonWords;
    public Map< String, Long> leastCommonWords;

    public Word() {}

    public Word(String fileName,
                Map< String, Long> mostCommonWords,
                Map< String, Long> leastCommonWords) {
        this.fileName = fileName;
        this.mostCommonWords = mostCommonWords;
        this.leastCommonWords = leastCommonWords;
    }

    @Override
    public String toString() {
        Helper helper = new Helper();
        return String.format(
                "Word[fileName='%s'\nmostCommonWords=\n'%s'\nleastCommonWords=\n'%s']",
                fileName, helper.mapToString(mostCommonWords), helper.mapToString(leastCommonWords));
    }



}
