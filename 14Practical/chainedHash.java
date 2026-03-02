//Joshua Scholtz 4397173
import java.util.*;
public class chainedHash {
    public static void main(String[] args) {
        int N = 1 << 20;
        String[] keys = new String[N];

        class Node {
            String key;
            String value;
            Node next;

            Node(String key, String value) {
                this.key = key;
                this.value = value;
                this.next = null;
            }


                private Node[] table;
                private int m;

                void ChainedHash(int m) {
                    this.m = m;
                    table = new Node[m];
                }




            //A) scatter function int hash(String key)
            int hash(String key) {
                int h = 0;
                int a = 31;
                for (int i = 0; i < key.length(); i++) {
                    h = a * h + key.charAt(i);
                }
                return Math.abs(h) % m + 1;
            }

            //B) method for inserting a (key, value) pair or ≡ (key, data)
            public void insert(String key, String value) {
                int i = hash(key);
                Node head = table[i];

                if (head == null) {
                    table[i] = new Node(key, value);
                    return;
                }

                Node current = head;
                while (current != null) {
                    if (current.key.equals(key)) {
                        current.value = value;
                        return;
                    }
                    if (current.next == null) break;
                    current = current.next;
                }

                current.next = new Node(key, value);  // append
            }

            //C) method String lookup(key)
            public String lookup(String key) {
                int i = hash(key);
                Node current = table[i];

                while (current != null) {
                    if (current.key.equals(key)) {
                        return current.value;
                    }
                    current = current.next;
                }

                return null;
            }


            //D) method String remove(key)
            public String remove(String key) {
                int i = hash(key);
                Node current = table[i];
                Node prev = null;

                while (current != null) {
                    if (current.key.equals(key)) {
                        String val = current.value;
                        if (prev == null) {
                            table[i] = current.next;  // remove head
                        } else {
                            prev.next = current.next;
                        }
                        return val;
                    }
                    prev = current;
                    current = current.next;
                }

                return null;
            }


            //E) Predicates
            public boolean isInTable(String key) {
                return lookup(key) != null;
            }


            public boolean isFull() {
                for (int i = 0; i < m; i++) {
                    if (table[i] == null) {
                        return false;
                    }
                }
                return true;
            }

            public boolean isEmpty() {
                for (int i = 0; i < m; i++) {
                    if (table[i] != null)
                        return false;
                }
                return true;
            }
            public static long timeInsert(chainedHash table, Node[] data) {
                long start = System.currentTimeMillis();

                for (int i = 0; i < data.length; i++) {
                    table.insert(data[i].key, data[i].value);
                }

                long end = System.currentTimeMillis();
                long F = end - start;
                return F;
            }

            public static long timeLookup(chainedHash table, Node[] data, int trials) {
                Random rand = new Random();
                long start = System.currentTimeMillis();

                for (int i = 0; i < trials; i++) {
                    int index = rand.nextInt(data.length);
                    table.lookup(data[index].key);
                }

                long end = System.currentTimeMillis();
                long F = end - start;
                return F;
            }
        }

    }
}
