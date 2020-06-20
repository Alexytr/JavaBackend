package server;

import java.util.List;

public interface Searchable<T> {

	public State<T> getInitialState();
	Boolean isGoal(State<T> s);
	List<State<T>> getStateNeighbors(State<T> s);
}
