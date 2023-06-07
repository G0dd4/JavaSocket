package TCP_Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private final int port;

    public static void main(String[] args){

        // initialization
        int port = 8080;
        if(args.length > 1){
            port = Integer.parseInt(args[1]);
        }

        TCPServer server = new TCPServer(port);

        try {
            server.launch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public TCPServer(int port) {
        this.port = port;
    }
    public void launch() throws IOException {

        ServerSocket serverSocket = new ServerSocket(this.port);

        Socket client = serverSocket.accept();

        PrintWriter out =  new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String line;

        System.out.println("user with : "
                + client.getInetAddress() +
                ":"
                + client.getPort()+
                " connected");

        do{
            line = in.readLine();

            // writing the received message from
            // client
            System.out.println("Client : "
                    +client.getInetAddress()+
                    ":"
                    +client.getPort()+
                    " send : "+line
            );

            if(line.equals("exit")){
                out.println("byebye Client : "+client.getInetAddress()+":"+client.getPort());
            }
            else {
                out.println(line.toUpperCase());
            }
        }while(!line.equals("exit"));

        System.out.println("Client : "
                +client.getInetAddress()+
                ":"
                +client.getPort()+
                " disconnected"
        );

        out.close();
        in.close();
        client.close();

    }

}

