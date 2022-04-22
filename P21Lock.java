import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockThread implements Runnable {
  private Lock lock = new ReentrantLock();

  @Override
  public void run() {
    lock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + " acquired lock");
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}

public class P21Lock {
  public static void main(String[] args) {
    LockThread lockThread = new LockThread();
    Thread t1 = new Thread(lockThread, "t1");
    Thread t2 = new Thread(lockThread, "t2");
    t1.start();
    t2.start();
  }
}
