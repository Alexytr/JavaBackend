package server_side;

public class FileCacheManager implements CacheManager {
    public Solution get_solution(Solver s) {
        // TODO: get existing solution to db
        Solution solution = new Solution();

        return solution;
    }
    public void delete_solution(Solver s) {
        // TODO: delete solver from db
    }
    public void add_solution(Solver s) {
        // TODO: add new solution to db
    }
    public boolean solution_exists(Solver s) {
        // TODO: check solution for the problem
        return false;
    }
}
