package server_side;

public interface CacheManager {
    public Solution get_solution(Solver s);
    public boolean solution_exists(Solver s);

    public void delete_solution(Solver s);
    public void add_solution(Solver s);
}
