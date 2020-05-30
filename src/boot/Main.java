package boot;
import server.*;

public class Main {
    public static void main(String[] args) {
        CacheManager<String, String> cm = new FileCacheManager<>();
        StringReverser r = new StringReverser();
        MySerialServer s = new MySerialServer(Integer.parseInt(args[0]));
        s.start(new MyTestClientHandler(r, cm));
    }
}
