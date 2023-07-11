import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 6666);
            DataInputStream datain = new DataInputStream(client.getInputStream());
            DataOutputStream dataout = new DataOutputStream(client.getOutputStream());
            String s = "Hey, this is diwakar, from VIT";
            dataout.writeUTF(s);
            int l = datain.readInt();
            int w = datain.readInt();
            System.out.println("Length of the string " + "\"" + s + "\"" + " is "  + l);
            System.out.println("Words in the string " + "\"" + s + "\"" + " are " + w);
            datain.close();
            dataout.flush();
            dataout.close();
            client.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
