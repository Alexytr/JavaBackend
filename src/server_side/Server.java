package server_side;

import java.io.IOException;
import java.net.SocketException;

public interface Server {
    public void start(int port, ClientHandler c) throws IOException, Exception;
    public void stop();
}
