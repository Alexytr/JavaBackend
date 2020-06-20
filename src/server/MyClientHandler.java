package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MyClientHandler implements ClientHandler {
	private final SearcherAdapter<Searchable<SPosition>,List<State<SPosition>>,SPosition> solver;
	private final CacheManager<FindGoalInMatrix,String> cacheManager;

	public MyClientHandler() {
		this.solver = new SearcherAdapter<>(new BestFirstSearch<SPosition>());
		this.cacheManager = new FileCacheManager<FindGoalInMatrix, String>();
	}

	private String createSolution(List<State<SPosition>> solverResult) {
		StringBuilder solution = new StringBuilder();

		for(int i = 0 ; i< solverResult.size()-1 ;i++) {
			State<SPosition> current = solverResult.get(i);
			State<SPosition> next = solverResult.get(i + 1);

			int row = current.getsValue().getRow() - next.getsValue().getRow();
			int column = current.getsValue().getCol() - next.getsValue().getCol();

			if (row == 1){
				solution.append("Up");
			}
			else if (row == -1){
				solution.append("Down");
			}
			else if (column == 1){
				solution.append("Left");
			}
			else if (column == -1) {
				solution.append("Right");
			}

			if (i+1 != solverResult.size() - 1){
				solution.append(",");
			}
		}

		return solution.toString();
	}
	
	//Override
	@Override
	public void handleClient(InputStream in, OutputStream out) throws IOException {
		BufferedReader inFromAClient = new BufferedReader(new InputStreamReader(in));
		PrintWriter outToAClient = new PrintWriter(out);
		List<int[]> matrixCreator = new ArrayList<>();

		GetFirstInformationFromClient(inFromAClient, matrixCreator); // get first info
		SPosition start = GetInformationFromClient(inFromAClient); // get second info
		SPosition goal = GetInformationFromClient(inFromAClient); // get third info

		FindGoalInMatrix problem = new FindGoalInMatrix(matrixCreator, start, goal); // get the problem
		
    	String solution = Solve(problem); // solve

		SendSolutionToClient(outToAClient, solution);
		Close(inFromAClient, outToAClient);
	}

	private void GetFirstInformationFromClient(BufferedReader inFromAClient, List<int[]> matrixCreator) throws IOException {
		String line;
		while(!(line = inFromAClient.readLine()).equals("end")) {
			matrixCreator.add(Arrays.asList(line.split(",")).stream().mapToInt(Integer::parseInt).toArray());
		}
	}

	private SPosition GetInformationFromClient(BufferedReader inFromAClient) throws IOException {
		String line;
		line = inFromAClient.readLine();
		int[] goalArr = Arrays.asList(line.split(",")).stream().mapToInt(Integer::parseInt).toArray();
		return new SPosition(goalArr[0], goalArr[1]);
	}

	private void Close(BufferedReader inFromAClient, PrintWriter outToAClient) throws IOException {
		inFromAClient.close();
		outToAClient.close();
	}

	private void SendSolutionToClient(PrintWriter outToAClient, String solution) {
		outToAClient.println(solution);
		outToAClient.flush();
	}

	private String Solve(FindGoalInMatrix problem) {
		String solution = this.cacheManager.solution_exists(problem);
		if (solution == null) {
			List<State<SPosition>> solverResult = this.solver.solve(problem);
		    solution = createSolution(solverResult);
		    cacheManager.add_solution(problem, solution);
		}

		return solution;
	}
}
