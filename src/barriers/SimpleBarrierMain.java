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
        SimpleBarrierThread[] threads = new SimpleBarrierThread[n];

        Counter c = new Counter(0); 
        Lock mutex = new MutexLock(1);
        Semaphore barrier = new Semaphore(0);

        for (int i = 0; i < n; i++) {
			SimpleBarrierThread thread = new SimpleBarrierThread("Thread " + i, n, c, barrier, mutex);
			threads[i] = thread;
        }

        for (Thread t : threads) {
            t.start(); 
        }

        for (Thread t : threads) {
            t.join(); 
        }
        scanner.close();
    }
}
