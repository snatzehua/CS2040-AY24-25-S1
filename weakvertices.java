import java.util.*;
import java.io.*;

public class weakvertices {
    // Function checks if node a is part of a triangle
    public static boolean hasTriangle(HashSet<Integer>[] map, int a) {
        // For all nodes connected to a, check if b shares a common node c with a
        for (int b : map[a]) {
            for (int c : map[b]) {
                if (map[c].contains(a)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        // For each graph
        while (!(line = br.readLine()).equals("-1") && !line.trim().isEmpty()) {
            int V = Integer.parseInt(line);
            // Create HashSet array of connected nodes
            HashSet<Integer>[] map = new HashSet[V];
            for (int i = 0; i < V; i += 1) {
                map[i] = new HashSet<>();
            }
            // Populate HashSet array using adjacency matrix
            for (int i = 0; i < V; i += 1) {
                String[] input = br.readLine().split(" ");
                for (int j = i; j < V; j += 1) {
                    if (input[j].equals("1")) {
                        map[i].add(j);
                        map[j].add(i);
                    }
                }
            }
            // Print all nodes that are weak vertices
            boolean none = true;
            for (int i = 0; i < V; i += 1) {
                if (!hasTriangle(map, i)) {
                    if (!none) {
                        System.out.print(" ");
                    }
                    System.out.print(i);
                    none = false;
                }
            }
            System.out.println();
        }
    }
}
