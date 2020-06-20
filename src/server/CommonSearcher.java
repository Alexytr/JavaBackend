package server;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public abstract class CommonSearcher<T> implements Searcher<T> {
	//Data
	protected PriorityQueue<State<T>> minHeap;
	private int numberOfNodes;
	
	//Ctor
	public CommonSearcher() {
		this.minHeap = new PriorityQueue<>((s1, s2) -> { return Integer.compare(s1.getsCost(), s2.getsCost());});
		this.numberOfNodes = 0;
	}
	
	//Protected methods
	protected final State<T> pollFromHeap(){
		this.numberOfNodes++;
		return this.minHeap.poll();
	}
	
	protected List<State<T>> backTrace(State<T> goal) {
		Stack<State<T>> stack = new Stack<>();
		List<State<T>> sList = new ArrayList<>();
		
		while(goal.getFather() != null) { //Check if we reached start position
			stack.push(goal);
			goal = goal.getFather();
		}
		stack.push(goal);//Adding the start position
		while(!stack.isEmpty())//while stack is not empty
			sList.add(stack.pop());
		
		this.minHeap.clear();
		return sList;
	}
	
	//Override
	@Override
	public abstract List<State<T>> Search(Searchable<T> searchable);

	@Override
	public final int getNumberOfNodes() { return this.numberOfNodes; }

}
