package server_side;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MySerialServer implements Server {

    private volatile boolean stop = false;

    private void open(int port, ClientHandler c, ServerSocket server) throws Exception {

        while(!stop) {
            try {
                Socket client = server.accept();
                try {
                    c.handleClient(client.getInputStream(), client.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                    client.close();
                }
            }
            catch (SocketTimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    public void start(int port, ClientHandler c) {

       new Thread(() -> {
           ServerSocket server = null;
           try {
               server = new ServerSocket(port);
               server.setSoTimeout(10000);
               open(port, c, server);
               server.close();

           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       );
    }
    public void stop() {
        this.stop = true;
    }
}
