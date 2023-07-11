import java.io.*;
import java.net.*;

public class Server {
    static int ccount(String s){
        int l = s.length();
        return l;
    }
    static int wcount(String s){
        String words[]=s.split("\\s"); 
        int w = words.length;
        return w;
    }
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(6666);
            Socket s = server.accept();
            DataInputStream datain = new DataInputStream(s.getInputStream());
            DataOutputStream dataout = new DataOutputStream(s.getOutputStream());
            String str = datain.readUTF();
            dataout.writeInt(ccount(str));
            dataout.writeInt(wcount(str));
            datain.close();
            dataout.flush();
            dataout.close();
            server.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
