import java.util.*;
import java.io.*;

/**
 * The Edge class represents an edge between two nodes u and v with weight of weight.
 * It has a constructor that takes in u, v and weight to construct an Edge object.
 */
class Edge {
    public int u;
    public int v;
    public int weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
}

public class lostmap {
    // Classic function to get root with flattening, similar to UFDS
    public static int getRoot(int[] roots, int item) {
        if (roots[item] == item) {
            return item;
        }
        roots[item] = getRoot(roots, roots[item]);
        return roots[item];
    }
    /**
     * Since there are minimum number of roads, but all villages are connected, there are n - 1 roads.
     * For each village, there is minimum one connection, and that connection will always be the smallest
     * distance in that village's list of distances to other villages.
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int n = Integer.parseInt(br.readLine());

        // Create PriorityQueue of edges, sorted by ascending weight
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(e -> e.weight));
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                pq.add(new Edge(i, j, Integer.parseInt(line[j])));
            }
        }
        // Create array where array[i] points to the root of i
        int[] roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
        // Maintain counter of connections / roads, stopping either when pq is empty or sufficient roads
        int counter = 0;
        while (counter < n - 1 && !pq.isEmpty()) {
            Edge curr_edge = pq.poll();
            int u = curr_edge.u;
            int v = curr_edge.v;
            int root_u = getRoot(roots, u);
            int root_v = getRoot(roots, v);
            // If roots are not connected yet (of the same set / root)
            if (root_u != root_v) {
                counter++;
                roots[root_u] = root_v;
                // Print smaller village number first
                if (u < v) {
                    pw.println((u + 1) + " " + (v + 1));
                } else {
                    pw.println((v + 1) + " " + (u + 1));
                }
            }
        }
        br.close();
        pw.close();
    }
}
