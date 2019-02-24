package com.thefloow.challenge.service;

import java.util.Map;

/**
 * Given HashMap with words and counts
 * update or insert Data
 */
public interface DataService {

    public void save(String fileName, Map<String, Long> wordCountMap);

}
