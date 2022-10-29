import java.io.BufferedReader;
import java.net.Socket;

public class ItemThreader {
    protected Socket socket;
    protected BufferedReader bufferedReader;

    public ItemThreader(Socket socket, BufferedReader bufferedReader) {
        this.socket = socket;
        this.bufferedReader = bufferedReader;
    }
}
