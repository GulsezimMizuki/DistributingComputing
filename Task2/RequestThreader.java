import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RequestThreader extends Thread {
    private Socket socket;
    public RequestThreaderQueue<BufferedReader> queue;

    public RequestThreader(Socket socket, RequestThreaderQueue<BufferedReader> queue) {
        this.socket = socket;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            // Get request
            while (true) {
                BufferedReader input = queue.pop();
                if(input == null) return;
                HttpRequest request = HttpRequest.parse(input);
                // Process request
                long startTime = System.nanoTime();
                Processor proc = new Processor(socket, request);
                proc.process();
                long endTime = System.nanoTime();
                System.out.println("Processor worked: " + ((float) (endTime - startTime) / 1000000000) + " seconds");
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}


