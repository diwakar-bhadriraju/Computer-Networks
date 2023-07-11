import java.io.*;
import java.net.*;

public class TCPConcurrentServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started. Waiting for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Start a new thread for each client
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String filename = in.readLine();
                File file = new File(filename);

                if (!file.exists() || file.isDirectory()) {
                    out.println("File not found");
                    return;
                }

                FileInputStream fileIn = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = fileIn.read(buffer)) != -1) {
                    clientSocket.getOutputStream().write(buffer, 0, bytesRead);
                }

                fileIn.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
