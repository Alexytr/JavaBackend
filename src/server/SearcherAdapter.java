package server;


public class SearcherAdapter<Problem , Solution , SType> implements Solver<Problem , Solution> {
	private Searcher<SType> searcher;
	
	public SearcherAdapter(Searcher<SType> searcher)
	{
		this.searcher = searcher;
	}
		
	@Override
	public Solution solve(Problem p)
	{
		return (Solution) this.searcher.Search((Searchable<SType>) p);
	}
	
}
