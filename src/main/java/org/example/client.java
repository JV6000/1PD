package org.example;
import java.io.*;
import java.net.Socket;

public class client {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 8080;

        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("PRISIJUNGTA.");

            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


            StringBuilder xmlContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                xmlContent.append(line).append("\n");
            }
            System.out.println("gavo is :\n" + xmlContent);


            reader.close();
            inputStream.close();
            socket.close();
            System.out.println("prisijungimas uzdarytas.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
