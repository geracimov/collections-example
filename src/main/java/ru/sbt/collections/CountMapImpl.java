package ru.sbt.collections;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T> {
    private Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T o) {
        Integer i;
        if ((i = map.putIfAbsent(o, 1)) != null) {
            map.put(o, i + 1);
        }
    }

    public void add(T o, Integer count) {
        Integer i;
        if ((i = map.putIfAbsent(o, 1)) != null) {
            map.put(o, i + count);
        }
    }

    @Override
    public int getCount(T o) {
        return map.get(o);
    }

    @Override
    public int remove(T o) {
        return map.remove(o);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Map<T, Integer> toMap() {
        return map;
    }

    @Override
    public void addAll(CountMap<T> source) {
        source.toMap().forEach(this::add);
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
        if (destination != null)
            for (Map.Entry<T, Integer> pair :
                    map.entrySet()) {
                destination.put(pair.getKey(), pair.getValue());
            }
    }


    public static void main(String[] args) {
        CountMap<Integer> map = new CountMapImpl<>();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);
        int count = map.getCount(5);   // 2
        count = map.getCount(6);   // 1
        count = map.getCount(10);  // 3

        CountMap<Integer> map2 = new CountMapImpl<>();
        map2.add(5);
        map2.add(6);
        map2.add(5);
        map2.add(11);
        map.addAll(map2);
        map2.add(11);
        Map<Integer, Integer> m = new HashMap<>();
        map.toMap(m);
    }
}
