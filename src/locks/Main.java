package locks;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insert the number of Threads to create:");
		
		int n = scanner.nextInt();

        Thread[] threads = new Thread[n];

        Counter c = new Counter(); 
        Lock lock = new MutexLock(1);

        for (int i = 0; i < n; i++) {
			Thread thread = new MyThread("Thread " + i, c, lock);
			threads[i] = thread;
        }

        for (Thread t : threads) {
            t.start(); 
        }

        for (Thread t : threads) {
            t.join(); 
        }
        String msg = String.format(
                "Final value of counter: %d",
                c.getCount()
        );
        System.out.println(msg);
		scanner.close();
    }
}



