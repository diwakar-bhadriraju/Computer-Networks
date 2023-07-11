import java.util.Scanner;
public class SubnetMask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("/");
        int devices = sc.nextInt();
        int bits = (int) Math.ceil(Math.log(devices) / Math.log(2));
        int mask = 32 - bits;
        int[] subnetMask = new int[4];
        for (int i = 0; i < 4; i++) {
            if (mask >= 8) {
                subnetMask[i] = 255;
                mask -= 8;
            } else {
                subnetMask[i] = (int) (256 - Math.pow(2, 8 - mask));
                mask = 0;
            }
        }
        System.out.println(subnetMask[0] + "." + subnetMask[1] + "." + subnetMask[2] + "." + subnetMask[3]);
    }
}
