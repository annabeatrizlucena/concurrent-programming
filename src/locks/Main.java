package locks;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insert the number of Threads to create:");
		
		int n = scanner.nextInt();

		Counter count = new Counter();
		Lock mutexLock = new MutexLock(1);
		
		System.out.println(count.getCount());
		mutexLock.lock();
		System.out.println(count.getCount());
		count.increment();
		System.out.println(count.getCount());
		mutexLock.unlock();
		
		
		
		
		
		scanner.close();
//		Lock s = new MutexLock(1);
//		
//		Thread threadA = new Thread();
//		Thread threadB = new Thread();

	}

}
