package TCP_Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    private final int port;
    private final String ipAddr;
    public static void main(String[] args){
        String ipAddr = "localhost";
        int port = 8080;

        if(args.length > 2){
            ipAddr = args[1];
            port = Integer.parseInt(args[2]);
        }

        TCPClient client = new TCPClient(ipAddr,port);

        try {
            client.launch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public TCPClient(String ipAddr, int port){
        this.port = port;
        this.ipAddr = ipAddr;
    }

    public void launch() throws IOException {


        try (Socket socket = new Socket(ipAddr, port)) {

            String keyboardString;
            do {
                Scanner scanner = new Scanner(System.in);
                keyboardString = scanner.nextLine();

                OutputStream out = socket.getOutputStream();
                PrintStream outSocker = new PrintStream(out);
                outSocker.println(keyboardString);

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = in.readLine();
                System.out.println(line);

            } while (!keyboardString.equals("exit"));
        }


    }
}


