//Joshua Scholtz 4397173
import java.util.*;
public class Openhash {
    public static void main(String[] args) {
        int N = 1 << 20;
        String[] keys = new String[N];

        class Pair {
            String key;
            String value;

            Pair(String key, String value) {
                this.key = key;
                this.value = value;
            }

        }
        Pair[] data = new Pair[N];


        // Step 1: generate keys
        for (int i = 0; i < N; i++) {
            keys[i] = "key" + i;
        }

        // Step 2: shuffle keys
        List<String> list = Arrays.asList(keys);
        Collections.shuffle(list);
        list.toArray(keys);


       // Step 3: assign values based on shuffled order
        for (int i = 0; i < N; i++) {
            String value = Integer.toString(i + 1);
            data[i] = new Pair(keys[i], value);
        }


        class Entry {
            String key;
            String value;

            Entry(String key, String value) {
                this.key = key;
                this.value = value;
            }



            public class openHash {

                private Entry[] table;
                private int m;   // table size

                public openHash(int m) {
                    this.m = m;
                    table = new Entry[m];
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
                void insert(String key, String value) {
                    int i = hash(key);
                    while (table[i] != null) {
                        i = (i + 1) % m;
                    }
                    table[i] = new Entry(key, value);
                }

            //C) method String lookup(key)
                public String lookup(String key) {
                    int i = hash(key);

                    while (table[i] != null) {
                        if (table[i].key.equals(key)) {
                            return table[i].value;
                        }
                        i = (i + 1) % m;
                    }

                    return null;  // key not found
                }

             //D) method String remove(key)
                public String remove(String key) {
                    int i = hash(key);

                    while (table[i] != null) {
                        if (table[i].key.equals(key)) {
                            String val = table[i].value;// mark as deleted
                            return val;
                        }
                        i = (i + 1) % m;
                    }

                    return null;  // key not found
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
                        if (table[i] != null) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            public static long timeInsert(openHash table, Pair[] data) {
                long start = System.currentTimeMillis();

                for (int i = 0; i < data.length; i++) {
                    table.insert(data[i].key, data[i].value);
                }

                long end = System.currentTimeMillis();
                long F = end - start;
                return F;
            }

            public static long timeLookup(openHash table, Pair[] data, int trials) {
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
