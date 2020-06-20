package server;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;


public class BestFirstSearch<T> extends CommonSearcher<T> {

	// gets the cost of the given node
	private int getCost(Iterator<State<T>> it, State<T> state ) {
	    int t = 0;
		while(it.hasNext()) {
			State<T> temp = it.next();

			if(temp.equals(state)) {
				t = temp.getsCost();
			}
		}

		return t;
	}
	
	// implements BFS search
	@Override
	public List<State<T>> Search(Searchable<T> searchable) {
		LinkedHashSet<State<T>> visitedSet = new LinkedHashSet<>(); // A set of states already evaluated
		super.minHeap.add(searchable.getInitialState()); 			// A priority queue of states to be evaluated
		
		while(!super.minHeap.isEmpty()) {
			State<T> head = super.pollHeap();
			visitedSet.add(head);
			if(searchable.isGoal(head))
				return super.backTrace(head);
			List<State<T>> successors = searchable.getStateNeighbors(head);
			for(State<T> successor : successors) 
				if(!super.minHeap.contains(successor) && !visitedSet.contains(successor)) {
					successor.setFather(head);
					super.minHeap.add(successor);
				}else if(!visitedSet.contains(successor) && successor.getsCost() < this.getCost(super.minHeap.iterator(), successor) ) {
					super.minHeap.remove(successor);
					super.minHeap.add(successor);
				}	
		}
		return null;
	}
}
