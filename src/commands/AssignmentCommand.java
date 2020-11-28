package commands;

import expression.ShuntingYard;
import client.Client;
import java.util.HashMap;

public class AssignmentCommand implements Command {

	CommandHandler commandHandler;
	ShuntingYard shuntingYard;
	Client client;

	public AssignmentCommand(CommandHandler commandHandler, Client client)
	{
		this.commandHandler = commandHandler;
		shuntingYard = new ShuntingYard();
		this.client = client;
	}

	@Override
	public void doCommand(String[] stringsArray, HashMap<String, Double> symbolTable)
	{
		stringsArray[0] = stringsArray[0].trim();

		if (!stringsArray[2].equals("bind"))
		{
			if (commandHandler.symbltablBind.containsKey(stringsArray[0]))
			{
				commandHandler.simulatorVars.put(commandHandler.symbltablBind.get(stringsArray[0]), shuntingYard.calc(stringsArray[stringsArray.length - 1], commandHandler).calculate());

				if (client.getSocket() != null)
				{
					client.setPathValue(commandHandler.symbltablBind.get(stringsArray[0].trim()), shuntingYard.calc(stringsArray[stringsArray.length - 1], commandHandler).calculate());
				}
			}
			else
			{
				symbolTable.put(stringsArray[0], shuntingYard.calc(stringsArray[stringsArray.length - 1], commandHandler).calculate());
			}
		}
		else
		{
			commandHandler.symbltablBind.put(stringsArray[0], stringsArray[stringsArray.length - 1]);
		}
	}
}