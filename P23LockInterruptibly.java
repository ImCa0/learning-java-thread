import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockInterruptibly implements Runnable {
  private Lock lock = new ReentrantLock();

  @Override
  public void run() {
    // lock()被中断时，会先等待直到获取到锁，再抛出InterruptedException
    // lock.lock();
    try {
      // lockInterruptibly()可以中断锁的等待，如果等待时间内被中断，*立刻*则抛出InterruptedException
      lock.lockInterruptibly();
    } catch (InterruptedException e1) {
      System.out.println(Thread.currentThread().getName() + " interrupted");
      return;
    }
    try {
      System.out.println(Thread.currentThread().getName() + " acquired lock");
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      System.out.println(Thread.currentThread().getName() + " interrupted");
    } finally {
      System.out.println(Thread.currentThread().getName() + " released lock");
      lock.unlock();
    }
  }
}

public class P23LockInterruptibly {
  public static void main(String[] args) throws InterruptedException {
    LockInterruptibly lockInterruptibly = new LockInterruptibly();
    Thread t1 = new Thread(lockInterruptibly, "t1");
    Thread t2 = new Thread(lockInterruptibly, "t2");
    t1.start();
    Thread.sleep(1000);
    t2.start();
    t2.interrupt();
  }
}
