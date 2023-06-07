package TCPServerMinMax;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerMinMax {
    private final int port;

    public static void main(String[] args){

        int port = 8080;
        if(args.length > 1){
            port = Integer.parseInt(args[1]);
        }

        TCPServerMinMax server = new TCPServerMinMax(port);

        try{
            server.launch();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public TCPServerMinMax(int port){
        this.port = port;
    }

    public void launch() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {

            while (true) {
                Socket client = serverSocket.accept();

                System.out.println("user with : "
                        + client.getInetAddress() +
                        ":"
                        + client.getPort() +
                        " connected");
                ClientHandler clientSocket = new ClientHandler(client);

                new Thread(clientSocket).start();

            }
        }

    }
}


