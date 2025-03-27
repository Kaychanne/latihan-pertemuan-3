package lat9;

import java.util.*;

public class latihan9 {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("4");
        arrayList.add("2");
        arrayList.add("5");
        arrayList.add("3");

        Vector v = new Vector();
        v.add("A");
        v.add("B");
        v.add("D");
        v.add("E");
        v.add("F");
        System.out.println("Vector contains: "  + v);
        Collections.copy(v, arrayList);

        System.out.println("After Copy, Vector Contains : " + v);
    }
}

