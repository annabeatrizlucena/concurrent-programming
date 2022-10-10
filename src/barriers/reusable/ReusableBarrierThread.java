package barriers.reusable;

import java.util.concurrent.Semaphore;

import locks.util.Counter;
import locks.util.Lock;

public class ReusableBarrierThread extends Thread{

    private String name; 
    private int n;
    private Counter counter;
    private Semaphore turnstile;
    private Semaphore turnstile2;
    private Lock mutexLock;
    
    public ReusableBarrierThread(String name, int n, Counter counter, Semaphore turnstile, Semaphore turnstile2, Lock mutex){
        this.name = name;
        this.n = n;
        this.counter = counter;
        this.turnstile = turnstile;
        this.turnstile2 = turnstile2;
        this.mutexLock = mutex;
    }

    public void run(){
        try {
            this.mutexLock.lock();
            this.counter.increment();

            if(this.counter.getCount() == this.n) {
                System.out.println(this.name + " - counter: " + this.counter.getCount());
                System.out.println("Barrier first step will unlock");
                this.turnstile2.acquire();
                this.turnstile.release();
            } else{
                System.out.println(this.name + " - counter: " + this.counter.getCount());
            }
            this.mutexLock.unlock();

            this.turnstile.acquire();
            System.out.println(this.name + " was liberated");
            this.turnstile.release();

            this.mutexLock.lock();
            this.counter.decrement();

            if(this.counter.getCount() == 0) {
                System.out.println(this.name + " - counter: " + this.counter.getCount());
                this.turnstile.acquire();
                this.turnstile2.release();
            } else{
                System.out.println(this.name + " - counter: " + this.counter.getCount());
            }
            this.mutexLock.unlock();

            this.turnstile2.acquire();
            this.turnstile2.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
