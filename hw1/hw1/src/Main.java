import lists.ArrayListCustom;

import java.util.ArrayList;
import java.util.Collection.*;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> c = new ArrayList<>();
        c.add(1);
        c.add(5);
        ArrayListCustom<Integer> a = new ArrayListCustom<>();
        a.add(0, 5);
        a.add(1, 2);
        a.add(2, 7);
        a.addAll(c);
        for (int i = 0; i < a.getSize(); i++) {
            System.out.println(a.get(i));
        }
        Comparator<Integer> v = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };
        a.sort(v);
        System.out.println("Результат сортировки: ");
        for (int i = 0; i < a.getSize(); i++) {
            System.out.println(a.get(i));
        }
    }
}