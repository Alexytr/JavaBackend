package boot;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import commands.CommandHandler;
import interpreter.Lexer;
import interpreter.Parser;
import client.DataReaderServer;
import client.MyClient;

public class MyInterpreter {

	public static MyClient client;
	public static Double returnValue;

	public static int interpret(String[] lines)
	{
		client = new MyClient();
		HashMap<String, Double> symbolTable = new HashMap<>();
		HashMap<String, String> symbolTableBind = new HashMap<>();
		ConcurrentHashMap<String, Double> simulatorVars = new ConcurrentHashMap<>();
		simulatorVars.put("simX", (double) 0);
		simulatorVars.put("simY", (double) 0);
		simulatorVars.put("simZ", (double) 0);
		DataReaderServer server = new DataReaderServer(simulatorVars, () -> new String[] { "simX", "simY", "simZ" });

		CommandHandler ch = new CommandHandler(server, client, symbolTable, symbolTableBind, simulatorVars);
		Lexer lexer = new Lexer();
		Parser parser = new Parser(ch, client);

		for (String string : lines)
		{
			parser.Parser(lexer.Lexer(string));
		}

		server.stop();
		return returnValue.intValue();
	}
}