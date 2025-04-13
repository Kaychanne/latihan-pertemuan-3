

import java.util.*;

public class pertemuan3 {
    public static void main(String[] args) {
        Set<String> s1 = new HashSet<>();
        s1.add("magenta");
        s1.add("red");
        s1.add("white");
        s1.add("blue");
        s1.add("cyan");

        // membuat set baru yang bernama warna dihapus
        Set<String> warnaDihapus = new HashSet<>();
        warnaDihapus.add("red");
        warnaDihapus.add("white");
        warnaDihapus.add("blue");

        // Menghapus warna dari s1
        s1.removeAll(warnaDihapus);
        
        // Menampilkan hasil setelah penghapusan
        print("Warna setelah penghapusan", s1);
    }

    protected static void print(String label, Collection<String> c) {
        System.out.println("----" + label + "----");
        for (String item : c) {
            System.out.println(item);
        }
    }
}
