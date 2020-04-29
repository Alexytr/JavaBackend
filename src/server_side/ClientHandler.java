package server_side;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {
    public abstract void handleClient(InputStream in, OutputStream out);
}
