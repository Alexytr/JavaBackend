package server;

import java.io.IOException;

public interface Server {
    public void start(ClientHandler c) throws IOException, Exception;
    public void stop();
}
