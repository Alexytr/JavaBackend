package server;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;


public class BestFirstSearch<T> extends CommonSearcher<T> {

	//private methods
	private int getCost(Iterator<State<T>> it, State<T> state ) {
		while(it.hasNext()) {
			State<T> temp = it.next();
			if(temp.equals(state))
				return temp.getsCost();
		}
		return 0;
	}
	
	//Override
	@Override
	public List<State<T>> Search(Searchable<T> searchable) {
		LinkedHashSet<State<T>> visitedSet = new LinkedHashSet<>(); // A set of states already evaluated
		super.minHeap.add(searchable.getInitialState()); // A priority queue of states to be evaluated
		
		while(!super.minHeap.isEmpty()) {
			State<T> n = super.pollFromHeap();
			visitedSet.add(n);
			if(searchable.isGoalState(n))
				return super.backTrace(n);
			List<State<T>> successors = searchable.getStateNeighbors(n);
			for(State<T> successor : successors) 
				if(!super.minHeap.contains(successor) && !visitedSet.contains(successor)) {
					successor.setFather(n);
					super.minHeap.add(successor);
				}else if(!visitedSet.contains(successor) && successor.getsCost() < this.getCost(super.minHeap.iterator(), successor) ) {
					super.minHeap.remove(successor);
					super.minHeap.add(successor);
				}	
		}
		return null;
	}
}
