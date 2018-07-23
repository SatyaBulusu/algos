import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophersNoDeadLock extends Thread {
    int bites = 10;
    ChopStick left, right;

    public DiningPhilosophersNoDeadLock() {
        left = new ChopStick();
        right = new ChopStick();
    }

    public void eat() {
        pick();
        // eat and chew
        putDown();
    }

    public void pick() {
        // pickLeft
        left.pickUp();
        // pickRight
        right.pickUp();
    }

    public void putDown() {
        left.putDown();
        right.putDown();
    }

    public void run() {
        for (int i = 0; i < bites; i++) {
            eat();
        }
    }
}

class ChopStick {
    Lock lock;

    // if chopstick is picked, then acquire lock
    public ChopStick() {
        lock = new ReentrantLock();
    }

    public void pickUp() {
        lock.lock();
    }

    public void putDown() {
        lock.unlock();
    }
}
