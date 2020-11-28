package commands;

import client.Client;

import java.util.HashMap;

public class DisconnectCommand implements Command
{
	Client client;

	public DisconnectCommand(Client client)
	{
		this.client = client;
	}

	@Override
	public void doCommand(String[] strings, HashMap<String, Double> symbolTable)
	{
		client.disconnect();
	}
}