package barriers;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

import locks.mutex.MutexLock;
import locks.util.Counter;
import locks.util.Lock;

public class ReusableBarrierMain {

    public static void main(String[] args) throws InterruptedException  {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert the number of Threads to create:");
		int n = scanner.nextInt();

        System.out.println("Insert the number of barrier runs");
		int numberOfExecutions = scanner.nextInt();

        ReusableBarrierThread[] threads = new ReusableBarrierThread[n];

        Counter c = new Counter(0); 
        Lock mutex = new MutexLock(1);
        Semaphore turnstile = new Semaphore(0);
        Semaphore turnstile2 = new Semaphore(1);

        for (int j = 0; j < numberOfExecutions; j++ ){
            System.out.println();
            System.out.println("Execution number " + (j + 1));
            System.out.println();
            for (int i = 0; i < n; i++) {
                ReusableBarrierThread thread = new ReusableBarrierThread("Thread " + i, n, c, turnstile, turnstile2, mutex);
                threads[i] = thread;
            }
    
            for (Thread t : threads) {
                t.start(); 
            }
    
            for (Thread t : threads) {
                t.join(); 
            }
        }

        scanner.close();
    }
    
}
