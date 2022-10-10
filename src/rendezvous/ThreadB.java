package rendezvous;

import java.util.concurrent.Semaphore;

public class ThreadB extends Thread {
    private String name;
    private Semaphore aArrived;
    private Semaphore bArrived;

    public ThreadB(Semaphore aArrived, Semaphore bArrived){
        this.name = "B";
        this.aArrived = aArrived;
        this.bArrived = bArrived;
    }

    private void b1(){
        System.out.println("b1");
    }

    private void b2(){
        System.out.println("b2");
    }

    public void run(){
        try {
            this.b1();
            long sleepTime = (long) (Math.random() * 1000);
            String msg = String.format(
                "%s will sleep %d milliseconds",
                this.name,
                sleepTime
        );
        System.out.println(msg);
            bArrived.release();
            aArrived.acquire();
            this.b2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
