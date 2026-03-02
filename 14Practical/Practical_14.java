//Joshua Scholtz 4397173
import java.util.*;
import java.lang.Math;
import java.util.Random;

public class Practical_14 {
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

        }

}
}

