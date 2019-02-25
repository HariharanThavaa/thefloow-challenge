package com.thefloow.challenge.helper;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Helper {

    public enum Query{
        MOST_COMMON_WORDS, LEAST_COMMON_WORDS
    }

    public String mapToString(Map< String, Long> map){
        StringBuilder result = new StringBuilder("[\n") ;
        map.forEach((k,v)->result.append("{ word : " + k + " , " + "count : " + v + " }\n"));
        return result.append("]").toString();
    }

    public Map< String, Long> getWordCountMap(Map< String, Long> existingMap,
                                                        Map< String, Long> newMap,
                                                        int numberOfWinners, Query query){

        if(!existingMap.isEmpty()){
            if (!newMap.isEmpty()){
                newMap.forEach((k,v) -> merge(existingMap, k, v));
                if(Query.MOST_COMMON_WORDS.equals(query)){
                    return getMostCommonWordsMap(existingMap, numberOfWinners);
                }

                if(Query.LEAST_COMMON_WORDS.equals(query)){
                    return getLeastCommonWordsMap(existingMap, numberOfWinners);
                }
            }
            return existingMap;
        }else{
            if (!newMap.isEmpty()){
                if(Query.MOST_COMMON_WORDS.equals(query)){
                    return getMostCommonWordsMap(newMap, numberOfWinners);
                }
                if(Query.LEAST_COMMON_WORDS.equals(query)){
                    return getLeastCommonWordsMap(newMap, numberOfWinners);
                }
            }
        }

        return new HashMap<>();
    }

    private Map< String, Long> getMostCommonWordsMap(
            Map< String, Long> existingMap,
            int numberOfWinners){
        Map< String, Long> response = new LinkedHashMap<>();
        existingMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(numberOfWinners)
                .forEach((e)-> addToMap(response, e.getKey(),e.getValue()));
        return response;
    }

    private void merge(Map< String, Long> map, String key, Long value){
        if(map.containsKey(key)){
            map.put(key, map.get(key)+value);
        }else{
            map.put(key,value);
        }
    }

    private void addToMap(Map< String, Long> map, String key, Long value){
        map.put(key,value);
    }

    private Map< String, Long> getLeastCommonWordsMap(
            Map< String, Long> existingMap,
            int numberOfWinners){
        Map< String, Long> response = new LinkedHashMap<>();
        existingMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(numberOfWinners)
                .forEach((e)-> addToMap(response, e.getKey(),e.getValue()));
        return response;
    }

}
