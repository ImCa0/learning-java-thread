import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

class UnreentrantLock implements Lock {
  private Thread owner;

  @Override
  public void lock() {
    Thread me = Thread.currentThread();
    synchronized (this) {
      while (owner != null) {
        try {
          wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      owner = me;
    }
  }

  @Override
  public void unlock() {
    Thread me = Thread.currentThread();
    synchronized (this) {
      if (owner == me) {
        owner = null;
        notifyAll();
      }
    }
  }

  @Override
  public void lockInterruptibly() throws InterruptedException {
  }

  @Override
  public boolean tryLock() {
    return false;
  }

  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    return false;
  }

  @Override
  public Condition newCondition() {
    return null;
  }
}

public class P26UnreentrantLock {
  public static void main(String[] args) {
    UnreentrantLock lock = new UnreentrantLock();
    lock.lock();
    try {
      System.out.println("UnreentrantLock aquired 1");
      lock.lock();
      try {
        System.out.println("UnreentrantLock aquired 2");
      } finally {
        lock.unlock();
      }
    } finally {
      lock.unlock();
    }
  }
}
