import java.util.Scanner;
public class FirstLastUsers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int numberOfSubnets = scanner.nextInt();
        String[] parts = input.split("/");
        int prefix = Integer.parseInt(parts[1]);
        int newPrefix = prefix;
        while ((Math.pow(2, newPrefix - prefix)) < numberOfSubnets) {
            newPrefix++;
        }
        int mask = -1 << (32 - newPrefix);
        for (int i = 3; i >= 0; i--) {
            System.out.print((mask >> (i * 8)) & 0xFF);
            if (i > 0) {
                System.out.print(".");
            }
        }
        System.out.println();
        System.out.println((int) Math.pow(2, 32 - newPrefix));
    }
}
