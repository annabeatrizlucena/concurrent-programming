package locks.util;

public class Counter {
	
	private int count;
	
	public Counter(int n) {
		this.count = n;
	}

	public void increment() throws InterruptedException {
        long sleepTime = (long) (Math.random() * 1000);
        String msg = String.format(
                "%s will sleep %d milliseconds",
                Thread.currentThread().getName(),
                sleepTime
        );
        System.out.println(msg);
        int tmp = this.count; 
        Thread.sleep(sleepTime);
        this.count = tmp + 1; 
	}
	
	public void decrement() throws InterruptedException {
		long sleepTime = (long) (Math.random() * 1000);
        String msg = String.format(
                "%s will sleep %d milliseconds",
                Thread.currentThread().getName(),
                sleepTime
        );
        System.out.println(msg);
        int tmp = this.count; 
        Thread.sleep(sleepTime);
        this.count = tmp - 1;
	}
	
	public int getCount() {
                long sleepTime = (long) (Math.random() * 1000);
                String msg = String.format(
                "%s will sleep %d milliseconds",
                Thread.currentThread().getName(),
                sleepTime
        );
                System.out.println(msg);
		return this.count;
	}
}
