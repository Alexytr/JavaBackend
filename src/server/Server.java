package server;

public interface Server {
    public void start(ClientHandler c) throws Exception;
    public void stop();
}
