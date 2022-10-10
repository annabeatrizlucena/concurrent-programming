package locks.util;

public class MyThread extends Thread{
    
    private String name;
    private Counter counter;
    private Lock lock;

    public MyThread(String name, Counter counter, Lock lock) {
        this.name = name;
        this.counter = counter;
        this.lock = lock;
    }

    public void run(){
        try{
            if(lock.toString().equals("mutex")){
                this.lock.lock();
                this.counter.increment();
                this.lock.unlock();
                System.out.println(this.name + " - counter: " + this.counter.getCount());
            } else if(lock.toString().equals("multiplex")){
                this.lock.lock();
                System.out.println(this.name + " - counter: " + this.counter.getCount());
                this.lock.unlock();

            }

            
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
