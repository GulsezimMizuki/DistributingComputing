import java.util.LinkedList;
import java.util.Queue;

class RequestThreaderQueue<T> {
    private final Queue<T> queue;

    public RequestThreaderQueue() {
        this.queue = new LinkedList<>();
    }

    // Put element in the queue.
    public synchronized void add(T elem) {
        queue.add(elem);
        notify();
    }

    // Wait for new element in the queue and return it.
    public synchronized T pop() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return this.queue.poll();
    }

    public synchronized int size() {
        return queue.size();
    }
}