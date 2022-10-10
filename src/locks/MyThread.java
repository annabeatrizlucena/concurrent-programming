package locks;

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
            this.lock.lock();
            this.counter.increment();
            this.lock.unlock();
            System.out.println(this.name + " - counter: " + this.counter.getCount());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
