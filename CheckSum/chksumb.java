import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class chksumb {
      public static String sum(String b1, String b2) {
    int len1 = b1.length();
    int len2 = b2.length();
    int carry = 0;
    String res = "";
    // the final length of the result depends on the bigger length between b1
    // and b,
    // (also the value of carry, if carry = 1, add "1" at the head of result,
    // otherwise)
    int maxLen = 8;
    for (int i = 0; i < maxLen; i++) {

      // start from last char of String b1 and b2
      // notice that left side is an int and right side is char
      // so we need to minus the decimal value of '0'
      int p = i < len1 ? b1.charAt(len1 - 1 - i) - '0' : 0;
      int q = i < len2 ? b2.charAt(len2 - 1 - i) - '0' : 0;
      int tmp = p + q + carry;
      carry = tmp / 2;
      res = tmp % 2 + res;
    }
    return (carry == 0) ? res : "1" + res;
  }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] string = new String [sc.nextInt()];
        sc.nextLine();
        for(int i = 0; i < string.length; i++){
            string[i] = sc.nextLine();
        }
        String f0 = sum(string[0],string[1]);
        String f1 = sum(string[2],string[3]);
        System.out.println(f0);
        System.out.println(f1);
    }
}
