package server;

import java.util.HashMap;
import java.util.Map;

public class FileCacheManager<ProblemT, SolutionT> implements CacheManager<ProblemT, SolutionT> {
    private Map<ProblemT, SolutionT> map;

    public FileCacheManager() {
        map = new HashMap<>();
    }

    @Override
    public SolutionT solution_exists(ProblemT p) {
        return map.get(p);
    }
    @Override
    public void add_solution(ProblemT p, SolutionT s) {
        map.put(p, s);
    }
}
