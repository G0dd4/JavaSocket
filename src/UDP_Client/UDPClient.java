package UDP_Client;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPClient {
    private final String ipAddr;
    private final int port;
    public static void main(String[] args) {
        String ipAddr = "localhost";
        int port = 8080;

        if(args.length > 2){
            ipAddr = args[1];
            port = Integer.parseInt(args[2]);
        }

        UDPClient client = new UDPClient(ipAddr, port);

        try {
            client.launch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public UDPClient(String ipAddr, int port) {
        this.ipAddr = ipAddr;
        this.port = port;
    }

    public void launch() throws IOException {


        Scanner scanner = new Scanner(System.in);
        String keyboardString = scanner.nextLine();

        InetAddress address = InetAddress.getByName(ipAddr);

        DatagramPacket request = new DatagramPacket(keyboardString.getBytes(StandardCharsets.UTF_8), keyboardString.length(), address, port);

        DatagramSocket socket = new DatagramSocket();
        socket.send(request);

        socket.close();
    }

}