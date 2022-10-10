package rendezvous;

import java.util.concurrent.Semaphore;

public class RendezvousMain {
    public static void main(String[] args) throws InterruptedException {

        Semaphore aArrived = new Semaphore(0);
        Semaphore bArrived = new Semaphore(0);
        ThreadA tA = new ThreadA(aArrived, bArrived);
        ThreadB tB = new ThreadB(aArrived, bArrived);

        tA.start();
        tB.start();

        tA.join();
        tB.join();
    }
}
