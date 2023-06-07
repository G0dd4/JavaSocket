package TCPServerMinMax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler  implements Runnable{
    private final Socket clientSocket;
    public ClientHandler(Socket socket){
        clientSocket = socket;
    }


    @Override
    public void run() {
        try {
            //création des Input / Output stream

            PrintWriter out =  new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line;
            do{

                line = in.readLine();

                // writing the received message from
                // client
                System.out.println("Client : "
                        +clientSocket.getInetAddress()+
                        ":"
                        +clientSocket.getPort()+
                        " Send : "+line
                        );

                if(line.equals("exit")){
                    out.println("bye bye Client : "+clientSocket.getInetAddress()+":"+clientSocket.getPort());
                }
                else {
                    out.println(line.toUpperCase());
                }
            }while(!line.equals("exit"));
            System.out.println("Client : "
                    +clientSocket.getInetAddress()+
                    ":"
                    +clientSocket.getPort()+
                    " disconnected"
            );

            out.close();
            in.close();
            clientSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
