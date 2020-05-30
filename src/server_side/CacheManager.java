package server_side;

public interface CacheManager<ProblemT, SolutionT> {
//    public Solution get_solution(Solver s);
    public SolutionT solution_exists(ProblemT p);

//    public void delete_solution(Solver s);
    public void add_solution(ProblemT p, SolutionT s);
}
