package barriers;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

import locks.mutex.MutexLock;
import locks.util.Counter;
import locks.util.Lock;

public class SimpleBarrierMain {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert the number of Threads to create:");
		int n = scanner.nextInt();
        Thread[] threads = new Thread[n];

        Counter c = new Counter(0); 
        Lock mutex = new MutexLock(1);
        Semaphore barrier = new Semaphore(0);

        for (int i = 0; i < n; i++) {
			BarrierThread thread = new BarrierThread("Thread " + i, n, c, barrier, mutex);
			threads[i] = thread;
        }

        for (Thread t : threads) {
            t.start(); 
        }

        for (Thread t : threads) {
            t.join(); 
        }
        // String msg = String.format(
        //         "Final value of counter: %d",
        //         c.getCount()
        // );
        // System.out.println(msg);


        scanner.close();
    }
}
