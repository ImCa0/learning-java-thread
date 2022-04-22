import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ConditionThread extends Thread {
  private Lock lock;
  private Condition condition;

  ConditionThread(Lock lock, Condition condition) {
    this.lock = lock;
    this.condition = condition;
  }

  @Override
  public void run() {
    lock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + " acquired lock");
      condition.await();
    } catch (InterruptedException e) {
      System.out.println(Thread.currentThread().getName() + " interrupted");
    } finally {
      lock.unlock();
      System.out.println(Thread.currentThread().getName() + " released lock");
    }
  }
}

public class P24Condition {
  public static void main(String[] args) throws InterruptedException {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    ConditionThread thread = new ConditionThread(lock, condition);
    thread.start();
    Thread.sleep(1000);
    lock.lock();
    condition.signal();
    lock.unlock();
  }
}
