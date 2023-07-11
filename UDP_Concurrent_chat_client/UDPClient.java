import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClient implements Runnable {
    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;
    private boolean running;

    public UDPClient(String serverIP, int serverPort) throws SocketException {
        socket = new DatagramSocket();
        try {
            serverAddress = InetAddress.getByName(serverIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.serverPort = serverPort;
    }

    public void run() {
        running = true;
        System.out.println("UDP Client is running.");

        try {
            while (running) {
                Scanner sc = new Scanner(System.in);
                String message = sc.nextLine();
                byte[] requestData = message.getBytes();
                DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, serverAddress,serverPort);
                socket.send(requestPacket);

                byte[] buffer = new byte[1024];
                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(responsePacket);

                String receivedMessage = new String(responsePacket.getData(), 0, responsePacket.getLength());
                System.out.print("Server Sent: ");
                System.out.println(receivedMessage);

                Thread.sleep(1000);
            }

            socket.close();
        }   catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
    }

    public static void main(String[] args) {
        String serverIP = "127.0.0.1"; // Specify the server IP address
        int serverPort = 12345; // Specify the server port number
        try {
            UDPClient client = new UDPClient(serverIP, serverPort);
            Thread clientThread = new Thread(client);
            clientThread.start();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
