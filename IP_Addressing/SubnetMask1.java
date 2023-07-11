import java.util.Scanner;
public class SubnetMask1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] parts = input.split("/");
        String ip = parts[0];
        int prefix = Integer.parseInt(parts[1]);
        int mask = -1 << (32 - prefix);
        String[] ipParts = ip.split("\\.");
        int ipNum = 0;
        for (int i = 0; i < 4; i++) {
            ipNum |= Integer.parseInt(ipParts[i]) << (24 - (8 * i));
        }
        int network = ipNum & mask;
        int lastAddress = network | ~mask;
        int numberOfDevices = (int) Math.pow(2, 32 - prefix);
        for (int i = 3; i >= 0; i--) {
            System.out.print((network >> (i * 8)) & 0xFF);
            if (i > 0) {
                System.out.print(".");
            }
        }
        System.out.println();
        for (int i = 3; i >= 0; i--) {
            System.out.print((lastAddress >> (i * 8)) & 0xFF);
            if (i > 0) {
                System.out.print(".");
            }
        }
        System.out.println();
        System.out.println(numberOfDevices);
    }
}
