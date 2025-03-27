package lat3;

import java.util.HashSet;
import java.util.Set;

public class latihan3 {
    public static void main(String[] args) {
        String[] words = {"magenta", "red", "white", "blue", "cyan"};
        Set<String> uniques = new HashSet<String> ();
        Set<String> dups = new HashSet<String>();
        for(String a : words)
            if (!uniques.add(a))
            dups.add(a);
        uniques.removeAll(dups);
        System.out.println("warna: " + uniques);
        System.out.println("warna yang dihapus: " +dups);
    }
}
//yang 1 menggunakan 1 hash set dan mendeteksi duplikasi saat menambah kata baru
// yang 2 menggunakan 2 hast set, satu untuk kata unik dan satu untuk kata yang terduplikasi
//yg 1 mencetak kata yang terdetek sbagai duplikat
//yg 2 mencetak kata unik tanpa yg terduplikasi dan daftar kata yang terduplikasi