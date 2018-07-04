package ru.sbt.collections;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Подсчитайте количество различных слов в файле.
 */
public class Counter1DifferentWords {
    private static final String REGEXP_SPLIT_LINE = "\n";
    private static final String REGEXP_SPLIT_STRING = "[\b ,.;:()«»\\r\\n\\t\\[\\]—\\d[0-9]]";

    private static List<String> getLines( String lines ) {
        return Arrays.asList( lines.split( REGEXP_SPLIT_LINE ) );
    }

    private static List<String> getWords( String lines ) {
        return Arrays.asList( lines.toLowerCase().split( REGEXP_SPLIT_STRING, -1 ) );
    }

    private static Set<String> getUniqWords( List<String> list ) {
        Set<String> resultSet = new HashSet<>( list );
        resultSet.remove( "" );
        return resultSet;
    }

    private static void printHeaderString( ) {
        System.out.println( "--- " + Thread.currentThread().getStackTrace()[2].getMethodName()
                + " -----------------------------------------------------------------------" );
    }

    /**
     * Задание 1: Подсчитайте количество различных слов в файле.
     */
    public static void printCountWords( String lines ) {
        if ( lines == null ) return;
        printHeaderString();
        System.out.println( "Количество слов в списке: " + getWords( lines ).size() );
    }

    /**
     * Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины.
     */
    public static void printWords( String lines ) {
        if ( lines == null ) return;
        Comparator<String> comparator = ( o1, o2 ) -> {
            int ret = o1.length() > o2.length() ? 1 : -1;
            return ret;
        };

        TreeSet<String> ts = new TreeSet<>( comparator );
        ts.addAll( getUniqWords( getWords( lines ) ) );

        printHeaderString();
        for ( String wo :
                ts ) {
            System.out.println( wo );
        }
//        System.arraycopy(  );
    }

    /**
     * Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле.
     */
    public static void printWordsFrequency( String lines ) {
        if ( lines == null ) return;
        HashMap<String, Integer> wc = new HashMap<>();
        for ( String word : getWords( lines ) ) {
//            wc.put(word, wc.containsKey(word) ? wc.get(word) + 1 : 1);
            wc.merge( word, 1, Integer::sum );
        }

        printHeaderString();
        for ( Map.Entry<String, Integer> pair : wc.entrySet() ) {
            System.out.println( pair.getKey() + " " + pair.getValue() );
        }
    }


    /**
     * Задание 4: Выведите на экран все строки файла в обратном порядке.
     */
    public static void printLinesReverse( String lines ) {
        if ( lines == null ) return;
        List<String> strings = Arrays.asList( lines.split( "\n" ) );
        Collections.reverse( strings );

        printHeaderString();
        for ( String s : strings ) {
            System.out.println( s );
        }
    }

    /**
     * Задание 5: Реализуйте свой Iterator для обхода списка cлов в обратном порядке.
     */
    public static class ReverseList<T> implements Iterable<T> {
        private List<T> innerlist;
        private int current;

        public ReverseList( List<T> list ) {
            this.innerlist = list;
            this.current = list.size() - 1;
        }

        public Iterator<T> iterator( ) {
            return new Iterator<T>() {
                @Override
                public boolean hasNext( ) {
                    return -1 < current;
                }

                @Override
                public T next( ) {
                    return innerlist.get( current-- );
                }
            };
        }
    }

    public static void printReverseList( String lines ) {
        if ( lines == null ) return;

        ReverseList<String> rlist = new ReverseList<>( getWords( lines ) );

        printHeaderString();
        for ( String s : rlist ) {
            System.out.println( s );
        }
    }

    /**
     * Задание 6: Выведите на экран строки, номера которых задаются пользователем в произвольном порядке.
     */
    public static void printSpecificLines( String lines, int[] numLines ) {
        if ( lines == null || numLines == null ) return;

        printHeaderString();
        for ( int i :
                numLines ) {
            System.out.println( getLines( lines ).get( i ) );
        }
    }

    public static void main( String[] args ) throws IOException {
        InputStream resourceAsStream = Counter1DifferentWords.class.getResourceAsStream( "/ru/sbt/collections/VeryBigText.txt" );
        String lines = IOUtils.toString( resourceAsStream, "UTF8" );
        resourceAsStream.close();

        printCountWords( lines );
        printWords( lines );
        printWordsFrequency( lines );
        printLinesReverse( lines );
        printReverseList( lines );
        printSpecificLines( lines, new int[]{ 0, 2, 4, 6, 8 } );
    }
}