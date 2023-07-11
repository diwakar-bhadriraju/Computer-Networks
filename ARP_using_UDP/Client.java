import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args) {
        String serverIP = "127.0.0.1";
        int serverPort = 12345;
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(serverIP);
            System.out.print("Enter IP address: ");
            String ipAddress = userInput.readLine();
            byte[] sendData = ipAddress.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String macAddress = new String(receivePacket.getData()).trim();
            System.out.println("MAC Address: " + macAddress);
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
