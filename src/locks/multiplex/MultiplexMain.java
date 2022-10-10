package locks.multiplex;

import java.util.Scanner;

import locks.util.Counter;
import locks.util.Lock;
import locks.util.MyThread;

public class MultiplexMain {
	public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert the number of Threads to create:");
		int number_of_threads = scanner.nextInt();
        Thread[] threads = new Thread[number_of_threads];

		System.out.println("Insert the number of Threads to exec critical section at the same time:");
        int n = scanner.nextInt();

        Lock multiplexLock = new MultiplexLock(n);
        Counter counter = new Counter(5);


        for (int i = 0; i < number_of_threads; i++) {
			Thread thread = new MyThread("Thread " + i, counter, multiplexLock);
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
