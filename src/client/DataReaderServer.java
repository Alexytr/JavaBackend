package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class DataReaderServer implements Server
{
	ConcurrentHashMap<String, Double> simulatorVars;
	volatile boolean stopServer;
	Thread serverThread;
	FileName fileNames;

	public DataReaderServer(ConcurrentHashMap<String, Double> simulatorVars, FileName names)
	{
		this.simulatorVars = simulatorVars;
		stopServer = false;
		this.fileNames = names;
	}

	@Override
	public void openServer(int port)
	{
		serverThread = new Thread(() -> {
			try
			{
				ServerSocket server = new ServerSocket(port);
				System.out.println("Server is open");
				String[] name1 = fileNames.getString();

				while (!stopServer)
				{
					Socket client = server.accept();
					System.out.println("Client connected");
					BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
					String line;

					while ((line = reader.readLine()) != null)
					{
						System.out.println("Received: " + line);
						String[] values = line.split(",");

						for (int index = 0; index < name1.length; index++)
						{
							simulatorVars.put(name1[index], Double.parseDouble(values[index]));
						}
					}

					client.getInputStream().close();
					client.close();
				}

				server.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		});
		serverThread.start();
	}

	@Override
	public void stop() {

		stopServer = true;
	}

	@Override
	public Thread getThread() {

		return serverThread;
	}
}