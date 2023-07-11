import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter filename: ");
            String filename = reader.readLine();

            out.println(filename);

            String response = in.readLine();

            if (response.equals("File not found")) {
                System.out.println("File not found on the server.");
            } else {
                FileOutputStream fileOut = new FileOutputStream(filename);
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = socket.getInputStream().read(buffer)) != -1) {
                    fileOut.write(buffer, 0, bytesRead);
                }

                fileOut.close();
                System.out.println("File downloaded successfully.");
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
