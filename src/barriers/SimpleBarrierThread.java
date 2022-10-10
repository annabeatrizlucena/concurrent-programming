package barriers;

import java.util.concurrent.Semaphore;
import locks.util.Lock;

import locks.util.Counter;

public class SimpleBarrierThread extends Thread{
    private String name; 
    private int n;
    private Counter counter;
    private Semaphore barrier;
    private Lock mutexLock;

    public SimpleBarrierThread(String name, int n, Counter counter, Semaphore barrier, Lock mutex){
        this.name = name;
        this.n = n;
        this.counter = counter;
        this.barrier = barrier;
        this.mutexLock = mutex;
    }

    public void run(){
        try {
            this.mutexLock.lock();
            this.counter.increment();
            this.mutexLock.unlock();

            if(this.counter.getCount() == this.n) {
                System.out.println(this.name + " - counter: " + this.counter.getCount());
                System.out.println("Barrier will unlock");
                barrier.release();
            } else{
            System.out.println(this.name + " - counter: " + this.counter.getCount());
            }
            barrier.acquire();
            System.out.println(this.name + " was liberated");
            barrier.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        

    }
    
}
