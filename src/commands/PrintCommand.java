package commands;

import java.util.HashMap;

public class PrintCommand implements Command
{
	CommandHandler commandHandler;

	public PrintCommand(CommandHandler commandHandler)
	{
		this.commandHandler = commandHandler;
	}

	@Override
	public void doCommand(String[] strings, HashMap<String, Double> symbolTable)
	{
		System.out.println(commandHandler.simulatorVars.get(commandHandler.symbltablBind.get(strings[1].trim())));

		try
		{
			Thread.sleep(250);
		}
		catch (InterruptedException interruptedException)
		{
			interruptedException.printStackTrace();
		}
	}
}