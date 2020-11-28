package commands;

import client.Client;

import java.util.HashMap;

public class ConnectCommand implements Command
{
	Client client;

	public ConnectCommand(Client client)
	{
		this.client = client;
	}

	@Override
	public void doCommand(String[] strings, HashMap<String, Double> symbolTable)
	{
		String ip = strings[1];
		int port = Integer.parseInt(strings[2]);
		client.connect(ip, port);
	}
}