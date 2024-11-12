import java.io.*;

public class islands3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] base_info = br.readLine().split(" ");
        int r = Integer.parseInt(base_info[0]);
        int c = Integer.parseInt(base_info[1]);

        char[][] grid = new char[r][c];
        // Create grid
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                grid[i][j] = (char) br.read();
            }
            br.read();
        }

        int result = 0;
        // Check how many land masses there are. Read comment for flip function
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 'L') {
                    flip(grid, r, c, j, i);
                    result += 1;
                }
            }
        }
        System.out.println(result);
    }

    /** 
     * Function flips all connected land masses or clouds into water, which means that the entire island
     * is flipped. Since the entire island is flipped, subsequent checks will not detect land or clouds
     * from the same island, and subsequent flip calls will only be on other islands, with each island only
     * receiving one flip call. Hence, number of initial flip calls corresponds to number of islands.
     */
    public static void flip(char[][] grid, int r, int c, int x, int y) {
        if (x < 0 || x >= c || y < 0 || y >= r) {
            return;
        }
        if (grid[y][x] == 'L' || grid[y][x] == 'C') {
            // Flipping to 'W' marks the land or cloud as visited (does not count for subsequent calls)
            grid[y][x] = 'W';
            // Check adjacent cells
            flip(grid, r, c, x, y - 1);
            flip(grid, r, c, x + 1, y);
            flip(grid, r, c, x, y + 1);
            flip(grid, r, c, x - 1, y);
        }
    }
}
