package commands;

import client.DataReaderServer;
import java.util.HashMap;

public class OpenServerCommand implements Command
{
	DataReaderServer server;

	public OpenServerCommand(DataReaderServer server)
	{
		this.server = server;
	}

	@Override
	public void doCommand(String[] strings, HashMap<String, Double> symbolTable)
	{
		int port = Integer.parseInt(strings[1]);
		server.openServer(port);
	}
}