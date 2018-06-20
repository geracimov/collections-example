package ru.sbt.collections;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Consumer;

/**
 * Подсчитайте количество различных слов в файле.
 */
public class Counter1DifferentWords {
    private static final String REGEXP_SPLIT_STRING = "[\b ,.;:()\\r\\n\\t\\[\\]—\\d[0-9]]";

    private static List<String> getWords(String lines) {
        return Arrays.asList(lines.toLowerCase().split(REGEXP_SPLIT_STRING));
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

    /**
     * Задание 1: Подсчитайте количество различных слов в файле.
     */
    public static void printCountWords(Set<String> words) {
        printHeaderString();
        System.out.println("Количество слов в списке: " + words.size());
    }

    /**
     * Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины.
     */
    public static void printWords(Set<String> words) {
        Comparator<String> comparator = (o1, o2) -> {
            int ret = 0;
            // если включить проверку на равенство ниже, то получим список слов с уникальной длинной,
            // в противном случае получаем список всех строк из вх множества words, отсортированных по длинне строки
            //if (o1.length() != o2.length())
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

    /**
     * Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле.
     */
    public static void printWordsFrequency(List<String> words) {
        HashMap<String, Integer> wc = new HashMap<>();
        for (String word : words) {
            wc.put(word, wc.containsKey(word) ? wc.get(word) + 1 : 1);
        }

        printHeaderString();
        for (Map.Entry<String, Integer> pair : wc.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }


    /**
     * Задание 4: Выведите на экран все строки файла в обратном порядке.
     */
    public static void printLinesReverse(List<String> strings) {
        Collections.reverse(strings);

        printHeaderString();
        for (String s : strings) {
            System.out.println(s);
        }
    }

    /**
     * Задание 5: Реализуйте свой Iterator для обхода списка cлов в обратном порядке.
     */
    public static class ReverseList<T> implements Iterable<T> {
        private List<T> innerlist;
        private int current;

        public ReverseList(List<T> list) {
            this.innerlist = list;
            this.current = list.size() - 1;
        }

        public Iterator<T> iterator() {
            return new Iterator<T>() {
                @Override
                public boolean hasNext() {
                    return -1 < current;
                }

                @Override
                public T next() {
                    return innerlist.get(current--);
                }
            };
        }
    }

    public static void printReverseList(List<String> list) {
        ReverseList<String> rlist = new ReverseList<>(list);
        for (String s : rlist) {
            System.out.println(s);
        }
    }

    /**
     * Задание 6: Выведите на экран строки, номера которых задаются пользователем в произвольном порядке.
     */
    public static void printSpecificLines(List<String> strings, int[] numlines) {
        printHeaderString();
        for (int i :
                numlines) {
            System.out.println(strings.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = Counter1DifferentWords.class.getResourceAsStream("/ru/sbt/collections/VeryBigText.txt");
        String lines = IOUtils.toString(resourceAsStream, "UTF8");
        List<String> strings = Arrays.asList(lines.split("\n"));
        List<String> words = getWords(lines);
        Set<String> uniqWordswords = getUniqWords(words);

        printCountWords(uniqWordswords);
        printWords(uniqWordswords);
        printWordsFrequency(words);
        printLinesReverse(strings);
        printReverseList(words);
        printSpecificLines(strings, new int[]{0, 2, 4, 6, 8});
    }
}