package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        try {

            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started. Listening on port 8080...");


            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection accepted from " + clientSocket.getInetAddress().getHostAddress());

                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {

                OutputStream outputStream = clientSocket.getOutputStream();
                PrintWriter out = new PrintWriter(outputStream, true);

                File file = new File("student.xml");
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder xmlContent = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    xmlContent.append(line).append("\n");
                }
                br.close();
                out.println(xmlContent.toString());
                out.flush();

                out.close();
                outputStream.close();
                clientSocket.close();
                System.out.println("XML file sent to the client.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
