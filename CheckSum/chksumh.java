import java.util.Scanner;
import java.lang.String;
public class chksumh {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int arr_size = 0;
		if (sc.hasNextInt()) {
			arr_size = sc.nextInt();
		}
		String[] arr = new String[arr_size];
		int[] arr_hex = new int[arr_size];
		for (int i = 0; i < arr_size; i++) {
			if (sc.hasNext()) {
				arr[i] = sc.next();
			}
		}
		sc.close();
	}
}
