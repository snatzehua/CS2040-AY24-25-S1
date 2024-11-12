import java.io.*;
import java.util.*;

/**
 * The Node class represents a plank with weight W and length L.
 * This class provides a constructor to create a given plank of weight W and
 * size L, as well as overridden methods to enable storage in the HashMap,
 */
class Node {
    public int W;
    public int L;

    public Node(int W, int L) {
        this.W = W;
        this.L = L;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node other) {
            return (this.W == other.W && this.L == other.L);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(W, L);
    }

    @Override
    public String toString() {
        return String.format("(W: %d, L: %d)", this.W, this.L);
    }
}

/**
 * The NodeComparator class is used to sort Nodes (planks) by ascending length,
 * then descending weight if the planks are of equal length. This allows us to
 * obtain the longest plank (with lightest weight as tiebreaker) and allows us
 * to obtain the shortest plank (with heaviest weight as tiebreaker) when using
 * the floor and ceiling methods of TreeSet.
 */
class NodeComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        if (a.L != b.L) {
            return a.L - b.L;
        }
        if (a.W != b.W) {
            return b.W - a.W;
        }
        return 0;
    }
}

public class planks {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());
        TreeSet<Node> ts = new TreeSet<>(new NodeComparator());
        Map<Node, Integer> map = new HashMap<>();

        // For each operation
        for (int i = 0; i < Q; i += 1) {
            String[] info = br.readLine().split(" ");
            // If operation 'a', add plank p of weight Wp and length Lp
            if (info[0].equals("a")) {
                Node curr = new Node(Integer.parseInt(info[1]), Integer.parseInt(info[2]));
                // TreeSet can only hold unique values (keyword set), so use HashMap to store duplicate counts
                boolean added = ts.add(curr);
                // If duplicate detected (added is false)
                if (!added) {
                    // If this plank is already in the HashMap
                    if (map.containsKey(curr)) {
                        map.put(curr, map.get(curr) + 1);
                    } 
                    // If this plank does not exist in the HashMap (but is already a duplicate, hence set as 2)
                    else {
                        map.put(curr, 2);
                    }
                }
            } 
            // If operation 'b', compute effort required
            else {
                int X = Integer.parseInt(info[1]);
                // Longest plank that does not exceed target length X
                Node A = ts.floor(new Node(0, X));
                // Update TreeSet / HashMap
                if (map.containsKey(A)) {
                    int num = map.get(A);
                    if (num == 1) {
                        map.remove(A);
                        ts.remove(A);
                    } else {
                        map.put(A, num - 1);
                    }
                } else {
                    ts.remove(A);
                }
                // Shortest plank that is greater than or equal to target length X
                Node B = ts.ceiling(new Node(Integer.MAX_VALUE, X));
                // Update TreeSet / HashMap
                if (map.containsKey(B)) {
                    int num = map.get(B);
                    if (num == 1) {
                        map.remove(B);
                        ts.remove(B);
                    } else {
                        map.put(B, num - 1);
                    }
                } else {
                    ts.remove(B);
                }
                System.out.println((1 + (long) A.W + (long) B.W) * (1 + (long) Math.abs(A.L - B.L)));
            }
        }
    }
}
