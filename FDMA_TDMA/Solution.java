import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String btsString = scanner.nextLine();
        String bchString = scanner.nextLine();
        String bgString = scanner.nextLine();
        int n = Integer.parseInt(scanner.nextLine());
        String[] btarray = btsString.split(" ");
        String[] bcharray = bchString.split(" ");
        String[] bgarray = bgString.split(" ");
        double bt = Double.parseDouble(btarray[0]);
        double bch = Double.parseDouble(bcharray[0]);
        double bg = Double.parseDouble(bgarray[0]);
        int users = (int) (bt/ (bch + bg));
        System.out.println(users * n);
    }
}
