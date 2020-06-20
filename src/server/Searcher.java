package server;

import java.util.List;

public interface Searcher <T>{
public List<State<T>> Search(Searchable<T> s);
public int getNumberOfNodes();
}
