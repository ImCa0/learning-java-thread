import java.util.concurrent.locks.ReentrantLock;

public class P26ReentrantLock {
  public static void main(String[] args) {
    // ReentrantLock(可重入锁) is a lock that can be acquired multiple times by the
    // same thread.
    ReentrantLock lock = new ReentrantLock();
    lock.lock();
    try {
      System.out.println("ReentrantLock aquired 1");
      lock.lock();
      try {
        System.out.println("ReentrantLock aquired 2");
      } finally {
        lock.unlock();
      }
    } finally {
      lock.unlock();
    }

    // synchronized blocks are reentrant
    synchronized (P26ReentrantLock.class) {
      System.out.println("Synchronized aquired 1");
      synchronized (P26ReentrantLock.class) {
        System.out.println("Synchronized aquired 2");
      }
    }

    // static synchronized methods are reentrant
    method1();
  }

  public static synchronized void method1() {
    System.out.println("method1 aquired");
    method2();
  }

  public static synchronized void method2() {
    System.out.println("method2 aquired");
  }
}
