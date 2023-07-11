import java.io.*;
import java.net.*;
public class Server {
    public static void main(String[] args) {
        int serverPort = 12345;
        try {
            DatagramSocket serverSocket = new DatagramSocket(serverPort);
            BufferedReader fileReader = new BufferedReader(new FileReader("ip_mac_mappings.txt"));
            String line;
            StringBuilder ipMacMappings = new StringBuilder();
            while ((line = fileReader.readLine()) != null) {
                ipMacMappings.append(line).append("\n");
            }
            fileReader.close();
            byte[] receiveData = new byte[1024];
            byte[] sendData;
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                String ipAddress = new String(receivePacket.getData()).trim();
                String macAddress = "Unknown";
                String[] mappings = ipMacMappings.toString().split("\n");
                for (String mapping : mappings) {
                    String[] parts = mapping.split(",");
                    if (parts.length == 2 && parts[0].trim().equals(ipAddress)) {
                        macAddress = parts[1].trim();
                        break;
                    }
                }
                sendData = macAddress.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
