package UDP_Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    private final int port;
    public static void main(String[] args) {

        int port = 8080;
        if(args.length > 1){
            port = Integer.parseInt(args[1]);
        }

        UDPServer server = new UDPServer(port);

        try{
            server.launch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public UDPServer(int port) {
        this.port = port;
    }
    public void launch() throws IOException {
        byte[] buffer = new byte[1024];

        DatagramPacket clientPacket = new DatagramPacket(buffer, buffer.length);
        DatagramSocket serverSocket = new DatagramSocket(port);
        serverSocket.receive(clientPacket);

        System.out.println("Client : "+clientPacket.getAddress()+":"
                +clientPacket.getPort()+" send : "
                +new String(clientPacket.getData(),0, clientPacket.getLength()));

        serverSocket.close();
    }
}