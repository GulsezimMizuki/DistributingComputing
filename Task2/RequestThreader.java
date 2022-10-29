import java.io.IOException; 
public class RequestThreader extends Thread {
    private RequestThreaderQueue<ItemThreader> queue;

    public RequestThreader(RequestThreaderQueue<ItemThreader> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            // Get request
            while (true) {
                ItemThreader item = queue.pop();
                System.out.println(item);
                if(item == null) return;
                HttpRequest request = HttpRequest.parse(item.bufferedReader);
                // Process request
                Processor proc = new Processor(item.socket, request);
                proc.process();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}


