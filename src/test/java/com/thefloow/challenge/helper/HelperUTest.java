package com.thefloow.challenge.helper;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class HelperUTest {

    private Helper classUnderTest = new Helper();


    @Test
    public void mapToStringTest(){
        Map<String, Long> words = new HashMap<>();
        words.put("A", 10L);
        words.put("B", 20L);
        words.put("C", 30L);
        words.put("D", 40L);
        String expected = "[\n" +
                "{ word : A , count : 10 }\n" +
                "{ word : B , count : 20 }\n" +
                "{ word : C , count : 30 }\n" +
                "{ word : D , count : 40 }\n]";
        assertEquals(classUnderTest.mapToString(words), expected);
    }

    @Test
    public void getWordCountMapTest_WhenBothMapEmpty_ReturnNewMap(){
        Map< String, Long> existingMap = new HashMap<>();
        Map< String, Long> newMap = new HashMap<>();

        Map< String, Long> actual = classUnderTest.getWordCountMap(existingMap, newMap, 5,
                Helper.Query.MOST_COMMON_WORDS);
        Map< String, Long> expected = new HashMap<>();
        assertEquals(actual,expected);
    }

    @Test
    public void getWordCountMapTest_WhenExistingMapHasDataAndNewMapIsEmpty_ReturnExistingMap(){
        Map< String, Long> existingMap = new HashMap<>();
        Map< String, Long> newMap = new HashMap<>();
        // new map is empty and existing map contains data return existingMap
        existingMap.put("A", 10L);
        Map< String, Long>  actual = classUnderTest.getWordCountMap(existingMap, newMap, 5,
                Helper.Query.MOST_COMMON_WORDS);
        Map< String, Long> expected = existingMap;
        assertEquals( expected, actual);
    }

    @Test
    public void getWordCountMapTest_WhenExistingMapEmptyAndNewMapHasData_ReturnNewMap(){
        Map< String, Long> existingMap = new HashMap<>();
        Map< String, Long> newMap = new HashMap<>();
        // existing map is empty and new map contains data return newMap
        newMap.put("A", 10L);
        Map< String, Long>  actual = classUnderTest.getWordCountMap(existingMap, newMap, 5,
                Helper.Query.MOST_COMMON_WORDS);
        Map< String, Long> expected = newMap;
        assertEquals( expected, actual);
    }

    @Test
    public void getWordCountMapTest_WhenBothMapHasNonCommonData_ReturnSortedMap(){

        //if both contains data
        Map< String, Long> existingMap = new HashMap<>();
        existingMap.put("A", 10L);
        existingMap.put("C", 30L);
        existingMap.put("E", 20L);

        Map< String, Long> newMap = new HashMap<>();
        newMap.put("B", 20L);
        newMap.put("D", 40L);
        newMap.put("E", 30L);

        Map< String, Long>  actual = classUnderTest.getWordCountMap(existingMap, newMap, 5,
                Helper.Query.MOST_COMMON_WORDS);
        Map< String, Long> expected = new HashMap<>();
        expected.put("A", 10L);
        expected.put("B", 20L);
        expected.put("C", 30L);
        expected.put("D", 40L);
        expected.put("E", 50L);

        assertEquals( expected, actual);
    }

    @Test
    public void getWordCountMapTest_WhenBothMapHasCommonData_ReturnSortedMap(){

        // limit to numberOfWinners
        Map< String, Long> existingMap = new HashMap<>();
        existingMap.put("A", 10L);
        existingMap.put("C", 30L);
        existingMap.put("E", 50L);

        Map< String, Long> newMap = new HashMap<>();
        newMap.put("B", 20L);
        newMap.put("D", 40L);
        newMap.put("F", 60L);

        Map< String, Long>  actual = classUnderTest.getWordCountMap(existingMap, newMap, 5,
                Helper.Query.MOST_COMMON_WORDS);
        Map< String, Long> expected = new LinkedHashMap<>();
        expected.put("F", 60L);
        expected.put("E", 50L);
        expected.put("D", 40L);
        expected.put("C", 30L);
        expected.put("B", 20L);

        assertEquals( expected, actual);
    }

    @Test
    public void getWordCountMapTest_WhenBothMapHasCommonData_ReturnLeastCommonWordsMap(){

        // limit to numberOfWinners
        Map< String, Long> existingMap = new HashMap<>();
        existingMap.put("A", 10L);
        existingMap.put("C", 30L);
        existingMap.put("E", 50L);

        Map< String, Long> newMap = new HashMap<>();
        newMap.put("B", 20L);
        newMap.put("D", 40L);
        newMap.put("F", 60L);

        Map< String, Long>  actual = classUnderTest.getWordCountMap(existingMap, newMap, 5,
                Helper.Query.LEAST_COMMON_WORDS);
        Map< String, Long> expected = new LinkedHashMap<>();
        expected.put("A", 10L);
        expected.put("B", 20L);
        expected.put("C", 30L);
        expected.put("D", 40L);
        expected.put("E", 50L);

        assertEquals( expected, actual);
    }

}
