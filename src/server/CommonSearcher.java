package server;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public abstract class CommonSearcher<T> implements Searcher<T> {
	protected PriorityQueue<State<T>> minHeap;
	private int numberOfNodes;
	
	public CommonSearcher() {

		this.minHeap = new PriorityQueue<>((s1, s2) -> { return Integer.compare(s1.getsCost(), s2.getsCost());});
		this.numberOfNodes = 0;
	}
	
	protected final State<T> pollHeap(){
		this.numberOfNodes++;
		State<T> head = this.minHeap.poll();
		return head;
	}
	
	protected List<State<T>> backTrace(State<T> g) {
		Stack<State<T>> s = new Stack<>();
		List<State<T>> sList = new ArrayList<>();
		
		while(g.getFather() != null) { //Check if we reached start position
			s.push(g);
			g = g.getFather();
		}
		s.push(g);
		while(!s.isEmpty())//while stack is not empty
		{
		    State<T> a = s.pop();
			sList.add(a);
		}
		this.minHeap.clear();
		return sList;
	}
	
	@Override
	public abstract List<State<T>> Search(Searchable<T> searchable);

	@Override
	public final int getNumberOfNodes() { return this.numberOfNodes; }

}
