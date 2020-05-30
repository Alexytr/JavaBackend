package boot;
import server.*;

public class Main {
    public static void main(String[] args) {
        MySerialServer s = new MySerialServer(Integer.parseInt(args[0]));
        s.start(new MyTestClientHandler());
    }
}
