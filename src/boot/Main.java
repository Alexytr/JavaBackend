package boot;
import server_side.*;

public class Main {
    public static void main(String[] args) {
        CacheManager<String, String> cm = new FileCacheManager<>();
        StringReverser r = new StringReverser();
        MySerialServer s = new MySerialServer();
        s.start(Integer.parseInt(args[0]), new MyTestClientHandler(r, cm));
    }
}
