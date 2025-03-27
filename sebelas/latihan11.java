package sebelas;

import java.util.*;

public class latihan11 {
    // Deklarasi PriorityQueue untuk menyimpan string
    static PriorityQueue<String> stringQueue;

    public static void main(String[] args) {
        // Inisialisasi PriorityQueue
        stringQueue = new PriorityQueue<>();
        
        // Menambahkan elemen ke dalam PriorityQueue
        stringQueue.add("ab");
        stringQueue.add("abcd");
        stringQueue.add("abc");
        stringQueue.add("a");

        // Menghapus dan mencetak elemen dari PriorityQueue dalam urutan prioritas
        while (!stringQueue.isEmpty()) {
            System.out.println(stringQueue.remove());
        }
    }
}
