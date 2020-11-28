package commands;

import expression.ShuntingYard;
import client.Client;
import boot.MyInterpreter;
import java.util.HashMap;

public class ReturnCommand implements Command {

	Client client;
	CommandHandler commandHandler;
	ShuntingYard shuntingYard;

	public ReturnCommand(Client client, CommandHandler commandHandler)
	{
		this.client = client;
		this.commandHandler = commandHandler;
		shuntingYard = new ShuntingYard();
	}

	@Override
	public void doCommand(String[] strings, HashMap<String, Double> symbolTable)
	{
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException interruptedException)
		{
			interruptedException.printStackTrace();
		}

		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 1; i < strings.length; i++)
		{
			stringBuilder.append(strings[i]);
		}

		MyInterpreter.returnValue = shuntingYard.calc(stringBuilder.toString(), commandHandler).calculate();
	}
}