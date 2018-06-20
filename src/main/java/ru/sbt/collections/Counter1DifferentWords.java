package ru.sbt.collections;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Consumer;

/**
 * Подсчитайте количество различных слов в файле.
 */
public class Counter1DifferentWords {
    private static final String REGEXP_SPLIT_STRING = "[\b ,.;:()\r\n\t\\[\\]—]";

    private static Iterator<String> reverseIterator = new Iterator<String>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public String next() {
            return null;
        }
    };

    private static List<String> getWords(String lines) {
        return Arrays.asList(lines.split(REGEXP_SPLIT_STRING));
    }

    private static Set<String> getUniqWords(List<String> list) {
        Set<String> resultSet = new HashSet<>();
        String[] arr = new String[list.size()];
        arr = list.toArray(arr);
        Collections.addAll(resultSet, arr);
        resultSet.remove("");
        return resultSet;
    }

    private static void printHeaderString() {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[2].getMethodName()
                + " -----------------------------------------------------------------------");
    }

    public static void printCountWords(Set<String> words) {
        //Задание 1: Подсчитайте количество различных слов в файле.
        printHeaderString();
        System.out.println("Количество слов в списке: " + words.size());
    }

    public static void printUniqWords(Set<String> words) {
        //Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины.
        Comparator<String> comparator = (o1, o2) -> {
            int ret;
            ret = o1.length() > o2.length() ? 1 : -1;
            return ret;
        };

        TreeSet<String> ts = new TreeSet<>(comparator);
        ts.addAll(words);

        printHeaderString();
        for (String wo :
                ts) {
            System.out.println(wo);
        }
    }

    public static void printWordsFrequency(List<String> words) {
        //Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле.
        HashMap<String, Integer> wc = new HashMap<>();
        for (String word : words) {
            wc.put(word, wc.containsKey(word) ? wc.get(word) + 1 : 1);
        }

        printHeaderString();
        for (Map.Entry<String, Integer> pair : wc.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }


    public static void printLinesReverse(String lines) {
        //Задание 4: Выведите на экран все строки файла в обратном порядке.
        List<String> list = Arrays.asList(lines.split("\n"));
        Collections.reverse(list);

        printHeaderString();
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = Counter1DifferentWords.class.getResourceAsStream("/ru/sbt/collections/VeryBigText.txt");
        String lines = IOUtils.toString(resourceAsStream, "UTF8");
        List<String> words = getWords(lines);
        Set<String> uniqWordswords = getUniqWords(words);


        printCountWords(uniqWordswords);
        printUniqWords(uniqWordswords);
        printWordsFrequency(words);
        printLinesReverse(lines);

    }
}