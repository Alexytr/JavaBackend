package commands;

import expression.ShuntingYard;

import java.util.HashMap;

public class DefineVarCommand implements Command
{
	CommandHandler commandHandler;
	ShuntingYard shuntingYard;

	public DefineVarCommand(CommandHandler commandHandler)
	{
		this.commandHandler = commandHandler;
		shuntingYard = new ShuntingYard();
	}

	@Override
	public void doCommand(String[] strings, HashMap<String, Double> symbolTable)
	{
		if (strings.length == 2)
		{
			symbolTable.put(strings[1], null);
		}
		else if (strings[3].contains("bind") || strings[2].contains("bind"))
		{
			commandHandler.symbltablBind.put(strings[1].trim(), (strings[strings.length - 1].trim()));
		}
		else if (strings[2].equals("="))
		{
			symbolTable.put(strings[1].trim(), (shuntingYard.calc(strings[3], commandHandler)).calculate());
		}
	}
}