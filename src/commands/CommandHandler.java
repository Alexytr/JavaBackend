package commands;

import client.DataReaderServer;
import client.MyClient;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CommandHandler {

	DataReaderServer server;
	MyClient client;
	public HashMap<String, Command> commandHashMap;
	public ConcurrentHashMap<String, Double> simulatorVars;
	public HashMap<String, String> symbltablBind;
	public HashMap<String, Double> symbolTable;

	public CommandHandler(DataReaderServer server, MyClient client, HashMap<String, Double> symbolTable, HashMap<String, String> symbltablBind, ConcurrentHashMap<String, Double> simulatorVars)
	{
		this.server = server;
		this.client = client;
		this.symbltablBind = symbltablBind;
		this.simulatorVars = simulatorVars;
		this.symbolTable = symbolTable;

		commandHashMap = new HashMap<>();
		commandHashMap.put("var", new DefineVarCommand(this));
		commandHashMap.put("openDataServer", new OpenServerCommand(server));
		commandHashMap.put("connect", new ConnectCommand(client));
		commandHashMap.put("while", new LoopCommand(server, this));
		commandHashMap.put("if", new IfCommand(server, this));
		commandHashMap.put("print", new PrintCommand(this));
		commandHashMap.put("disconnect", new DisconnectCommand(client));
		commandHashMap.put("return", new ReturnCommand(client, this));
		commandHashMap.put("assignment", new AssignmentCommand(this, client));
	}
}