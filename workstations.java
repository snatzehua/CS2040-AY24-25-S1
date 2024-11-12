import java.io.*;
import java.util.*;

public class workstations {
  /**
   * Main idea is to check for each researcher b if there is a workstation that 
   * is still unlocked after usage by researcher a, which can be represented by 
   * (arrival time of b) < (departure time of a) + m, where m is the number of
   * minutes of inactivity after which a workstation locks itself.
   *
   * This is represented using two PriorityQueues (needed since timings may not
   * be inputted in order) to represent the timeline of events, with one queue
   * being used for the arrival times and the other queue being used for locks
   */
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] base_info = br.readLine().split(" ");
    int n = Integer.parseInt(base_info[0]);
    int m = Integer.parseInt(base_info[1]);

    // pq stores arrival times of researchers
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    // q stores workstation lock times
    PriorityQueue<Integer> q = new PriorityQueue<>();
    for (int i = 0; i < n; i += 1) {
      String[] info = br.readLine().split(" ");
      int a = Integer.parseInt(info[0]);
      int s = Integer.parseInt(info[1]);
      pq.add(a);
      q.add(a + s);
    }

    int unlocks_saved = 0;

    // While there's still more researchers coming
    while (!pq.isEmpty()) {
      int curr = pq.poll();
      // Remove all workstations in q that have been locked by the time of current researcher's arrival
      while (!q.isEmpty() && q.peek() < curr - m) {
        q.poll();
      }
      // Check if there's still a workstation that is left unlocked
      if (!q.isEmpty() && q.peek() <= curr) {
        unlocks_saved += 1;
        // Remove that unlocked workstation from list
        q.poll();
      }
    }
    System.out.println(unlocks_saved);
  }
}
