package ru.sbt.collections;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Параметризовать CountMap и реализовать его.
 */
public class CountMapImpl<T> implements CountMap<T> {
    private Map<T, Integer> map = new HashMap<>();
    private String field1;

    public void setMap( Map<T, Integer> map ) {
        this.map = map;
    }

    public void setField1( String field1 ) {
        this.field1 = field1;
    }

    @Override
    public void add( T o ) {
        if ( o == null ) return;
        map.merge( o, 1, Integer::sum );
    }

    private void add( T o, Integer count ) {
        if ( o == null ) return;
        map.merge( o, count, Integer::sum );
    }

    @Override
    public int getCount( T o ) {
        if ( o == null ) throw new NullPointerException();
        return map.get( o );
    }

    @Override
    public int remove( T o ) {
        if ( o == null ) throw new NullPointerException();
        int count = map.get( o );
        map.remove( o );
        return count;
    }

    @Override
    public int size( ) {
        return map.size();
    }

    @Override
    public Map<T, Integer> toMap( ) {
        return new HashMap<>( map );
    }

    @Override
    public void addAll( CountMap<T> source ) {
        if ( source == null ) return;
        source.toMap().forEach( this::add );
    }

    @Override
    public void toMap( Map<T, Integer> destination ) {
        if ( destination == null ) return;
        for ( Map.Entry<T, Integer> pair :
                map.entrySet() ) {
            destination.put( pair.getKey(), pair.getValue() );
        }
    }


    public static void main( String[] args ) throws NoSuchFieldException {
        ////////////
//        CountMap<Integer> cmi = new CountMapImpl<>();
//        Type t = cmi.getClass().getDeclaredField( "map" ).getGenericType();
//        if ( t instanceof ParameterizedType ) {
//            System.out.println( t);
//            ParameterizedType pt = (ParameterizedType) t;
//            System.out.println( pt );
//            for ( Type type : pt.getActualTypeArguments() ) {
//                Class typeClass = (Class) type;
//                System.out.println(typeClass);
//            }
//        }
//
//        System.exit( 0 );
        ////////////
        CountMap<Integer> map = new CountMapImpl<>();
        map.add( 10 );
        map.add( 10 );
        map.add( 5 );
        map.add( 6 );
        map.add( 5 );
        map.add( 10 );
        map.add( 10 );
        int count = map.getCount( 5 );   // 2
        count = map.getCount( 6 );   // 1
        count = map.getCount( 10 );  // 3

        CountMap<Integer> map2 = new CountMapImpl<>();
        map2.add( 5 );
        map2.add( 6 );
        map2.add( 5 );
        map2.add( 11 );
        map.addAll( map2 );
        map2.add( 11 );
        Map<Integer, Integer> m = new HashMap<>();
        map.toMap( m );
    }
}
