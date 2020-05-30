package boot;
import server.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        Server s = new MySerialServer(Integer.parseInt(args[0]));
        s.start(new MyTestClientHandler());
    }
}
