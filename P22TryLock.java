import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TryLockThread implements Runnable {
  private Lock lock = new ReentrantLock();

  @Override
  public void run() {
    // lock.tryLock(3, TimeUnit.SECONDS);
    // 等待3秒，如果3秒内没有获取到锁，则返回false
    if (lock.tryLock()) {
      try {
        System.out.println(Thread.currentThread().getName() + " acquired lock");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    } else {
      System.out.println(Thread.currentThread().getName() + " failed to acquire lock");
    }
  }
}

public class P22TryLock {
  public static void main(String[] args) {
    TryLockThread tryLockThread = new TryLockThread();
    Thread t1 = new Thread(tryLockThread, "t1");
    Thread t2 = new Thread(tryLockThread, "t2");
    t1.start();
    t2.start();
  }
}
 