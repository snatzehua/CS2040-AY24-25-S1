import java.util.Scanner;

public class peasoup {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int restaurants = sc.nextInt();
        sc.nextLine();
        // For each restaurant
        while (restaurants > 0) {
            int menu = sc.nextInt();
            sc.nextLine();
            String name = sc.nextLine();
            boolean hasPancakes = false;
            boolean hasPeaSoup = false;
            // For each menu item
            for (int i = 0; i < menu; i += 1) {
                String item = sc.nextLine();
                // If pancakes found
                if (item.equals("pancakes")) {
                    hasPancakes = true;
                    // Early termination if pea soup previously found
                    if (hasPeaSoup == true) {
                        System.out.println(name);
                        return;
                    }
                }
                // If pea soup found
                if (item.equals("pea soup")) {
                    hasPeaSoup = true;
                    // Early termination if pancakes previously found
                    if (hasPancakes == true) {
                        System.out.println(name);
                        return;
                    }
                }
            }
            restaurants -= 1;
        }
        // If restaurant does not have both pancakes and pea soup
        System.out.println("Anywhere is fine I guess");
        sc.close();
    }
}
