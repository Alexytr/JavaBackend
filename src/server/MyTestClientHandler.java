package server;

import java.io.*;

public class MyTestClientHandler implements ClientHandler {
	private Solver<String,String> solver;
	private CacheManager<String,String> cm;

	public MyTestClientHandler() {
		this.solver = new StringReverser();
		this.cm = new FileCacheManager();
	}

	//Getters
	public CacheManager<String,String> getCm() { return cm; }
	public Solver<String,String> getSolver() {	return solver; }
	//Setters
	public void setCm(CacheManager<String,String> cm) { this.cm = cm; }
	public void setSolver(Solver<String,String> solver) { this.solver = solver; }

	//Override
	@Override
	public void handleClient(InputStream in, OutputStream out) throws IOException{
		//throws IOEexception incase the buffer coudnt read from the client
		BufferedReader client = new BufferedReader(new InputStreamReader(in));
		PrintWriter outToAClient = new PrintWriter(out);

		String problem;
		while(!(problem = client.readLine()).equals("end")) { // Reading until "end"
			String solution = cm.solution_exists(problem); // If Allready solved return the result else null
			if(solution == null) { // If the cache does not possess the solution
				solution = solver.solve(problem);//Solve
				cm.add_solution(problem, solution);// Add the new solution to the cache
			}
			outToAClient.println(solution);//send to client
			outToAClient.flush();
		}
		client.close();
		outToAClient.close();

	}

}
