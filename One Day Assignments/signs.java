import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class signs {
  public static void main(String[] args) throws Exception {
    // Faster input
    BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(b.readLine());
    // Add all words into array
    String[] s = new String[n];
    for (int i = 0; i < n; i += 1) {
      s[i] = b.readLine();
    }
    // Sort word array lexicographically by the middle letter(s)
    Arrays.sort(s, Comparator.comparing(a -> a.substring((a.length() - 1) / 2, (a.length() / 2) + 1)));
    for (int i = 0; i < n; i += 1) {
      System.out.println(s[i]);
    }
  }
}
