package locks;

import java.util.concurrent.Semaphore;

public class MutexLock implements Lock{
	
	
	private Semaphore semaphore;
	
	public MutexLock(int n) {
		this.semaphore = new Semaphore(1);
	}
	
	@Override
	public void lock() {
		try {
			this.semaphore.acquire();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void unlock() {
		this.semaphore.release();
	}
}
