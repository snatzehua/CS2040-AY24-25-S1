import java.io.*;
import java.util.*;

public class conformity {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    
    HashMap<String, Integer> map = new HashMap<>();
    // For each "Frosh"
    for (int i = 0; i < n; i += 1) {
      String[] info = br.readLine().split(" ");
      int[] mods = new int[5];
      // Convert inputs to integer array and sort array
      for (int j = 0; j < 5; j += 1) {
        int curr = Integer.parseInt(info[j]);
        mods[j] = curr;
      }
      Arrays.sort(mods);
      // Count number of this unique combination (using String and HashMap) 
      String f = Arrays.toString(mods);
      if (map.containsKey(f)) {
        map.put(f, map.get(f) + 1);
      } else {
        map.put(f, 1);
      }
    }
    // Obtain most common combination
    int count = 0;
    int max = 0;
    for (int value : map.values()) {
      if (max < value) {
        count = 1;
        max = value;
      } else if (max == value) {
        count += 1;
      }
    }
    System.out.println(count * max);
  }
}
