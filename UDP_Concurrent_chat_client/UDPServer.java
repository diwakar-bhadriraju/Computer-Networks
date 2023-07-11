import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;
public class UDPServer implements Runnable {
    private DatagramSocket socket;
    private boolean running;

    public UDPServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void run() {
        running = true;
        System.out.println("UDP Server is running.");

        try {
            while (running) {
                Scanner sc = new Scanner(System.in);
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                System.out.print("Client Sent: ");
                System.out.println(receivedMessage);
                String replyy = sc.nextLine();
                String responseMessage = replyy;
                byte[] responseData = responseMessage.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length,packet.getAddress(), packet.getPort());
                socket.send(responsePacket);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
    }

    public static void main(String[] args) {
        int port = 12345; // Specify the desired port number
        try {
            UDPServer server = new UDPServer(port);
            Thread serverThread = new Thread(server);
            serverThread.start();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
