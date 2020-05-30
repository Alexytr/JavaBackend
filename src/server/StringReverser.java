package server;

public class StringReverser implements Solver<String, String> {

    @Override
    public String solve(String p) {

        StringBuilder b = new StringBuilder(p);
        b.reverse();
        return b.toString();

    }
}
