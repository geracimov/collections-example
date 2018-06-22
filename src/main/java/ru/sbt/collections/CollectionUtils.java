package ru.sbt.collections;

import java.util.*;

class Animal {
    void feed() {
    }
}

class Pet extends Animal {
    private String name;

    void call() {
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Kitty extends Pet {
    void mew() {
    }
}

class Doge extends Pet {
    void bark() {
    }
}

public class CollectionUtils {

    public static void main(String[] args) {
        List<Kitty> src = new ArrayList<>(Arrays.asList(new Kitty(), new Kitty()));
        Pet p = new Pet();
        p.setName("init");
        List<Pet> dest = new ArrayList<>(Arrays.asList(new Pet(), p, new Pet()));

        addAll(src, dest);
        List<Pet> dest2 = (List<Pet>) newArrayList(dest);
        p.setName("dasdasdasd");
        dest.set(0, null);
        dest.set(1, null);
        dest.set(2, null);


        System.out.println(indexOf(dest2, p));
        List<Pet> dest3 = (List<Pet>) limit(dest2, 4);
        add(dest3, p);
        removeAll(dest3, dest);

        System.out.println(containsAll(dest3, new ArrayList<Pet>(Arrays.asList(p))));
        System.out.println(containsAny(dest3, new ArrayList<Pet>(Arrays.asList(new Pet(), p))));
        Comparator<? super Integer> comp = (o1, o2) -> {
            if (o1 == null && o2 == null) {
                return 0;
            }
            // Если o1 является null, считается что o2 больше
            if (o1 == null) {
                return -1;
            }
            // Если o2 является null, считается что o1 больше.
            if (o2 == null) {
                return 1;
            }

            if (o1.hashCode() == o2.hashCode()) return 0;
            return (Integer) o1 > (Integer) o2 ? 1 : -1;
        };
        List<Integer> r = (List<Integer>) range(Arrays.asList(8, 1, 3, 5, 6, 4), 3, 6, comp);
    }

    // пример параметризации метода и его реализации:
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    //Создать новый List из c вернуть в виде List
    public static <T> List<? super T> newArrayList(List<? extends T> c) {
        List<? super T> ret = new ArrayList<>();
        ret.addAll(c);
        return ret;
    }

    //индекс элемента в листе
    public static <T> int indexOf(List<? extends T> source, Object o) {
        return source.indexOf(o);
    }

    //вернуть лист длиной не более size
    public static <T> List<? super T> limit(List<? extends T> source, int size) {
        List<? super T> ret = new ArrayList<>();
        for (int i = 0; i < source.size() & i < size; i++) {
            ret.add(source.get(i));
        }
        return ret;
    }

    //добавить элемент в конец листа
    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    //удалить все вхождения элемента c2
    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    //true если первый лист содержит все элементы второго
    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    //true если первый лист содержит хотя-бы 1 второго
    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (Object o : c2) {
            if (c1.contains(o)) return true;
        }
        return false;
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
// Элементы сравнивать через Comparable.
// Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T> List<? super T> range(List<? extends T> list, T min, T max) {
     /*   class ListComp<T> extends ArrayList<T> implements Comparable<T> {

            @Override
            public int compareTo(Object o) {
                if (this.hashCode() == o.hashCode()) return 0;
                return this.hashCode() > o.hashCode() ? 1 : -1;

            }
        }
        ListComp<? extends T>  lc = new ListComp<>();
        //if ()
        List<? super T> ret = new ListComp<>();
        for (T t : list) {
            if (t > min && t < max) {

            }
        }
        //return ret;*/
        return null;
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
// Элементы сравнивать через comparator.
// Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T> List<? super T> range(List<? extends T> list, T min, T max,
                                            Comparator<? super T> comparator) {

        List<? super T> res = new ArrayList<>();
        for (T t : list) {
            if (comparator.compare(t, min) >= 0 && comparator.compare(t, max) <= 0) res.add(t);

        }
        return res;
    }
}

