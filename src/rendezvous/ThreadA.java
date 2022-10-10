package rendezvous;

import java.util.concurrent.Semaphore;

public class ThreadA extends Thread {
    private String name;
    private Semaphore aArrived;
    private Semaphore bArrived;

    public ThreadA(Semaphore aArrived, Semaphore bArrived){
        this.name = "A";
        this.aArrived = aArrived;
        this.bArrived = bArrived;
    }

    private void a1(){
        System.out.println("a1");
    }

    private void a2(){
        System.out.println("a2");
    }

    public void run(){
        try {
            this.a1();
            long sleepTime = (long) (Math.random() * 1000);
            String msg = String.format(
                "%s will sleep %d milliseconds",
                this.name,
                sleepTime
        );
        System.out.println(msg);
            aArrived.release();
            bArrived.acquire();
            this.a2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
