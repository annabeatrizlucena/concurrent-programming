package locks.multiplex;

import java.util.concurrent.Semaphore;

import locks.util.Lock;

public class MultiplexLock implements Lock{

    private Semaphore semaphore;

    public MultiplexLock(int n) {
        this.semaphore = new Semaphore(n);
    }

    @Override
    public void lock() {
        try {
            this.semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void unlock() {
        this.semaphore.release();
    }

    @Override
    public String toString(){
        return "multiplex";
    }
    
}
