package bonus;

import java.util.concurrent.*;

/**
 * bonus part
 * ThreadPoolExecutor class:
 *  - creates a (large) number of concurrent tasks,
 *  each requiring a database connection in order
 *  to perform various SQL operations on the database.
 */
public class ThreadPoolExecutor {
    private int tasks;

    public ThreadPoolExecutor(int tasks) {
        this.tasks = tasks;
    }

    public void start() {
        java.util.concurrent.ThreadPoolExecutor threadPoolExecutor = (java.util.concurrent.ThreadPoolExecutor) Executors.newFixedThreadPool(tasks);

        int i = 0;
        while (i < tasks) {
            ThreadPoolMain newThread = new ThreadPoolMain();
            threadPoolExecutor.execute(newThread);
            i++;
        }

        threadPoolExecutor.shutdown();
    }
}