package client;

public interface Server {

	void openServer(int port);

	void stop();

	Thread getThread();
}