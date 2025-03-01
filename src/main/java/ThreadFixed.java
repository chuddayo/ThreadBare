import java.util.HashMap;
import java.util.Set;

public class ThreadFixed extends Thread {

    public ThreadFixed(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("ThreadTwoHashMapB - START "+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            //Get database connection, delete unused data from DB
            doDBProcessing();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThreadTwoHashMapB - END "+Thread.currentThread().getName());
    }

    private void doDBProcessing() throws InterruptedException {
        Thread.sleep(5000);
    }


    // Run this
    public static void main(String[] args){
        runMapOfSize(6);

        runMapOfSize(8);
    }

    private static void runMapOfSize(int size) {
        System.out.println("Constructing HashMap of Size "+size);
        Integer threadCount = size;
        HashMap<String, Thread> threadMap = new HashMap<>();


        for (int i = 0; i < threadCount; i++) {
            threadMap.put("T"+ i, new ThreadTwoHashMap("T"+ i));
        }
        System.out.println("Starting Threads in HashMap");
        Set<String> names = threadMap.keySet();
        for (String name : names) {
            threadMap.get(name).start();
        }
        System.out.println("Thread HashMap, all have been started");
    }
}
